package ecom;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DoOrder {
    private static final String PAYMENT_STATUS_SUCCESSFUL = "Successful";

    private static final Logger logger = Logger.getLogger(DoOrder.class.getName());
    int orderId=0;
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            logger.log(Level.SEVERE, "MySQL JDBC Driver not found!", e);
        }
    }

    		
    //Method to Place an Order
    public int placeOrder(int userId, int productId, int quantity, String address, String paymentMethod) throws SQLException {
        Connection conn = ConnectionFactory.getInstance().getConnection();
        PreparedStatement getUserStmt = null;
        PreparedStatement getProductStmt = null;
        PreparedStatement updateStockStmt = null;
        PreparedStatement orderStmt = null;
        ResultSet userRs = null;
        ResultSet productRs = null;
        ResultSet generatedKeys = null;
        int orderId = -1;

        if (userId <= 0 || address == null || address.trim().isEmpty()) {
            logger.warning("❌ Invalid input for placing an order.");
            return -1;
        }

        try {
            conn.setAutoCommit(false); // Start transaction

            // Step 1: Validate User
            String getUserQuery = "SELECT name FROM customers WHERE customer_id = ?";
            getUserStmt = conn.prepareStatement(getUserQuery);
            getUserStmt.setInt(1, userId);
            userRs = getUserStmt.executeQuery();

            if (!userRs.next()) {
                System.out.println("❌ User not found.");
                return -1;
            }
            String userName = userRs.getString("name");
            System.out.println("👋 Hello, " + userName + "! Welcome to the E-commerce App.");

            // Step 2: Get Product Details
            String getProductQuery = "SELECT name, price, stock_quantity FROM products WHERE product_id = ?";
            getProductStmt = conn.prepareStatement(getProductQuery);
            getProductStmt.setInt(1, productId);
            productRs = getProductStmt.executeQuery();

            if (!productRs.next()) {
                System.out.println("❌ Product not found.");
                return -1;
            }
            String productName = productRs.getString("name");
            double productPrice = productRs.getDouble("price");
            int availableStock = productRs.getInt("stock_quantity");

            // Step 3: Check Stock
            if (availableStock < quantity) {
                System.out.println("❌ Insufficient stock. Available: " + availableStock);
                return -1;
            }
            double totalPrice = productPrice * quantity;

            // Step 4: Process Payment
            int paymentId = processPayment(conn, userId, totalPrice, paymentMethod);
            if (paymentId == -1) {
                System.out.println("❌ Payment failed! Order not placed.");
                conn.rollback();
                return -1;
            }

            // Step 5: Insert Order
            String PAYMENT_STATUS_SUCCESSFUL = "Successful"; // Define the constant
            String ORDER_STATUS_SUCCESSFUL = "Delivered";
            String orderQuery = "INSERT INTO orders (user_id, order_status, total_amount, address, payment_status, order_date, product_id, quantity, product_name, product_price, user_name, payment_id) " +
                    "VALUES (?, ?, ?, ?, ?, NOW(), ?, ?, ?, ?, ?, ?)";
            orderStmt = conn.prepareStatement(orderQuery, Statement.RETURN_GENERATED_KEYS);
            orderStmt.setInt(1, userId);
            orderStmt.setString(2, ORDER_STATUS_SUCCESSFUL);
            orderStmt.setDouble(3, totalPrice);
            orderStmt.setString(4, address);
            orderStmt.setString(5, PAYMENT_STATUS_SUCCESSFUL);
            orderStmt.setInt(6, productId);
            orderStmt.setInt(7, quantity);
            orderStmt.setString(8, productName);
            orderStmt.setDouble(9, productPrice);
            orderStmt.setString(10, userName);
            orderStmt.setInt(11, paymentId);

            int rowsInserted = orderStmt.executeUpdate();

            if (rowsInserted > 0) {
                generatedKeys = orderStmt.getGeneratedKeys();
                if (generatedKeys.next()) {
                    orderId = generatedKeys.getInt(1);
                    logger.info("✅ Order placed successfully! Order ID: " + orderId);
                } else {
                    logger.warning("❌ Order ID retrieval failed!");
                    conn.rollback();
                    return -1;
                }
            } else {
                logger.warning("❌ Order insertion failed!");
                conn.rollback();
                return -1;
            }

            // Step 6: Update Stock
            String updateStockQuery = "UPDATE products SET stock_quantity = stock_quantity - ? WHERE product_id = ?";
            updateStockStmt = conn.prepareStatement(updateStockQuery);
            updateStockStmt.setInt(1, quantity);
            updateStockStmt.setInt(2, productId);

            int stockUpdated = updateStockStmt.executeUpdate();
            if (stockUpdated <= 0) {
                System.out.println("❌ Stock update failed.");
                conn.rollback();
                return -1;
            }
            System.out.println("✅ Product stock updated successfully!");

            conn.commit(); // Commit transaction
            return orderId;

        } catch (SQLException e) {
            if (conn != null) {
                try {
                    conn.rollback();
                    System.err.println("❌ Transaction failed. Rolling back changes.");
                } catch (SQLException rollbackEx) {
                    rollbackEx.printStackTrace();
                }
            }
            e.printStackTrace();
            return -1;
        } finally {
            // Close resources properly
            try {
                if (userRs != null) userRs.close();
                if (productRs != null) productRs.close();
                if (generatedKeys != null) generatedKeys.close();
                if (getUserStmt != null) getUserStmt.close();
                if (getProductStmt != null) getProductStmt.close();
                if (updateStockStmt != null) updateStockStmt.close();
                if (orderStmt != null) orderStmt.close();
                if (conn != null) conn.setAutoCommit(true); // Restore AutoCommit
                if (conn != null) conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    // 📌 Method to Make Payment
//    public boolean processPayament(int orderId, double amount, String paymentMethod) {
//        if (orderId <= 0 || amount <= 0 || paymentMethod == null || paymentMethod.trim().isEmpty()) {
//            logger.warning("Invalid input for making payment.");
//            return true;
//        }

    public int processPayment(Connection conn, int userId, double amount, String paymentMethod) throws SQLException {
        PreparedStatement paymentStmt = null;
        PreparedStatement userCheckStmt = null;
        ResultSet rs = null;
        ResultSet generatedKeys = null;

        try {
            // Step 1: Check if the user exists and is authenticated
            String userCheckQuery = "SELECT COUNT(*) FROM customers WHERE customer_id = ?";
            userCheckStmt = conn.prepareStatement(userCheckQuery);
            userCheckStmt.setInt(1, userId);
            rs = userCheckStmt.executeQuery();
            if (rs.next() && rs.getInt(1) == 0) {
                System.out.println("Authentication failed: User does not exist.");
                return -1;
            }

            // Step 2: Validate Payment Method
            String paymentStatus = "Completed"; // Default status
            if (paymentMethod.equalsIgnoreCase("Cash on Delivery")) {
                paymentStatus = "Pending";
            } else if (!isValidPaymentMethod(paymentMethod)) {  // 🔹 Define this method or remove it
                System.out.println("Invalid payment method: " + paymentMethod);
                return -1;
            }

            // Step 3: Insert Payment into Database
            String paymentQuery = "INSERT INTO payments (user_id, PaymentDate, Amount, PaymentMethod, PaymentStatus) VALUES (?, NOW(), ?, ?, ?)";
            paymentStmt = conn.prepareStatement(paymentQuery, Statement.RETURN_GENERATED_KEYS);
            paymentStmt.setInt(1, userId);
            paymentStmt.setDouble(2, amount);
            paymentStmt.setString(3, paymentMethod);
            paymentStmt.setString(4, paymentStatus); // 🔹 Now inserting Payment Status

            int rowsInserted = paymentStmt.executeUpdate();
            if (rowsInserted <= 0) {
                System.out.println(" Payment failed.");
                conn.rollback();  // 🔹 Ensure rollback happens before returning
                return -1;
            }

            // Step 4: Get Generated Payment ID
            generatedKeys = paymentStmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                int paymentId = generatedKeys.getInt(1);
                System.out.println("Payment successfully inserted! Payment ID: " + paymentId);
                return paymentId;
            } else {
                System.out.println("Payment ID retrieval failed.");
                conn.rollback();  // 🔹 Ensure rollback
                return -1;
            }

        } catch (SQLException e) {
            try {
                conn.rollback();  // 🔹 Always rollback on exception
                System.err.println("Transaction failed. Rolling back changes.");
            } catch (SQLException rollbackEx) {
                rollbackEx.printStackTrace();
            }
            e.printStackTrace();
            return -1;
        } finally {
            // Step 5: Cleanup
            try {
                if (rs != null) rs.close();
                if (paymentStmt != null) paymentStmt.close();
                if (userCheckStmt != null) userCheckStmt.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
          
    
    public boolean isValidPaymentMethod(String paymentMethod) {
        String[] validMethods = {"Credit Card", "Debit Card", "UPI", "PayPal", "Net Banking", "Cash on Delivery"};
        for (String method : validMethods) {
            if (method.equalsIgnoreCase(paymentMethod)) {
                return true;
            }
        }
        return false;
    }
    
    }
	    


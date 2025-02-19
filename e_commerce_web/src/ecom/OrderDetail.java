package ecom;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderDetail {
    
    public void ShowOrder() throws SQLException {
    	Connection conn = ConnectionFactory.getInstance().getConnection();
        try  {
        	PreparedStatement stmt = conn.prepareStatement("SELECT * FROM orders");
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                System.out.println("Order Details:");
                System.out.println("Order ID: " + rs.getInt("order_id"));
                System.out.println("User ID: " + rs.getInt("user_id"));
                System.out.println("User Name: " + rs.getString("user_name"));
                System.out.println("Status: " + rs.getString("order_status"));
                System.out.println("Total Amount: " + rs.getDouble("total_amount"));
                System.out.println("Address: " + rs.getString("address"));
                System.out.println("Payment Status: " + rs.getString("payment_status"));
                System.out.println("Order Date: " + rs.getTimestamp("order_date"));
                System.out.println("Product id: " + rs.getInt("product_id"));
                System.out.println("Quantity: " + rs.getInt("quantity"));
                System.out.println("Product Name: " + rs.getString("product_name"));
                System.out.println("Product Price: " + rs.getInt("product_price"));
                System.out.println("Payment id: " + rs.getInt("payment_id"));
            } else {
                System.out.println("Order not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void cancelOrder(int orderId) throws SQLException {
    	Connection conn = ConnectionFactory.getInstance().getConnection();
        PreparedStatement checkStatusStmt = null, updateStatusStmt = null, restoreStockStmt = null;
        ResultSet rs = null;
        
        try {
            conn.setAutoCommit(false);

            //Step 1: Check Order Status
            checkStatusStmt = conn.prepareStatement("SELECT order_status FROM orders WHERE order_id = ?");
            checkStatusStmt.setInt(1, orderId);
            rs = checkStatusStmt.executeQuery();

            if (!rs.next()) {
                System.out.println("Order not found.");
                return;
            }
            
            String status = rs.getString("order_status");
            if (status.equals("Delivered")) {
                System.out.println("Cannot cancel. Order is already delivered.");
                return;
            }

            // Step 2: Update Order Status to 'Cancelled'
            updateStatusStmt = conn.prepareStatement("UPDATE orders SET order_status = 'Cancelled' WHERE order_id = ?");
            updateStatusStmt.setInt(1, orderId);
            updateStatusStmt.executeUpdate();

            //Step 3: Restore Stock for Cancelled Items
            restoreStockStmt = conn.prepareStatement(
                "UPDATE products p JOIN order_items oi ON p.product_id = oi.product_id " +
                "SET p.stock = p.stock + oi.quantity WHERE oi.order_id = ?");
            restoreStockStmt.setInt(1, orderId);
            restoreStockStmt.executeUpdate();

            conn.commit();
            System.out.println("Order cancelled successfully! Stock restored.");

        } catch (SQLException e) {
            try { if (conn != null) conn.rollback(); } catch (SQLException ex) { ex.printStackTrace(); }
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (checkStatusStmt != null) checkStatusStmt.close();
                if (updateStatusStmt != null) updateStatusStmt.close();
                if (restoreStockStmt != null) restoreStockStmt.close();
                if (conn != null) conn.close();
            } catch (SQLException ex) { ex.printStackTrace(); }
        }
    }

}

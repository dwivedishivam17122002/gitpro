package ecom;
import java.sql.*;
//cart_id
//user_id
//product_id
//quantity
//added_date
//product_name
//product_price
//product_price
//user_name
//totalPrice
public class CartDao {
	
	// Method to fetch user name and greet the user
	public void greetUser(int userId) {
	    Connection con = null;
	    PreparedStatement ps = null;
	    ResultSet rs = null;

	    try {
	        con = ConnectionFactory.getInstance().getConnection();
	        String query = "SELECT name FROM customers WHERE customer_id = ?";
	        ps = con.prepareStatement(query);
	        ps.setInt(1, userId);
	        rs = ps.executeQuery();

	        if (rs.next()) {
	            String userName = rs.getString("name");
	            System.out.println("Hello, " + userName + " ! Welcome to the Cart App.");
	        } else {
	            System.out.println("User not found with ID: " + userId);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (rs != null) rs.close();
	            if (ps != null) ps.close();
	            if (con != null) con.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	}

	
	
	// List all available products
    public void listProducts() {
     	  String query = "SELECT * FROM products";
     	    try(Connection con = ConnectionFactory.getInstance().getConnection();
     	    	PreparedStatement ptmt = con.prepareStatement(query);
     	    	ResultSet rs = ptmt.executeQuery()){
     	    	while(rs.next()) {
     	    		System.out.println("Product Id: "+rs.getInt("product_id"));
     	    		System.out.println("Product Name: "+rs.getString("name"));
     	    		System.out.println("Category Id: "+rs.getInt("category_id"));
     	    		System.out.println("Product price: "+rs.getFloat("price"));
     	    		System.out.println("Stock Quantity: "+rs.getInt("stock_quantity"));
     	    		System.out.println("Category Name: "+rs.getString("category_name"));
     	    		System.out.println("--------------------------------------------------");
     	    	}
     	    }catch(SQLException  e) {
     	    	e.printStackTrace();
     	    }
     	    
       }

	
    public void addToCart(int userId, int productId, int quantity) throws SQLException {
        Connection con = ConnectionFactory.getInstance().getConnection();
        PreparedStatement getUserStmt = null;
        PreparedStatement getProductStmt = null;
        PreparedStatement insertCartStmt = null;
        PreparedStatement updateStockStmt = null;
        ResultSet userRs = null;
        ResultSet productRs = null;

        try {
            con.setAutoCommit(false);

            // Step 1: Get user name
            String getUserQuery = "SELECT name FROM customers WHERE customer_id = ?";
            getUserStmt = con.prepareStatement(getUserQuery);
            getUserStmt.setInt(1, userId);
            userRs = getUserStmt.executeQuery();

            if (!userRs.next()) {
                System.out.println("User not found.");
                return;
            }
            String userName = userRs.getString("name");
            System.out.println("Hello, " + userName + "! Welcome to the Cart App.");
            // Step 2: Get product details
            String getProductQuery = "SELECT name, price, stock_quantity FROM products WHERE product_id = ?";
            getProductStmt = con.prepareStatement(getProductQuery);
            getProductStmt.setInt(1, productId);
            productRs = getProductStmt.executeQuery();

            if (!productRs.next()) {
                System.out.println("Product not found.");
                return;
            }
            String productName = productRs.getString("name");
            double productPrice = productRs.getDouble("price");
            int availableStock = productRs.getInt("stock_quantity");

            // Step 3: Check stock availability
            if (availableStock < quantity) {
                System.out.println("Insufficient stock. Available: " + availableStock);
                return;
            }

            double totalPrice = productPrice * quantity;

            // Step 4: Insert into cart
            String insertCartQuery = "INSERT INTO cart (user_id, user_name, product_id, product_name, product_price, quantity, totalPrice, added_date) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, NOW())";
            insertCartStmt = con.prepareStatement(insertCartQuery);
            insertCartStmt.setInt(1, userId);
            insertCartStmt.setString(2, userName);
            insertCartStmt.setInt(3, productId);
            insertCartStmt.setString(4, productName);
            insertCartStmt.setDouble(5, productPrice);
            insertCartStmt.setInt(6, quantity);
            insertCartStmt.setDouble(7, totalPrice);

            int rowsInserted = insertCartStmt.executeUpdate();
       
            if (rowsInserted > 0) {
                System.out.println("✅ Product added to cart successfully.");
            } else {
                System.out.println("❌ Failed to add product to cart.");
            }

            // Step 5: Update product stock
//            String updateStockQuery = "UPDATE products SET stock_quantity = stock_quantity - ? WHERE product_id = ?";
//            updateStockStmt = con.prepareStatement(updateStockQuery);
//            updateStockStmt.setInt(1, quantity);
//            updateStockStmt.setInt(2, productId);
//
//            int rowsUpdated = updateStockStmt.executeUpdate();
//            System.out.println("Product stock updated successfully!");

            con.commit();
            displayTotalCartPrice(con, userId);

        } catch (SQLException e) {
            if (con != null) {
                con.rollback();
            }
            e.printStackTrace();
        } finally {
            if (userRs != null) userRs.close();
            if (productRs != null) productRs.close();
            if (getUserStmt != null) getUserStmt.close();
            if (getProductStmt != null) getProductStmt.close();
            if (insertCartStmt != null) insertCartStmt.close();
            if (updateStockStmt != null) updateStockStmt.close();
            if (con != null) con.close();
        }
    }

  
    
    //Method to display total price of all products in the cart for a specific customer
    private void displayTotalCartPrice(Connection con,int userId) {
    	PreparedStatement totalCartStmt = null;
        ResultSet rs = null;
        
        try {
            String totalCartQuery = "SELECT SUM(totalPrice) AS total_cart_price FROM cart WHERE user_id = ?";
            totalCartStmt = con.prepareStatement(totalCartQuery);
            totalCartStmt.setInt(1, userId);

            rs = totalCartStmt.executeQuery();

            if (rs.next()) {
                double totalCartPrice = rs.getDouble("total_cart_price");
                System.out.println("Total Price of all items in the cart for User ID " + userId + ": $" + totalCartPrice);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (totalCartStmt != null) totalCartStmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
    // Display all items in the user's cart
    public void displayUserCart(int userId) {
        String query = "SELECT c.cart_id ,p.name, c.quantity, c.totalPrice FROM cart c JOIN products p ON c.product_id = p.product_id WHERE c.user_id = ?";
        try (Connection con = ConnectionFactory.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, userId);
            try (ResultSet rs = ps.executeQuery()) {
                System.out.println("\n===== Your Cart =====");
                boolean isEmpty = true;

                while (rs.next()) {
                    isEmpty = false;
                    System.out.println("Cart Id: " + rs.getString("cart_id"));
                    System.out.println("Product Name: " + rs.getString("name"));
                    System.out.println("Quantity: " + rs.getInt("quantity"));
                    System.out.println("Total Price: $" + rs.getDouble("totalPrice"));
                    System.out.println("---------------------------");
                }

                if (isEmpty) {
                    System.out.println("Your cart is empty.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void updateCart(int userId,  int newQuantity,int cartid) throws SQLException {
    	String updateQuery = "UPDATE cart SET quantity = ? WHERE user_id = ? AND cart_id = ?";
        try (Connection con = ConnectionFactory.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(updateQuery)) {
            ps.setInt(1, newQuantity);
            ps.setInt(2, userId);
            ps.setInt(3, cartid);
            
            	int rowsUpdated = ps.executeUpdate();
                if (rowsUpdated > 0) {
                    System.out.println("✅ Cart updated successfully.");
                } else {
                    System.out.println("❌ No matching item found in your cart.");
                }
                
            }
         catch (SQLException e) {
            e.printStackTrace();
        }
       
    }
    
 // Method to delete a product from the cart and update stock
    public void deleteFromCart(int userId, int productId,int cartId) {
        Connection con = null;
        PreparedStatement checkCartStmt = null;
        PreparedStatement deleteCartStmt = null;
        PreparedStatement updateStockStmt = null;
        ResultSet rs = null;

        try {
            con = ConnectionFactory.getInstance().getConnection();
            con.setAutoCommit(false); // Start transaction

            // Step 1: Check if the product exists in the user's cart
            String checkCartQuery = "SELECT quantity FROM cart WHERE user_id = ? AND product_id = ?";
            checkCartStmt = con.prepareStatement(checkCartQuery);
            checkCartStmt.setInt(1, userId);
            checkCartStmt.setInt(2, productId);
            rs = checkCartStmt.executeQuery();

            if (rs.next()) {
                int quantityInCart = rs.getInt("quantity");

                // Step 2: Delete the product from the cart
                String deleteCartQuery = "DELETE FROM cart WHERE user_id = ? AND product_id = ? AND cart_id = ?";
                deleteCartStmt = con.prepareStatement(deleteCartQuery);
                deleteCartStmt.setInt(1, userId);
                deleteCartStmt.setInt(2, productId);
                deleteCartStmt.setInt(3, cartId);

                int rowsDeleted = deleteCartStmt.executeUpdate();
             
                if (rowsDeleted > 0) {
                    System.out.println("✅ Product removed from cart successfully.");
                } else {
                    System.out.println("❌ No product found in cart.");
                }

                // Step 3: Restore the stock quantity in the products table
//                String updateStockQuery = "UPDATE products SET stock_quantity = stock_quantity + ? WHERE product_id = ?";
//                updateStockStmt = con.prepareStatement(updateStockQuery);
//                updateStockStmt.setInt(1, quantityInCart);
//                updateStockStmt.setInt(2, productId);
//
////                int rowsUpdated = updateStockStmt.executeUpdate();
//                System.out.println("Product stock updated successfully!");

                con.commit(); // Commit the transaction
            } else {
                System.out.println("Product not found in the cart.");
            }

        } catch (SQLException e) {
            try {
                if (con != null) con.rollback(); // Rollback in case of error
            } catch (SQLException rollbackEx) {
                rollbackEx.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (checkCartStmt != null) checkCartStmt.close();
                if (deleteCartStmt != null) deleteCartStmt.close();
                if (updateStockStmt != null) updateStockStmt.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
    





































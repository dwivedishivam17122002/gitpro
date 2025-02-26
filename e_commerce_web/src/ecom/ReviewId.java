package ecom;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ReviewId {

	public static void addReview(int ProductId, int userId, String reviewText, int rating) throws SQLException {
	    String query = "INSERT INTO reviews (UserID, ProductID , Rating, Comment) VALUES (?, ?, ?, ?)";
	    try (Connection con = ConnectionFactory.getInstance().getConnection();
	    		PreparedStatement stmt = con.prepareStatement(query)) {
	        stmt.setInt(1, userId);
	        stmt.setInt(2, ProductId);
	        stmt.setInt(3, rating);
	        stmt.setString(4, reviewText);
	        int rowsInserted = stmt.executeUpdate();
	        if (rowsInserted > 0) {
	            System.out.println("Review added successfully!");
	        }
	    }
	}
	
	
	
	public static void showReviewsByProduct( int productId) throws SQLException {
	    String query = "SELECT p.name, c.Name, r.Rating, r.comment " +
                "FROM reviews r " +
                "JOIN products p ON r.ProductID =  " +
                "JOIN customers c ON r.UserID = c.customer_id ";
	    try (Connection con = ConnectionFactory.getInstance().getConnection();
	    		PreparedStatement stmt = con.prepareStatement(query)) {
	        stmt.setInt(1, productId);
	        try(ResultSet rs = stmt.executeQuery()){
	       
	        System.out.println("Product | User | Rating | Comment");
	        System.out.println("-----------------------------------");

	        boolean hasReviews = false;
	        while (rs.next()) {
	        	   if (!hasReviews) {
	        		   hasReviews = true;
	               }
	        	   
	            System.out.println("Product Name: " +rs.getString("name") + " | " +
	            "User Name: " + rs.getString("Name") + " | " +
	            "Rating: " + rs.getInt("Rating") + " | " +
	           "Comment: " + rs.getString("comment") != null ? rs.getInt("comment") :"No Comment");
	            System.out.println("---------------------------------------------------");
	        }
	        if (!hasReviews) {
	            System.out.println("No reviews found for this product.");
	        }
	        }
	    }
	}
}
		
	
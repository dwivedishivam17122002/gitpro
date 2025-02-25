package ecom;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GetUserId { 
              public static int userId(String userName , String password) throws SQLException {
            	 String query="SELECT customer_id FROM customers WHERE email_id = ?";
            	 int userId = -1;
            	 try (Connection con = ConnectionFactory.getInstance().getConnection();
                         PreparedStatement ptmt = con.prepareStatement(query)) {

                        ptmt.setString(1, userName.trim());
//                        ptmt.setString(2, password);

                        try(ResultSet rs = ptmt.executeQuery()){
                        	if (rs.next()) { // Ensure there is a result
                                userId = rs.getInt("customer_id");
                                return userId;
                            } else {
                                System.out.println("❌ No user found with this email.");
                                return -1;
                            }
                        }
                            
                        
                    } catch(SQLException e) {
            		e.printStackTrace();
            		return -2;
            	}
				
}
}
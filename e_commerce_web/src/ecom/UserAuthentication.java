package ecom;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
//import org.mindrot.jbcrypt.BCrypt; 
public class UserAuthentication {
	 public static String login(String username, String password) {
	        String query = "SELECT password, role FROM customers WHERE email_id = ? AND password = ?";
	        
	        try (Connection conn = ConnectionFactory.getInstance().getConnection();
	             PreparedStatement ps = conn.prepareStatement(query)) {
	            
	            ps.setString(1, username.trim());
	            ps.setString(2, password);
	           try( ResultSet rs = ps.executeQuery()){
	        	   if (rs.next()) {
//		                String hashedPassword = rs.getString("password");
		                String role = rs.getString("role");
		                
//		                if(passwordUtil.hashPassword(password,hashedPassword)) {
//		                }
		                return role;
		            }else {
		            	System.out.println("❌ No user found with this email.");
	                    return null; // Return error code
		            }
	           }

	           
	        } catch (Exception e) {
	            e.printStackTrace();
	            return "DB_ERROR";
	        }
//	        finally {
//	        	rs.close();

	    //	        }
		 return null;
	  }
}

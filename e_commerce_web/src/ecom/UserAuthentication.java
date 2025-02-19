package ecom;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserAuthentication {
	 public static String login(String username, String password) {
	        String query = "SELECT password, role FROM users WHERE username = ? AND password = ?";
	        
	        try (Connection conn = ConnectionFactory.getInstance().getConnection();
	             PreparedStatement ps = conn.prepareStatement(query)) {
	            
	            ps.setString(1, username);
	            ps.setString(2, password);
	            ResultSet rs = ps.executeQuery();

	            if (rs.next()) {
	                String hashedPassword = rs.getString("password");
	                String role = rs.getString("role");
	                
	                if(passwordUtil.hashPassword(password,hashedPassword)) {
	                	return role;
	                }
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return null;
	    }
}

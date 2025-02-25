package ecom;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class MainC {
	
	 public static boolean isAdmin(String username, String userPassword) {

	        // JDBC connection and query
	        try (Connection conn = ConnectionFactory.getInstance().getConnection();) {
	            String query = "SELECT * FROM customers WHERE email_id = ? AND password = ? AND role = 'admin'";

	            try (PreparedStatement pstmt = conn.prepareStatement(query)) {
	                pstmt.setString(1, username);
	                pstmt.setString(2, userPassword);

	                try (ResultSet rs = pstmt.executeQuery()) {
	                    return rs.next();
	                }
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return false; 
	    }
	 
	 
	public static boolean registerUser(String username, String password) {
        String checkQuery = "SELECT COUNT(*) FROM customers WHERE email_id = ?";
        try (Connection conn = ConnectionFactory.getInstance().getConnection();
            PreparedStatement checkStmt = conn.prepareStatement(checkQuery)) {
        	 checkStmt.setString(1, username);
             ResultSet rs = checkStmt.executeQuery();
             rs.next();
             
             if (rs.getInt(1) > 0) {
                 System.out.println("You are already registered!");
                 System.out.println("Please login into the from!");
                 return false;
             } else {
                 // Step 2: Insert new user
            	 customerService.addUser();
            	  return true;
                 }
               
             } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
	
	//                      !---------main fuction --------!
       public static void main(String[] args) throws SQLException {
    	   
    	   try (Scanner scanner = new Scanner(System.in)) {
			while (true) {
			   System.out.println("1. Register");
			   System.out.println("2. Login");
			   System.out.println("3. Exit");
			   System.out.print("👉 Choose an option: ");
			   
			   if (!scanner.hasNextInt()) { // ✅ Fix invalid input
			       System.out.println("❌ Invalid input. Please enter a number.");
			       scanner.next(); // Clear invalid input
			       continue;
			   }
			   
			   int choice = scanner.nextInt();
			  scanner.nextLine();

			   Menu mn = new Menu();

			   if (choice == 1) {
			   	System.out.print("Enter Username (please enter the your email id in use name section) : ");
			       String username = scanner.nextLine();
			       
			       System.out.print("Enter Password: ");
			       String password = scanner.nextLine();
			       if (registerUser(username, password)) {
			           System.out.println("User registered successfully!");
			       } else {
			           System.out.println("Registration failed!");
			       }
			   } else if (choice == 2) {
			       int attempts = 3; // Max login attempts

			       while (attempts > 0) {
			           System.out.print("Enter Username: ");
			           String username = scanner.nextLine();
//                   scanner.nextLine();
			           System.out.print("Enter Password: ");
			           String password = scanner.nextLine();
			             
			           String role = UserAuthentication.login(username, password);
			           if (role != null) {
			               System.out.println("✅ Login Successful! You can now access the system.");
			               if(isAdmin(username,password)) {
			            	   AdminView.adminMenu(username,password, role);
			            	   return;
			               }
			               mn.showMenu(username,password,role);  // Go to menu after successful login
			               
			               break;  // Exit loop after successful login
			           } else {
			               attempts--;
			               System.out.println("❌ Login Failed! Attempts left: " + attempts);
			               if (attempts == 0) {
			                   System.out.println("🚫 Too many failed attempts! Exiting...");
			               }
			           }
			       }
			 
			   }else if(choice ==3) {
				   System.out.println("🙏 Thanks you..................");
				   break;
			   }else {
			       System.out.println("❌ Invalid choice. Please try again.");
			   }
			   }
			 
		}
	}
}

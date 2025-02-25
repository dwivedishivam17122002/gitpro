package ecom;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class customerService {
	
	public static void addUser() {
		Scanner sc = new Scanner(System.in);
		CustomerBean customerb = new CustomerBean();
		 CustomerDao customer = new CustomerDao();
		 
		 System.out.println("For adding of user filling some data...................................");
//		 sc.nextLine();
		 System.out.println("Enter your name! ");
		 String name = sc.nextLine();
//		 sc.nextLine();
		 System.out.println("Enter your Email");
		 String email = sc.nextLine();
//		 sc.nextLine();
		 System.out.println("Enter your Password ");
		 String password = sc.nextLine();
		 
		 System.out.println("Enter your Phone Number ");
		 String number = sc.nextLine();
//		 sc.nextLine();
		 System.out.println("Enter your Address");
		 String address = sc.nextLine();
		 
		 customerb.setName(name);
		 customerb.setEmail(email);
		 customerb.setPassword(password);
		 customerb.setPhone_no(number);
		 customerb.setAddress(address);
		 
		 customer.addCustomer(customerb);
		 System.out.println("User Add Successfully ");
//		 sc.close();
		 }
	  public static void updateSpecificUser(String username,String password) {
	        Scanner scanner = new Scanner(System.in);
//				scanner.nextLine();
				// User Login

				int userId = getUserId(username, password);
				if (userId == -1) {
				    System.out.println("Invalid username or password!");
				    return;
				}

				System.out.println("Login successful! You can update your details.");
         customerService.updateUser(userId);
			
	  }
	   private static int getUserId(String username, String password) {
		   String query = "SELECT customer_id FROM customers WHERE email_id = ? AND password = ?";
	        try (Connection conn = ConnectionFactory.getInstance().getConnection();
	                PreparedStatement pstmt = conn.prepareStatement(query)) {

	               pstmt.setString(1, username);
	               pstmt.setString(2, password);
	               
	               try (ResultSet rs = pstmt.executeQuery()) {
	                   if (rs.next()) {
	                       return rs.getInt("customer_id"); // Return the user ID
	                   }
	               }
	           } catch (SQLException e) {
	               e.printStackTrace();
	           }
	           return -1; // Return -1 if authentication fails
	       }
		
	
	public static void updateUser(int userId) {

			 CustomerDao customer = new CustomerDao();
		   Scanner sc = new Scanner(System.in);
		   System.out.println("Update customer................");
		   
		 
		   while(true) {
			   System.out.println("1. Update Name of customer");
			   System.out.println("2. Update Phone Number");
			   System.out.println("3. Update Email");
               System.out.println("4. Change Password");
			   System.out.println("5. Update Address");
			   System.out.println("6. Exit");
//			   sc.nextLine();
			   System.out.print("Choose an option: ");
               int choice = sc.nextInt();
//               sc.nextLine();
               switch (choice) {
               case 1:
                   System.out.println("Update Name of customer");
                   System.out.println("Enter Update User Name ");
                   sc.nextLine();
                   String name = sc.nextLine();
                   customer.updateName(name,userId);
                   break;

               case 2:
                   System.out.print(" Update Phone Number: ");
                   System.out.println("Enter Updated Phone Number");
                   sc.nextLine();
                   String phone = sc.nextLine();
                   customer.updatePhoneNo(phone, userId);
                   break;

               case 3:
            	   System.out.print(" Update Email or user name: ");
                   System.out.println("Enter Updated User which is being unique");
                   sc.nextLine();
                   String email = sc.nextLine();
                   customer.updateEmail(email, userId);
                   break;
               
               case 4:
            	   System.out.println("Change Password: ");
            	   System.out.println("Enter updated password");
            	   sc.nextLine();
            	   String Password = sc.nextLine();
            	   customer.changePassword(Password, userId);
                   break;
               case 5:
            	   System.out.println("Change Address");
            	   System.out.println("Enter Updated password");
            	   sc.nextLine();
            	   String address = sc.nextLine();
            	   customer.updateAddress(address, userId);
            	   break;
               case 6:
                   System.out.println("Thank you for using the Cart App!");
//                   sc.close();
                   return;

               default:
                   System.out.println("Invalid option. Please try again.");
           }
		   }
	   }
           public static void customerOp(int userId) {
        	   
        	   CustomerDao customer = new CustomerDao();
//        	   CustomerBean shivam = new CustomerBean();
//        	   CustomerBean kishan = new CustomerBean();
//        	   CustomerBean customerb = new CustomerBean();
        	   
        	   
        	   //customer
//        	   shivam.setName("shivam");
//        	   shivam.setEmail("dwivedishivam17122002@gmail.com");
//        	   shivam.setPassword("shi@123");
//        	   shivam.setPhone_no(234567891);
//        	   shivam.setAddress("manikpur");
//        	   

//        	   kishan.setName("kishan");
//        	   kishan.setEmail("dwivedikishan0@gmail.com");
//        	   kishan.setPassword("kish@12");
//        	   kishan.setPhone_no(234567891);
//        	   kishan.setAddress("manikpur");
        	   
        	   //Adding data
//        	   customer.addCustomer(shivam);
//        	   customer.addCustomer(kishan);
        	   //get customer by id 
        	   //customer.getCustomerById(01);
        	   //get all customer
//        	   customer.findAll();
        	   
        	   //update customer
        	   //customer.update("om", 02);
        	   
        	   //customer deleted
        	   //customer.delete(01);
        	   
        	   
             	  Scanner sc = new Scanner(System.in);
            	   CartDao cartDao = new CartDao();
            	   
            	   //Greet the user
            	   cartDao.greetUser(userId);
            	   
            	   while (true) {
                       System.out.println("\n===== Product Menu =====");
                       System.out.println("1. Add customer");
                       System.out.println("2. Update customer");
                       System.out.println("3. Delete customer");
                       System.out.println("4. Find all customer");
                       System.out.println("5. Find detail of specific customer");
                       System.out.println("6. Exit");
                       System.out.print("Choose an option: ");
                       int choice = sc.nextInt();
        
                       switch (choice) {
                           case 1:
                               System.out.println("Adding customer");
                               customerService.addUser();
                               break;
        
                           case 2:
                               System.out.print("Update customer: ");
                               customerService.updateUser(userId);
                               break;
        
                           case 3:
                        	   System.out.println("Delete customer");
                        	   customer.delete(userId);
                               break;
                           
                           case 4:
                        	   System.out.println(" Find all customer: ");
                               customer.findAll();
                               break;
                           case 5:
                        	   System.out.println("Get customer By Id");
                        	   CustomerDao.getCustomerById(userId);
                        	   break;
                           case 6:
                               System.out.println("Thank you for using the Cart App!");
//                               sc.close();
                               return;
        
                           default:
                               System.out.println("Invalid option. Please try again.");
                       }
                   }
           }
}

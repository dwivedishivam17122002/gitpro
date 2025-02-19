package ecom;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class MainC {
	
	public static boolean registerUser(CustomerBean customerBean,String username, String password) {
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
                    CustomerDao cd = new CustomerDao();
                    cd.addCustomer(customerBean);
                 }
                 return true;
             } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
	
	
       public static void main(String[] args) throws SQLException {
    	   
    	   
    	   Scanner sc = new Scanner(System.in);
    	   Scanner scanner = new Scanner(System.in);
           CustomerBean customerBean = null;
           System.out.println("1. Register");
           System.out.println("2. Login");
           int choice = scanner.nextInt();
           scanner.nextLine(); 

           Menu mn = new Menu();

           if (choice == 1) {
           	System.out.print("Enter Username (please enter the your email id in use name section) : ");
               String username = scanner.nextLine();

               System.out.print("Enter Password: ");
               String password = scanner.nextLine();
               if (registerUser( customerBean, username, password)) {
                   System.out.println("User registered successfully!");
               } else {
                   System.out.println("Registration failed!");
               }
           } else if (choice == 2) {
               int attempts = 3; // Max login attempts

               while (attempts > 0) {
                   System.out.print("Enter Username: ");
                   String username = scanner.nextLine();

                   System.out.print("Enter Password: ");
                   String password = scanner.nextLine();
                     
                   String role = UserAuthentication.login(username, password);
                   if (role != null) {
                       System.out.println("✅ Login Successful! You can now access the system.");
                       mn.showMenu(username,role);  // Go to menu after successful login
                       break;  // Exit loop after successful login
                   } else {
                       attempts--;
                       System.out.println("❌ Login Failed! Attempts left: " + attempts);
                       if (attempts == 0) {
                           System.out.println("🚫 Too many failed attempts! Exiting...");
                       }
                   }
               }
         
           }

           scanner.close();
           
           
//    	   CustomerDao customer = new CustomerDao();
//    	   CustomerBean shivam = new CustomerBean();
//    	   CustomerBean kishan = new CustomerBean();
    	   
//    	   CategoriesDao categories = new CategoriesDao();
//    	   CategoriesDao electronic = new CategoriesDao();
//    	   CategoriesDao groceries = new CategoriesDao();
    	   
//    	   ProductDao product = new ProductDao();
    	   
//    	   CartDao cartDao = new CartDao();
//    	   System.out.println("Enter your User ID: ");
//    	   int userId = sc.nextInt();
//    	   
//    	   //Greet the user
//    	   cartDao.greetUser(userId);
//    	   
//    	   while (true) {
//               System.out.println("\n===== Product Menu =====");
//               System.out.println("1. List Products");
//               System.out.println("2. Add Product to Cart");
//               System.out.println("3. View Cart");
//               System.out.println("4. Delete Product from Cart");
//               System.out.println("5. Exit");
//               System.out.print("Choose an option: ");
//               int choice = sc.nextInt();
//
//               switch (choice) {
//                   case 1:
//                       cartDao.listProducts();
//                       break;
//
//                   case 2:
//                       System.out.print("Enter Product ID to add: ");
//                       int productId = sc.nextInt();
//                       System.out.print("Enter Quantity: ");
//                       int quantity = sc.nextInt();
//
//                       cartDao.addToCart(userId, productId, quantity);
//                       break;
//
//                   case 3:
//                       cartDao.displayUserCart(userId);
//                       break;
//                   
//                   case 4:
//                	   System.out.println("Enter Product ID to delete from cart: ");
//                	   int deleteProductId = sc.nextInt();
//                       cartDao.deleteFromCart(userId, deleteProductId);
//                       break;
//                       
//                   case 5:
//                       System.out.println("Thank you for using the Cart App!");
//                       sc.close();
//                       return;
//
//                   default:
//                       System.out.println("Invalid option. Please try again.");
//               }
//           }
    	   
    	   
//    	   DoOrderService dos = new DoOrderService();
//    	   OrdreDetailService ods = new OrdreDetailService();
    	   
//           DoOrder od = new DoOrder();
//           while (true) {
//               System.out.println("\nSelect an option:");
//               System.out.println("1️Place an Order");
//               System.out.println("2️Make a Payment");
//               System.out.println("3️Exit");
//               System.out.print("👉 Enter choice: ");
//               int choice = sc.nextInt();
//
//               switch (choice) {
//                   case 1:
//                       System.out.print("Enter User ID: ");
//                       int userId = sc.nextInt();
//                       System.out.print("Enter Total Amount: ");
//                       double totalAmount = sc.nextDouble();
//                       sc.nextLine(); // Consume newline
//                       System.out.print("Enter Address: ");
//                       String address = sc.nextLine();
//                       int orderId = DoOrder.placeOrder(userId, totalAmount, address);
//                       if (orderId != -1) {
//                           System.out.println("Order successfully placed with ID: " + orderId);
//                       }
//                       break;
//                   case 2:
//                       System.out.print("\nEnter Order ID to make payment: ");
//                       int orderIdForPayment = sc.nextInt();
//                       System.out.print("Enter payment amount: ");
//                       double amount = sc.nextDouble();
//                       sc.nextLine(); // Consume newline
//                       System.out.print("Enter payment method: ");
//                       String paymentMethod = sc.nextLine();
//                       DoOrder.makePayment(orderIdForPayment, amount, paymentMethod);
//                       break;
//                   case 3:
//                       System.out.println("Exiting... Thank you!");
//                       sc.close();
//                       return;
//                   default:
//                       System.out.println("Invalid choice, please try again.");
//               }
//           }
    	   
    	   //customer
//    	   shivam.setName("shivam");
//    	   shivam.setEmail("dwivedishivam17122002@gmail.com");
//    	   shivam.setPassword("shi@123");
//    	   shivam.setPhone_no(234567891);
//    	   shivam.setAddress("manikpur");
//    	   

//    	   kishan.setName("kishan");
//    	   kishan.setEmail("dwivedikishan0@gmail.com");
//    	   kishan.setPassword("kish@12");
//    	   kishan.setPhone_no(234567891);
//    	   kishan.setAddress("manikpur");
    	   
    	   //Adding data
//    	   customer.addCustomer(shivam);
//    	   customer.addCustomer(kishan);
    	   //get customer by id 
    	   //customer.getCustomerById(01);
    	   //get all customer
//    	   customer.findAll();
    	   
    	   //update customer
    	   //customer.update("om", 02);
    	   
    	   //customer deleted
    	   //customer.delete(01);
    	   
    	   //--categories--
//    	   electronic.addCategoried(01, "Electronic",1);
//    	   electronic.selectProductbyCategories();
//    	   electronic.listCategories();
//    	   categories.listCategories();
    	   
//    	   groceries.addCategoried(02, "Grocery Item",2);
//    	   groceries.selectProductbyCategories();
//    	   categories.selectProductbyCategories();
//    	   <!--Products-->
//    	   product.addProduct("Mobile", 1, 10000, 100,"Electronic");
//    	   product.addProduct("Laptop", 1, 40000, 50,"Electronic");
//    	   product.addProduct("Tablets", 1, 40000, 100,"Electronic");
//    	   product.addProduct("Apple", 2, 200, 300,"Grocery");
//    	   product.addProduct("Mango", 2, 400, 50,"Grocery");
    	   
//    	   product.listProduct();
//    	   product.updateProduct(3, "Electronic");
//    	   product.updateProduct(1, "Electronic");
    	   
    	   //cart insertion (user_id,product_id,stock_quantity)
//    	   cd.addToCart(2, 3, 1);
    	   
	}
}

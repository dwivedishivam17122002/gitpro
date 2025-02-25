package ecom;

import java.sql.SQLException;
import java.util.Scanner;

public class cartService {
//	public static void main(String[] args) throws SQLException {
		
	
          public static void cartOpration(int userId) throws SQLException {
        	  Scanner sc = new Scanner(System.in);
				CartDao cartDao = new CartDao();
				  
				   
				   //Greet the user
				   cartDao.greetUser(userId);
				   
				   cartDao.displayUserCart(userId);
				   
				   while (true) {
				          System.out.println("\n===== Cart Services =====");
				          System.out.println("1. List Products");
				          System.out.println("2. Add Product to Cart");
				          System.out.println("3. View Cart");
				          System.out.println("4. Update Quantity Cart");
				          System.out.println("5. Delete Product from Cart");
				          System.out.println("6. Exit");
				          System.out.print("Choose an option: ");
				          int choice = sc.nextInt();
   
				          switch (choice) {
				              case 1:
				                  cartDao.listProducts();
				                  break;
   
				              case 2:
				                  System.out.print("Enter Product ID to add: ");
				                  int productId = sc.nextInt();
				                  System.out.print("Enter Quantity: ");
				                  int quantity = sc.nextInt();
   
				                  cartDao.addToCart(userId, productId, quantity);
				                  break;
   
				              case 3:
				            	  System.out.println(" View Cart");
				                  cartDao.displayUserCart(userId);
				                  break;
				              
				              case 4:
				            	  System.out.println(" Update Quanttity into cart");
				            	  System.out.println("Enter new updated quantity");
				            	  int newquantity = sc.nextInt();
				                  System.out.print("Enter Cart ID to add: ");
				                  int ncartId = sc.nextInt();
				            	  cartDao.updateCart(userId, newquantity,ncartId);
				            	  break;
				            	  
				              case 5:
				           	   System.out.println("Enter Product ID to delete from cart: ");
				           	   int deleteProductId = sc.nextInt();
				           	   System.out.println("Enter cart ID");
				           	   int cartId = sc.nextInt();
				                  cartDao.deleteFromCart(userId, deleteProductId,cartId);
				                  break;
				                  
				              case 6:
				                  System.out.println("Thank you for using the Cart App!");
//                          sc.close();
				                  return;
   
				              default:
				                  System.out.println("Invalid option. Please try again.");
				          }
				      }
			}
          }


package ecom;

import java.sql.SQLException;
import java.util.Scanner;

public class Menu {
	public void showMenu(String username,String password , String role) throws SQLException {
	    Scanner sc = new Scanner(System.in); 
			boolean loggedIn = true;
			int orderId=0;
			int userId = GetUserId.userId(username, role);
			ProductDao product = new ProductDao();
			CategoriesService cs = new CategoriesService();
//			System.out.println(" your user id "+userId);
			while (loggedIn) {
			    System.out.println("\nWelcome, " + username + "!");
			    System.out.println("1. View Products");
			    System.out.println("2. Categories Opration");
			    System.out.println("3. Cart opration");
			    System.out.println("4. Do Order");
			    System.out.println("5. Order detail");
//			    System.out.println("6. Give Rating To Product");
			    System.out.println("6. Update User");
			    System.out.println("7. Show Profile.");
			    System.out.println("8. Logout");
			    System.out.print("Choose an option: ");
			    
			    if (!sc.hasNextInt()) { 
			        System.out.println("❌ Invalid input. Please enter a  valid number.");
			        sc.next(); // Clear invalid input
			        continue;
			    }
			    int choice = sc.nextInt();

			    switch (choice) {
			        case 1:
			            System.out.println("📦 Displaying Products...");
			            int productId =product.listProduct();
			            System.out.println("Show review : ");
			            if(productId == 0) {
			            	System.out.println("Wrong Product Id");
			            }else {
			            	ReviewId.showReviewsByProduct(productId);
			            }
			            break;
			            
			        case 2:
			        	System.out.println("📂 Display Categories");
			        	cs.showCategoriescustomer();
			        	break;
			        	
			        case 3:
			            System.out.println("🛒 Cart Opration...");
			            cartService.cartOpration(userId);
			            break;
			            
			        case 4:
			        	System.out.println("🛍️ Placing Order...");
			        	orderId = DoOrderService.order(userId);
			        	break;
			        	
			        case 5:
			        	System.out.println("📂 Get Order Detail ");
//			        	   if (sc.hasNextInt()) {   
			                    if (orderId > 0) {
			                        OrdreDetailService.orderdetaifun(userId,orderId);
			                    } else {
			                        System.out.println("❌ No order found. Please place an order first.");
			                    }
//			                } else {
//			                    System.out.println("❌ Invalid input. Please enter a valid user ID.");
//			                    sc.next(); // Clear the invalid input
//			                }
			        	 break;
			        case 6:
			        	System.out.println("🔧 Update your detail");
			        	customerService.updateSpecificUser(username,password);
			        	break;
			        case 7:
			        	System.out.println("Show Profile.");
			        	CustomerDao.getCustomerById(userId);
			        	break;
			        case 8:
			            System.out.println("👋 Logging out... Goodbye, " + username + "!");
			            loggedIn = false;  // Exit the menu loop
			            break;
			            
			        default:
			        	
			            System.out.println("❌ Invalid Choice. Try again.");
			    }
			}
			
		}

	}


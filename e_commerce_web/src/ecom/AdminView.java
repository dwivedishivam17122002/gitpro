package ecom;

import java.sql.SQLException;
import java.util.Scanner;

public class AdminView {
          
	public static void adminMenu(String username,String password,String role) throws SQLException {
      
	    Scanner sc = new Scanner(System.in);
	    boolean loggedIn = true;
	    int orderId=0;
	    int userId = GetUserId.userId(username, role);
	  	if (role.equalsIgnoreCase("admin")) {
	  		System.out.println("This is Admin View");
            System.out.println("⚙️ Managing Users...");
        
        
	    while (loggedIn) {
	        System.out.println("\nWelcome, " + username + "!");
	        System.out.println("1. Customer opration");
	        System.out.println("2. Categories Opration");
	        System.out.println("3.  Products Opration");
	        System.out.println("4. Cart opration");
	        System.out.println("5. Do Order");
	        System.out.println("6. Order detail");      
	        System.out.println("7. Logout");
	        System.out.print("Choose an option: ");
	        int choice = sc.nextInt();

	        switch (choice) {
		        case 1:
		        	System.out.println("Customer oprations");
		        	customerService.customerOp(userId);
                     break;
	            case 2:
	            	System.out.println(" Categories Opration's");
	            	CategoriesService.showCategoriesAdmin();
	            	break;
	            	
	            case 3:
	                System.out.println("Product Opration...");
	                ProductService.AdminShowProducts();
	                break;
	                
	            case 4:
	            	System.out.println(" Cart opration ");
	            	cartService.cartOpration(userId);
	            	break;
	            	
	            case 5:
	            	System.out.println(" Make Order ");
	            	orderId = DoOrderService.order(userId);
	            	break;
	            	
	            case 6:
	            	System.out.println(" Get Order Detail ");
	            	 OrdreDetailService.orderdetaifun(orderId,userId);
	            	 break;
                    
	            case 7:
	                System.out.println("👋 Logging out... Goodbye, " + username + "!");
	                loggedIn = false;  // Exit the menu loop
	                break;
	                
	            default:
	            	
	                System.out.println("❌ Invalid Choice. Try again.");
	        }
	    }
	  	}
	  	 else {
	            System.out.println("❌ Invalid Option.");
	        }
//	    sc.close();
	
	}
}

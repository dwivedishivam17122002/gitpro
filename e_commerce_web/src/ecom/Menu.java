package ecom;

import java.util.Scanner;

public class Menu {
	public void showMenu(String username,String role) {
	    Scanner sc = new Scanner(System.in);
	    boolean loggedIn = true;

	    while (loggedIn) {
	        System.out.println("\nWelcome, " + username + "!");
	        System.out.println("1. View Products");
	        System.out.println("2. Add to Cart");
	        
            if (role.equalsIgnoreCase("admin")) { 
                System.out.println("3. Manage Users"); // Admin-only feature
            }
            
            
	        System.out.println("4. Logout");
	        System.out.print("Choose an option: ");
	        int choice = sc.nextInt();

	        switch (choice) {
	            case 1:
	                System.out.println("📦 Displaying Products...");
	                break;
	            case 2:
	                System.out.println("🛒 Adding to Cart...");
	                break;
	            case 3:
	            	if (role.equalsIgnoreCase("admin")) {
                        System.out.println("⚙️ Managing Users...");
                    } else {
                        System.out.println("❌ Invalid Option.");
                    }
                    break;
	            case 4:
	                System.out.println("👋 Logging out... Goodbye, " + username + "!");
	                loggedIn = false;  // Exit the menu loop
	                break;
	            default:
	                System.out.println("❌ Invalid Choice. Try again.");
	        }
	    }
	    sc.close();
	}
}

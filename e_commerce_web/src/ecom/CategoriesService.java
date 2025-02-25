package ecom;

import java.util.Scanner;

public class CategoriesService {
	 
         public static void showCategoriesAdmin() {
//try (//	public static void main(String args[]) {
			Scanner sc = new Scanner(System.in);
				CategoriesDao categories = new CategoriesDao();
				 System.out.println("📂 Categories Section! ");
				 
				 
//      	   electronic.addCategoried(01, "Electronic",1);
//      	   electronic.selectProductbyCategories();
//      	   electronic.listCategories();
//      	   categories.listCategories();
    	   
//      	   groceries.addCategoried(02, "Grocery Item",2);
//      	   groceries.selectProductbyCategories();
//      	   categories.selectProductbyCategories();
				 
				 
				 while(true) {
					 System.out.println("/n Select an option: ");
					 System.out.println("1 Add Categories");
					 System.out.println("2 List Categories");
					 System.out.println("3 Select Product By Categories");
					 System.out.println("4 Select Product of Specific Categories");
//        		 System.out.println("4 Delete Categories");
					 System.out.println("5 ️Exit");
					 System.out.println("👉 Enter choice");
//				     if (!sc.hasNextInt()) {
//				         System.out.println("❌ Invalid input! Please enter a valid number.");
//				         sc.next(); // Clear invalid input
//				         continue;
//				     }
					 int choice = sc.nextInt();
					 sc.nextLine();
					 switch(choice) {
					 case 1:
						 System.out.println("Add Categories");
						 System.out.println("Enter categories name");
						 String category =sc.nextLine();
						 categories.addCategoried(category);
						 break;
					 case 2:
						 System.out.println("📋 Show all categories: ");
						 categories.listCategories();
						 break;
					 case 3: 
						 System.out.println("🔍 Select all product by Category wise");
						 categories.selectProductbyCategories();
						 break;
					 case 4:
						 System.out.println("🔎 Select all product of specific categories");
						 System.out.println("Enter Category Id: ");
						 if (sc.hasNextInt()) {
						 int category_id = sc.nextInt();
						 categories.selectProductofspecificCategories(category_id);
						 } else {
				             System.out.println("❌ Invalid input! Please enter a valid category ID.");
				             sc.next(); // Clear invalid input
				         }
						 break;
					 case 5:
						 System.out.println("Exiting... Thank you!");
				        
				         return;
					 default:
				         System.out.println("Invalid choice, please try again.");
				         
					 }
				 }
			}

  
         
         
         public void showCategoriescustomer() {
//try (//        		public static void main(String args[]) {
						Scanner sc = new Scanner(System.in);
							CategoriesDao categories = new CategoriesDao();
							 System.out.println("Categories Section! ");
							 
							 
//        	      	   electronic.addCategoried(01, "Electronic",1);
//        	      	   electronic.selectProductbyCategories();
//        	      	   electronic.listCategories();
//        	      	   categories.listCategories();
   	      	   
//        	      	   groceries.addCategoried(02, "Grocery Item",2);
//        	      	   groceries.selectProductbyCategories();
//        	      	   categories.selectProductbyCategories();
							 
							 
							 while(true) {
								 System.out.println("/n Select an option: ");
								 System.out.println("1 List Categories");
								 System.out.println("2 Select Product By Categories");
								 System.out.println("3 Select Product of Specific Categories");
//        	        		 System.out.println("4 Delete Categories");
								 System.out.println("4 ️Exit");
								 System.out.println("👉 Enter choice");
//							     if (!sc.hasNextInt()) {
//							         System.out.println("❌ Invalid input! Please enter a valid number.");
//							         sc.next(); // Clear invalid input
//							         continue;
//							     }
								 int choice = sc.nextInt();
								 sc.nextLine();
								 switch(choice) {
								 case 1:
									 System.out.println("📋 Show all categories: ");
									 categories.listCategories();
									 break;
								 case 2: 
									 System.out.println("🔍 Select all product by Category wise");
									 categories.selectProductbyCategories();
									 break;
								 case 3:
									 System.out.println("🔎 Select all product of specific categories");
									 System.out.println("Enter Category Id: ");
									 if (sc.hasNextInt()) {
									 int category_id = sc.nextInt();
									 categories.selectProductofspecificCategories(category_id);
									 } else {
							             System.out.println("❌ Invalid input! Please enter a valid category ID.");
							             sc.next(); // Clear invalid input
							         }
									 break;
								 case 4:
									 System.out.println("Exiting... Thank you!");
							         return;
								 default:
							         System.out.println("Invalid choice, please try again.");
							         
								 }
							 }
						}
        	        	 
      
        	         }


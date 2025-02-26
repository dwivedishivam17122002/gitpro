package ecom;

import java.util.Scanner;

public class ProductService {
	
    public static void AdminShowProducts() {
//public static void main(String args[]) {
   	 Scanner sc = new Scanner(System.in);
   	ProductDao product = new ProductDao();
//   	 CategoriesDao categories = new CategoriesDao();
   	 System.out.println("Product Section! ");
   	 	   
//	   product.listProduct();
//	   product.updateProduct(3, "Electronic");
//	   product.updateProduct(1, "Electronic");
   	 
   	 while(true) {
   		 System.out.println("/n Select an option: ");
   		 System.out.println("1 Add Product");
   		 System.out.println("2 List of Products");
   		 System.out.println("3 Update Product");
   		 System.out.println("4 Delete Product");
//   		 System.out.println("4 Delete Categories");
   		 System.out.println("5 ️Exit");
   		 System.out.println("👉 Enter choice");
   		 int choice = sc.nextInt();
   		 
   		 switch(choice) {
   		 case 1:
   			 System.out.println("Add Product");
   			sc.nextLine();
   			 System.out.println("Enter Product name");
   			 String productName =sc.nextLine();
   			 
   			 System.out.println("Enter Product Price");
   			 float productPrice =sc.nextFloat();
   			 
   			System.out.println("Enter Product Quantity");
  			 int productQuantity =sc.nextInt();
  			 
   			System.out.println("Enter Category Id");
   			System.out.println("If you do'nt know categories Id Then first press 1 .. ohterwise enter categories Id");
   			int ncategoryId = sc.nextInt();
   			if(ncategoryId == 1) {
   				
   			}
  			 int categoryId =sc.nextInt();
  			 
  			sc.nextLine();
  			 System.out.println("Enter Categories name");
   			 String categoryName =sc.nextLine();
   			 
   			product.addProduct(productName, productPrice, productQuantity, categoryId,categoryName);
   			
   			 break;
   		 case 2:
   			 System.out.println("Show all Product: ");
   			   product.listProduct();
   			 break;
   		 case 3: 
   			 System.out.println("Update Product:");
   			 
   			System.out.println("Enter Product Id");
 			 int uproductId =sc.nextInt();
 			sc.nextLine();
   			 System.out.println("Enter updated Product name");
   			 String uproductName =sc.nextLine();
   			 
   			 System.out.println("Enter updated Product Price");
   			 float uproductPrice =sc.nextFloat();
   			 
   			System.out.println("Enter updated Product Quantity");
  			 int uproductQuantity =sc.nextInt();
  			 
   			System.out.println("Enter Category Id");
  			 int ucategoryId =sc.nextInt();
  			sc.nextLine();
  			 System.out.println("Enter Categories name");
   			 String ucategoryName =sc.nextLine();
   			 
   			 product.updateProduct(uproductId, uproductName, uproductPrice, uproductQuantity, ucategoryId, ucategoryName);
   			 break;
   		 case 4:
   			 System.out.println("Delete all product");
   			 System.out.println("Enter Product Id: ");
   			 int dproductId = sc.nextInt();
             product.deleteProduct(dproductId);
   			 break;
   		 case 5:
   			 System.out.println("Exiting... Thank you!");
//                sc.close();
                return;
   		 default:
                System.out.println("Invalid choice, please try again.");
                
   		 }
   	 }
   	 

    }
}

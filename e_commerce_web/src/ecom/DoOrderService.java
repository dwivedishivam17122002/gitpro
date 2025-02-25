package ecom;

import java.sql.SQLException;
import java.util.Scanner;

public class DoOrderService {
//	public static void main(String[] args) throws SQLException {
	public static int order(int userId) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        ProductDao product = new ProductDao();
        DoOrder od = new DoOrder();
        int Payment_method_id=0;
        String Payment_Method = null;

                        product.listProduct();
                        
				        System.out.print("Enter Product ID: ");
				        int productId = scanner.nextInt();
				        
				        System.out.print("Enter Quantity: ");
				        int quantity = scanner.nextInt();
				        scanner.nextLine();
		
                    System.out.print("Enter Address: ");
                    String address = scanner.nextLine();
		             

				      System.out.print("Enter Payment Method options: ");
				      System.out.println("/n");
				      System.out.println("1 Credit Card");
				      System.out.println("2 Debit Card");
				      System.out.println("3 UPI");
				      System.out.println("4 PayPal");
				      System.out.println("5 Net Banking");
				      System.out.println("6 Cash on Delivery");
				      
				       Payment_method_id = scanner.nextInt();	
		               
				       
	                    switch(Payment_method_id) {
	                    case 1:
	                    	System.out.println("Credit Card");
	                    	Payment_Method="Credit Card";
	                    	break;
	                    case 2:
	                    	System.out.println("Debit Card");
	                    	Payment_Method="Debit Card";
	                    	break;
	                    case 3:
	                    	System.out.println("UPI");
	                    	Payment_Method="UPI";
	                    	break;
	                    case 4:
	                    	System.out.println("PayPal");
	                    	Payment_Method="PayPal";
	                    	break;
	                    case 5:
	                    	System.out.println("Net Banking");
	                    	Payment_Method="Net Banking";
	                    	break;
	                    case 6:
	                    	System.out.println("Cash on Delivery");
	                    	Payment_Method ="Cash on Delivery";
	                    	break;
	                    default:
	                    	System.out.println("You are Put Wrong Entry! ");
	                    	break;
	                    }
	                    
	                    
                    int orderId = od.placeOrder(userId, productId, quantity, address, Payment_Method);
                    if (orderId != -1) {
                    	 System.out.println("Product Ordered Succesfully! " + orderId);
                    }else {
                    	System.out.println("Sorry somthing went worng : ");
                    }
                    
                   
                    
                  System.out.println("Exiting... Thank you!");
//                  scanner.close();
                  return orderId;

    }
}

package ecom;
import java.sql.SQLException;
import java.util.Scanner;

public class OrdreDetailService {
//     public static void main(String[] args) throws SQLException {
    	 public static void orderdetaifun(int userId,int orderId) throws SQLException {
    	 Scanner scanner = new Scanner(System.in);
    	 OrderDetail od = new OrderDetail();
  
    	 while(true) {
    		 System.out.println("/n Select an option: ");
    		 System.out.println("1 Show all order");
    		 System.out.println("2 Cancle Order");
    		 System.out.println("3 ️Exit");
    		 System.out.println("👉 Enter choice");
    		 int choice = scanner.nextInt();
    		 
    		 switch(choice) {
    		 case 1:
    			 System.out.println("Show all the order details");
    			 od.ShowOrder(userId);
    			 break;
    		 case 2:
    			 System.out.println("Cancle Order: ");
    			 od.cancelOrder(userId,orderId);
    			 break;
    		 case 3:
    			 System.out.println("Exiting... Thank you!");
//                 scanner.close();
                 return;
    		 default:
                 System.out.println("Invalid choice, please try again.");
    		 }
    	 }
	}
}

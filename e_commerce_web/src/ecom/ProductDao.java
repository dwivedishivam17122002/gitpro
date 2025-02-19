package ecom;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductDao {
//	product_id;
//	name;
//	category_id;
//	price;
//	stock_quantity;
         public void addProduct(String name,int category_id,float price,int stock_quantity,String category_name) {
        	  String query = "INSERT INTO products (name, category_id, price, stock_quantity, category_name) VALUES (?, ?, ?, ?, ?)";
        	  try(Connection con = ConnectionFactory.getInstance().getConnection();
          	    	PreparedStatement ptmt = con.prepareStatement(query)){
          	    	ptmt.setString(1, name);
          	    	ptmt.setInt(2, category_id);
          	    	ptmt.setFloat(3, price);
          	    	ptmt.setInt(4, stock_quantity);
          	    	ptmt.setString(5, category_name);
          	    	ptmt.executeUpdate();
          	    	System.out.println("Product added successfully");
          	    }catch(SQLException  e) {
          	    	e.printStackTrace();
          	    }
          }
         
         public void listProduct() {
       	  String query = "SELECT * FROM products";
       	    try(Connection con = ConnectionFactory.getInstance().getConnection();
       	    	PreparedStatement ptmt = con.prepareStatement(query);
       	    	ResultSet rs = ptmt.executeQuery()){
       	    	while(rs.next()) {
       	    		System.out.println("Product Id: "+rs.getInt("product_id"));
       	    		System.out.println("Product Name: "+rs.getString("name"));
       	    		System.out.println("Category Id: "+rs.getInt("category_id"));
       	    		System.out.println("Product price: "+rs.getFloat("price"));
       	    		System.out.println("Stock Quantity: "+rs.getInt("stock_quantity"));
       	    		System.out.println("Category Name: "+rs.getString("category_name"));
       	    		System.out.println("--------------------------------------------------");
       	    	}
       	    }catch(SQLException  e) {
       	    	e.printStackTrace();
       	    }
       	    
         }
         
         public void updateProduct(int productId,String category_name) {
       	  String query = "UPDATE products SET category_name = ? WHERE product_id = ?";
       	  try(Connection con = ConnectionFactory.getInstance().getConnection();
         	    	PreparedStatement ptmt = con.prepareStatement(query)){
         	    	ptmt.setString(1, category_name);
         	    	ptmt.setInt(2, productId);
         	    	ptmt.executeUpdate();
         	    	System.out.println("Product added successfully");
         	    }catch(SQLException  e) {
         	    	e.printStackTrace();
         	    }
         }
         
         public void deleteProduct(int productId) {
       	  String query = "DELETE FROM products WHERE id =?";
       	  try(Connection con = ConnectionFactory.getInstance().getConnection();
         	    	PreparedStatement ptmt = con.prepareStatement(query)){
         	    	ptmt.setInt(1, productId);
         	    	ptmt.executeUpdate();
         	    	System.out.println("Product added successfully");
         	    }catch(SQLException  e) {
         	    	e.printStackTrace();
         	    }
         }
}

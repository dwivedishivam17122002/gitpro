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
         public void addProduct(String name,float price,int stock_quantity ,int category_id ,String category_name) {
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
         
         public int listProduct() {
        	 int productId = 0;
       	  String query = "SELECT * FROM products";
       	    try(Connection con = ConnectionFactory.getInstance().getConnection();
       	    	PreparedStatement ptmt = con.prepareStatement(query);
       	    	ResultSet rs = ptmt.executeQuery()){
       	         productId = rs.getInt("product_id");
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
       	    return productId;
         }
         
         public void updateProduct(int productId,String productName,float productPrice,int productQuantity,int categoryId,String category_name) {
       	  String query = "UPDATE products SET name = ? , price = ? , stock_quantity = ? , category_id = ? , category_name = ? WHERE product_id = ?";
       	  try(Connection con = ConnectionFactory.getInstance().getConnection();
         	    	PreparedStatement ptmt = con.prepareStatement(query)){
         	    	ptmt.setString(1, productName);
         	    	ptmt.setFloat(2, productPrice);
         	    	ptmt.setInt(3, productQuantity);
         	    	ptmt.setInt(4, categoryId);
         	    	ptmt.setString(5, category_name);
         	    	ptmt.setInt(6, productId);
         	    	ptmt.executeUpdate();
         	    	System.out.println("Product updated successfully");
         	    }catch(SQLException  e) {
         	    	e.printStackTrace();
         	    }
         }
         
         public void deleteProduct(int productId) {
       	  String query = "DELETE FROM products WHERE product_id =?";
       	  try(Connection con = ConnectionFactory.getInstance().getConnection();
         	    	PreparedStatement ptmt = con.prepareStatement(query)){
         	    	ptmt.setInt(1, productId);
         	    	ptmt.executeUpdate();
         	    	System.out.println("Product deleted successfully");
         	    }catch(SQLException  e) {
         	    	e.printStackTrace();
         	    }
         }
}

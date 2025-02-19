package ecom;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CategoriesDao {
//	
//	ALTER TABLE categories
//	ADD constraint FK FOREIGN KEY (product_id)
//	REFERENCES products(product_id)
//	ON DELETE CASCADE
//	ON UPDATE CASCADE;
	
          public void addCategoried(int categoriesId,String categoriesName,int product_id) {
        	  String query = "INSERT INTO categories(categories_id,categories_name,product_id) VALUES (?,?,?)";
        	    try(Connection con = ConnectionFactory.getInstance().getConnection();
        	    	PreparedStatement ptmt = con.prepareStatement(query)){
        	    	ptmt.setInt(1, categoriesId);
        	    	ptmt.setString(2, categoriesName);
        	    	ptmt.setInt(3, product_id);
        	    	ptmt.executeUpdate();
        	    	System.out.println("Categories added successfully");
        	    }catch(SQLException  e) {
        	    	e.printStackTrace();
        	    }
          }
          
          public void listCategories() {
        	  String query = "SELECT * FROM categories";
        	    try(Connection con = ConnectionFactory.getInstance().getConnection();
        	    	PreparedStatement ptmt = con.prepareStatement(query);
        	    	ResultSet rs = ptmt.executeQuery()){
        	    	while(rs.next()) {
        	    		System.out.println("Categories id: "+rs.getInt("categories_id"));
        	    		System.out.println("Categories name: "+rs.getString("categories_name"));
        	    		System.out.println("Product id: "+rs.getInt("product_id"));
        	    		System.out.println("--------------------------------------------------");
        	    	}
        	    }catch(SQLException  e) {
        	    	e.printStackTrace();
        	    }
          }
          
          public void selectProductbyCategories() {
        	  String query = "SELECT p.name, p.price, c.categories_name FROM products p JOIN categories c ON p.category_id = c.categories_id";
        	    try(Connection con = ConnectionFactory.getInstance().getConnection();
        	    	PreparedStatement ptmt = con.prepareStatement(query);
        	    	ResultSet rs = ptmt.executeQuery()){
        	    	while(rs.next()) {
        	    		System.out.println("Product Name: "+rs.getString("name"));
        	    		System.out.println("Product price: "+rs.getBigDecimal("price"));
        	    		System.out.println("Category Name: "+rs.getString("categories_name"));
        	    		System.out.println("--------------------------------------------------");
        	    	}
        	    }catch(SQLException  e) {
        	    	e.printStackTrace();
        	    }
          }
}

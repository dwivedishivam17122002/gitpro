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
	
          public void addCategoried(String categoriesName) {
        	  String query = "INSERT INTO categories(categories_name) VALUES (?)";
        	    try(Connection con = ConnectionFactory.getInstance().getConnection();
        	    	PreparedStatement ptmt = con.prepareStatement(query)){
        	    	ptmt.setString(1, categoriesName);
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
        	    		System.out.println("--------------------------------------------------");
        	    	}
        	    }catch(SQLException  e) {
        	    	e.printStackTrace();
        	    }
          }
          
          public void selectProductbyCategories() {
        	  String query = "SELECT p.name, p.price, c.categories_name , c.categories_id FROM products p JOIN categories c ON p.category_id = c.categories_id";
        	    try(Connection con = ConnectionFactory.getInstance().getConnection();
        	    	PreparedStatement ptmt = con.prepareStatement(query);
        	    	ResultSet rs = ptmt.executeQuery()){
        	    	while(rs.next()) {
        	    		System.out.println("Product Name: "+rs.getString("name"));
        	    		System.out.println("Product price: "+rs.getBigDecimal("price"));
        	    		System.out.println("Category Name: "+rs.getString("categories_name"));
        	    		System.out.println("Category Id: "+rs.getString("categories_id"));
        	    		System.out.println("--------------------------------------------------");
        	    	}
        	    }catch(SQLException  e) {
        	    	e.printStackTrace();
        	    }
          }
          
          public void selectProductofspecificCategories(int category_id) {
        	  String query = "SELECT name, price, category_name FROM products where category_id = ?";
      	    Connection con = null;
    	    PreparedStatement ps = null;
    	    ResultSet rs = null;
        	    try{
        	    	con = ConnectionFactory.getInstance().getConnection();
        	    	ps = con.prepareStatement(query);
        	    	ps.setInt(1, category_id);
        	    	rs = ps.executeQuery();
        	    	
        	    	while(rs.next()) {
        	    		System.out.println("Product Name: "+rs.getString("name"));
        	    		System.out.println("Product price: "+rs.getBigDecimal("price"));
        	    		System.out.println("Category Name: "+rs.getString("category_name"));
        	    		System.out.println("--------------------------------------------------");
        	    	}
        	    }catch(SQLException  e) {
        	    	e.printStackTrace();
        	    }finally {
        	    	  try {
        		            if (rs != null) rs.close();
        		            if (ps != null) ps.close();
        		            if (con != null) con.close();
        		        } catch (SQLException e) {
        		            e.printStackTrace();
        		        }
        	    }
          }

}

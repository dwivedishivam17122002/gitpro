package ecom;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerDao {
        static Connection con=null;
        static PreparedStatement ptmt=null;
        static ResultSet rs=null;
        
        public CustomerDao() {
        	
        }
        
        private static Connection getConnection() throws SQLException{
        	Connection conn;
        	conn = ConnectionFactory.getInstance().getConnection();
        	return conn;
        }
        
//        int customer_id;
//        String name;
//        String email;
//        String password;
//        int phone_no;
//        String address;
        
        //add customer
        public void addCustomer(CustomerBean customerBean) {
        	try {
        		String query = "INSERT INTO customers (Name, email_id, password, phone_number, address) VALUES (?, ?, ?, ?, ?)";
        		con = getConnection();
        		ptmt = con.prepareStatement(query);
        		ptmt.setString(1, customerBean.getName());
        		ptmt.setString(2, customerBean.getEmail());
        		ptmt.setString(3, customerBean.getPassword());
        		ptmt.setString(4, customerBean.getPhone_no());
        		ptmt.setString(5, customerBean.getAddress());
        		
        		ptmt.executeUpdate();
        		System.out.println("Data added successfully");
        	}catch(SQLException e) {
        		e.printStackTrace();
        	}
        	finally {
        		try {
        			if (ptmt != null)
        				ptmt.close();
        				if (con != null)
        				con.close();
        				} catch (SQLException e) {
        					e.printStackTrace();
        				} catch (Exception e) {
        				e.printStackTrace();
        				}

        		}
        	}
        
        public void updateName(String name,int id) {
        	try {
        	con = getConnection();
        	String queryString = "UPDATE customers SET Name=? WHERE customer_id=?";
        	ptmt = con.prepareStatement(queryString);
        	ptmt.setString(1, name);
        	ptmt.setInt(2, id);
        	ptmt.executeUpdate();
        	System.out.println("Table Updated Successfully");
        	} catch (SQLException e) {
        	e.printStackTrace();
        	} finally {
        	try {
        	if (ptmt != null)
        	ptmt.close();
        	if (con != null)
        	con.close();
        	}
        	catch (SQLException e) {
        	e.printStackTrace();
        	} catch (Exception e) {
        	e.printStackTrace();
        	}
        	}
        	}
        
        public void updatePhoneNo(String Phone,int id) {
        	try {
        	con = getConnection();
        	String queryString = "UPDATE customers SET phone_number=? WHERE customer_id=?";
        	ptmt = con.prepareStatement(queryString);
        	ptmt.setString(1, Phone);
        	ptmt.setInt(2, id);
        	ptmt.executeUpdate();
        	System.out.println("Table Updated Successfully");
        	} catch (SQLException e) {
        	e.printStackTrace();
        	} finally {
        	try {
        	if (ptmt != null)
        	ptmt.close();
        	if (con != null)
        	con.close();
        	}
        	catch (SQLException e) {
        	e.printStackTrace();
        	} catch (Exception e) {
        	e.printStackTrace();
        	}
        	}
        	}
        
        public boolean updateEmail(String email,int id) {
        	 PreparedStatement checkStmt=null;
        	 ResultSet rs=null;
        	try {
        	con = getConnection();
            String checkQuery = "SELECT COUNT(*) FROM customers WHERE email_id = ?";
                   checkStmt = con.prepareStatement(checkQuery);
                	 checkStmt.setString(1, email);
                     rs = checkStmt.executeQuery();
                     rs.next();
            if (rs.getInt(1) > 0) {
                System.out.println("This email is  already registered!");
                System.out.println("Your email is duplicate!");
                return false;
            } else {
            	String queryString = "UPDATE customers SET email_id=? WHERE customer_id=?";
            	ptmt = con.prepareStatement(queryString);
            	ptmt.setString(1, email);
            	ptmt.setInt(2, id);
            	ptmt.executeUpdate();
            	
                }
        	
        	System.out.println("Table Updated Successfully");
        	return true;
        	} catch (SQLException e) {
        	e.printStackTrace();
        	} finally {
        	try {
        	if (ptmt != null)
        		ptmt.close();
        	if (con != null)
        	con.close();
        	if(checkStmt != null)
        		checkStmt.close();
        	if(rs != null)
        		rs.close();
        	}
        	catch (SQLException e) {
        	e.printStackTrace();
        	} catch (Exception e) {
        	e.printStackTrace();
        	}
        	}
			return true;
        	}
        
        public void updateAddress(String address,int id) {
        	try {
        	con = getConnection();
        	String queryString = "UPDATE customers SET address=? WHERE customer_id=?";
        	ptmt = con.prepareStatement(queryString);
        	ptmt.setString(1, address);
        	ptmt.setInt(2, id);
        	ptmt.executeUpdate();
        	System.out.println("Table Updated Successfully");
        	} catch (SQLException e) {
        	e.printStackTrace();
        	} finally {
        	try {
        	if (ptmt != null)
        	ptmt.close();
        	if (con != null)
        	con.close();
        	}
        	catch (SQLException e) {
        	e.printStackTrace();
        	} catch (Exception e) {
        	e.printStackTrace();
        	}
        	}
        	}
          
        public void changePassword(String password,int id) {
        	try {
        	con = getConnection();
        	String queryString = "UPDATE customers SET password=? WHERE customer_id=?";
        	ptmt = con.prepareStatement(queryString);
        	ptmt.setString(1, password);
        	ptmt.setInt(2, id);
        	ptmt.executeUpdate();
        	System.out.println("Table Updated Successfully");
        	} catch (SQLException e) {
        	e.printStackTrace();
        	} finally {
        	try {
        	if (ptmt != null)
        	ptmt.close();
        	if (con != null)
        	con.close();
        	}
        	catch (SQLException e) {
        	e.printStackTrace();
        	} catch (Exception e) {
        	e.printStackTrace();
        	}
        	}
        	}
        
        
        public void delete(int customer_id) {
        	try {
        	String queryString = "DELETE FROM customers WHERE customer_id=?";
        	con = getConnection();
        	ptmt = con.prepareStatement(queryString);
        	ptmt.setInt(1, customer_id);
        	ptmt.executeUpdate();
        	System.out.println("Data deleted Successfully");
        	} catch (SQLException e) {
        	e.printStackTrace();
        	} finally {
        	try {
        	if (ptmt != null)
        	ptmt.close();
        	if (con != null)
        		con.close();
        	} catch (SQLException e) {
        	e.printStackTrace();
        	} catch (Exception e) {
        	e.printStackTrace();
        	}
        	}
        	}
        
        public void findAll() {
        	String query = "SELECT * FROM customers";
        	try{
        		con = getConnection();
        		ptmt = con.prepareStatement(query);
        		rs = ptmt.executeQuery();
//                String name;
//                String email;
//                String password;
//                int phone_no;
//                String address;
        		while(rs.next()) {
        			System.out.println("Customer ID: " + rs.getInt("customer_id"));
                    System.out.println("Name: " + rs.getString("name"));
                    System.out.println("Email: " + rs.getString("email_id"));
                    System.out.println("Phone: " + rs.getString("phone_number"));
                    System.out.println("Address: " + rs.getString("address"));
                    System.out.println("Created At: " + rs.getTimestamp("created_at"));
                    System.out.println("Updated At:- " + rs.getTimestamp("updated_at"));
                    System.out.println("-------------------------------------------------------------");
        		}
        	}catch(SQLException e) {
        		e.printStackTrace();
        	}finally {
        		try {
        			if (rs != null)
        				rs.close();
        			if (ptmt != null)
        			ptmt.close();
        			if (con != null)
        			con.close();
        			} catch (SQLException e) {
        			e.printStackTrace();
        			} catch (Exception e) {
        			e.printStackTrace();
        			}
        	}
        }
        
        public static void getCustomerById(int id) {
        	String query = "SELECT * FROM customers WHERE customer_id = ?";
        	try{
        		con = getConnection();
        		ptmt = con.prepareStatement(query);
        		ptmt.setInt(1, id);
        		rs = ptmt.executeQuery();
        		
//                String name;
//                String email;
//                String password;
//                int phone_no;
//                String address;
        		if(rs.next()) {
                    System.out.println("Customer ID: " + rs.getInt("customer_id"));
                    System.out.println("Name: " + rs.getString("name"));
                    System.out.println("Email: " + rs.getString("email_id"));
                    System.out.println("Phone: " + rs.getString("phone_number"));
                    System.out.println("Address: " + rs.getString("address"));
                    System.out.println("Created At: " + rs.getTimestamp("created_at"));
                    System.out.println("Updated At:- " + rs.getTimestamp("updated_at"));
                    System.out.println("-------------------------------------------------------------");
        		} else {
                    System.out.println("Customer not found!");
                }
        	}catch(SQLException e) {
        		e.printStackTrace();
        	}finally {
        		try {
        			if (rs != null)
        				rs.close();
        			if (ptmt != null)
        			ptmt.close();
        			if (con != null)
        			con.close();
        			} catch (SQLException e) {
        			e.printStackTrace();
        			} catch (Exception e) {
        			e.printStackTrace();
        			}
        	}
        }
        
        
        }


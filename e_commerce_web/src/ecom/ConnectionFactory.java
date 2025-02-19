package ecom;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    
        	 String driverclass = "com.mysql.cj.jdbc.Driver";
        	 String connectionUrl = "jdbc:mysql://localhost:3306/ecom_managment";
        	 String user = "root";
        	 String pwd = "Shivam@1976";
        	 
        	 private static ConnectionFactory connectionFactory = null;
        	 
        	 private ConnectionFactory() {
        		 try {
					Class.forName(driverclass);
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        	 }
        	 
        	 public Connection getConnection() throws SQLException {
        		 Connection conn = null;
        		 
        	     conn = DriverManager.getConnection(connectionUrl,user,pwd);
        	     return conn;
		}
        	 
            public static ConnectionFactory getInstance() {
            	if(connectionFactory == null) {
            		connectionFactory = new ConnectionFactory();
            	}
            	return connectionFactory;
            }
	
}

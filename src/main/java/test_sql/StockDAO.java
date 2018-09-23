package test_sql;

import java.io.File;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class StockDAO {
	String fileName;
	//Connection string. 
	String url;
	
	public StockDAO(String fileName) {
		this.fileName = fileName;
		url = "jdbc:sqlite:C:/dbs/" + fileName + ".db";
	}
	
    private Connection connect() {  
        Connection conn = null;  
        try {  
            conn = DriverManager.getConnection(url);  
        } catch (SQLException e) {  
            System.out.println(e.getMessage());  
        }  
        return conn;  
    }  

	//Adds .db to the end of file name. Checks if file name already exists. If not, creates a new database. 
	public void createNewDatabase() {
        File db = new File("C:/dbs/" + fileName + ".db");
        
        if (!db.exists()) {
	        try (Connection conn = DriverManager.getConnection(url)) {
	            if (conn != null) {
	                DatabaseMetaData meta = conn.getMetaData();
	                System.out.println("The driver name is " + meta.getDriverName());
	                System.out.println("A new database has been created.");
	            }
	            else {
	            	System.out.println("Database connection may be null. ");
	            }
	        } catch (SQLException e) {
	            System.out.println(e.getMessage());
	        }
        } else {
        	System.out.println("Database " + url + " already exists. ");
        }

    }
	
    public void createTodaysTable() {   
        // SQL statement for creating a new table  
        String sql = "CREATE TABLE IF NOT EXISTS employees (\n"  
                + " id integer PRIMARY KEY,\n"  
                + " name text NOT NULL,\n"  
                + " capacity real\n"  
                + ");";  
          
        try{  
            Connection conn = DriverManager.getConnection(url);  
            Statement stmt = conn.createStatement();  
            stmt.execute(sql);  
        } catch (SQLException e) {
            System.out.println(e.getMessage());  
        }  
    }
    
    public void insert(String name, double capacity) {  
        String sql = "INSERT INTO employees(name, capacity) VALUES(?,?)";  
   
        try{  
            Connection conn = this.connect();  
            PreparedStatement pstmt = conn.prepareStatement(sql);  
            pstmt.setString(1, name);  
            pstmt.setDouble(2, capacity);  
            pstmt.executeUpdate();  
        } catch (SQLException e) {  
            System.out.println(e.getMessage());  
        }  
    }  

}

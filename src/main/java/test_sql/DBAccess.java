package test_sql;

import java.io.File;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBAccess {

	//Adds .db to the end of file name. Checks if file name already exists. If not, creates a new database. 
	public void createNewDatabase(String fileName) {
        String url = "jdbc:sqlite:C:/dbs/" + fileName + ".db";
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

}

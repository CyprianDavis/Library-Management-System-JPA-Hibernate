package database.conn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Handles Database connections
 * @author CYPRIAN DAVIS
 *
 */
public class DatabaseConn {
	private static Connection conn = null; //Connection object
	
	
	static {
		String connString ="jdbc:sqlserver://CYPRIAN-DAVIS\\CYPRIANMSSQL;Database=Library_Management_System;IntegratedSecurity=true";
		try {
			conn = DriverManager.getConnection(connString);
		}
		catch(SQLException e) {
			
			e.printStackTrace();
		}
	}
	//Returns the database connection object
	public static Connection getConnection() {
		System.out.println("Connection Successful ....");
		return conn;
		
	}
	
	}




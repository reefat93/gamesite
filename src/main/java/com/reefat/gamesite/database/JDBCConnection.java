package com.reefat.gamesite.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCConnection {

	public Connection getConnnection() {
		
		Connection connection = null; //A connection object with particular properties is instantiated
	
		try {
		 String connectionURL = "jdbc:mysql://localhost:3306/gamesite_db"; //connection URL for mySQL server
		 Class.forName("com.mysql.jdbc.Driver").newInstance(); //Creation of a JDBC driver
		 connection = DriverManager.getConnection(connectionURL, "root", "root"); //Details given to START connection for Driver 
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
		 // TODO Auto-generated catch block
		 e.getLocalizedMessage();
		}
		
		return connection; //this getConnection method returns a (connected) Connection object and connects to mySQL in the process.
	}

}

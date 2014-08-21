package net.michael.mywebproject;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class MyModel {
	private Connection con;
	
	public MyModel () {
		try {
			 //Accessing driver from the JAR file
			 Class.forName("com.mysql.jdbc.Driver");
	         con =
			 DriverManager.getConnection("jdbc:mysql://localhost:3306/mywebproject","root","root");
		} catch (Exception e) {
			 System.out.println("Error in SQL at constructor method!");
			 System.err.println(e.getMessage());
			 e.printStackTrace();
		}
		
	}
	public void addRec(String userName, String passWord) {
		try {
		// To insert new record into table
	         String insert = "INSERT INTO user_login (username, password) VALUES ('" + userName + "', '" + passWord + "')";
	         Statement stmt = (Statement) con.createStatement();
	         stmt.executeUpdate(insert);
		} catch (Exception e) {
			 System.out.println("Error in SQL at addRec method!");
			 System.err.println(e.getMessage());
			 e.printStackTrace();
		}

	}
	
	public void showRecs(PrintWriter out) {
	         // To show all records in table
			 PreparedStatement statement;
		try {
			 statement = con.prepareStatement("select * from user_login");
			 ResultSet result = statement.executeQuery();
			 
		     while (result.next()) {
		 		 out.write(result.getString(1) + " " + result.getString(2) + " " + result.getString(3));
		 		 out.write("\n");
		 	 }
		} catch (SQLException e) {
			 System.out.println("Error in SQL at showRecs method!");
			 System.err.println(e.getMessage());
             e.printStackTrace();
		}
		
	}
}

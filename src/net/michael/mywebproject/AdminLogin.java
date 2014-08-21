package net.michael.mywebproject;

import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class AdminLogin extends Login {
	
	public AdminLogin(String userName, String passWord) {
		super(userName,passWord,1);
	}

	public ResultSet displayAllUsers() {
		try {
			String stmt = "Select * from user_master"; 
	        PreparedStatement ps=con.prepareStatement(stmt);
			ResultSet rs=ps.executeQuery();
			rs=ps.executeQuery();
			return rs;	
  
		} catch (Exception e) {
			System.out.println("Exception at AdminLogin class displayAllUsers method!");
			System.err.println(e.getMessage());
			e.printStackTrace();
			return null;
		}
	}

	public boolean deleteUser(String dUser) {
		try {
			// check if user to be deleted is existing
			String stmt="Select * from user_master where username=?";
	        PreparedStatement ps=con.prepareStatement(stmt);
			ps.setString(1, dUser);
	        ResultSet rs=ps.executeQuery();
			rs=ps.executeQuery();
			// if user exists, delete from table
			if (rs.next()) {
				stmt = "Delete from user_master where username=?"; 
		        ps=con.prepareStatement(stmt);
		        ps.setString(1, dUser);
				ps.executeUpdate();
				return true;	
			}
			else { 
				// username does not exists
				return false;
			}	

		} catch (Exception e) {
			System.out.println("Exception at AdminLogin class deleteUser method!");
			System.err.println(e.getMessage());
			e.printStackTrace();
			return false;
		}
	}
	
	
}

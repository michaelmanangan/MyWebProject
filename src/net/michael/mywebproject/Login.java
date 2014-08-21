package net.michael.mywebproject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Login {
	public static Connection con;
	private String userName;
	private String hashPword;
	private int role;
	

	public Login (String userName, String hashPword) {
		try {

       		
			this.userName = userName;
			this.hashPword = hashPword;
			this.role     = 0;

			if (con == null) {
				//SQL Accessing driver from the JAR file
				Class.forName("com.mysql.jdbc.Driver");
				con = 
				DriverManager.getConnection("jdbc:mysql://localhost:3306/mywebproject","root","root");
			//MS-Access	
			//	Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			//     con=DriverManager.getConnection("jdbc:odbc:mydsn1");
			} 

		} catch (Exception e) {
			 System.out.println("Error at Login class constructor method!");
			 System.err.println(e.getMessage());
			 e.printStackTrace();
		}
		
	}
	
	public Login (String userName, String hashPword, int role) {
		this(userName,hashPword);
		this.role = role;
	}

	public int getRole() {
		return role;
	}
	
	public String getUserName() {
		return userName;
	}


	public boolean accountExist() {
		try {
		// Returns true if record exists in table otherwise false
	         String stmt = "Select * from user_master where username =? and password =?"; 
	         PreparedStatement ps=con.prepareStatement(stmt);
	         ps.setString(1, userName);
			 ps.setString(2, hashPword);
			 ResultSet rs=ps.executeQuery();
			 rs=ps.executeQuery();
			 if (rs.next()) {
				 role=rs.getInt(3);
				 return true;
			 }
			 else {
				 return false;
			 }

		} catch (Exception e) {
			 System.out.println("Error at Login class accountExist method!");
			 System.err.println(e.getMessage());
			 e.printStackTrace();
			 return false;
		}
	}

	public Login addAccount(int role) {
	// Returns null if account already exists otherwise returns the newly created (admin/user) 
    // login object
		try {
			PreparedStatement ps=con.prepareStatement("Select * from user_master where username =?");
			ps.setString(1, userName);
			ResultSet rs=ps.executeQuery();
			if (rs.next()) {
				System.out.println("Login class: addAccount : Username " + userName + " already exists!");
				return null;
			}
			else {
				String query="insert into user_master values ('"+userName+"', '"+hashPword+"',"+role+")";
				ps=con.prepareStatement(query);
				ps.executeUpdate();
				if (role == 1) {
					return new AdminLogin(userName, hashPword);
				}
				else {
					return new UserLogin(userName, hashPword);
				}
			}
		} catch (Exception e) {
			 System.out.println("Error at Login class addAccount method!");
			 System.err.println(e.getMessage());
			 e.printStackTrace();
			 return null;
		}
	}
	
	public ResultSet displayUser(String viewuser) {
		try {
			String stmt = "Select * from user_master where username =?"; 
	        PreparedStatement ps=con.prepareStatement(stmt);
			ps.setString(1, viewuser);
	        ResultSet rs=ps.executeQuery();
			rs=ps.executeQuery();
			return rs;	
  
		} catch (Exception e) {
			System.out.println("Exception at Login class displayUser method!");
			System.err.println(e.getMessage());
			e.printStackTrace();
			return null;
		}
	}

	public boolean changePassword(String user, String npassword) {
		try {
		  ResultSet rs = this.displayUser(user);
		  if (rs.next()) {
			  String stmt="Update user_master set password=? where username=?";
	          PreparedStatement ps=con.prepareStatement(stmt);
			  ps.setString(1, npassword);
			  ps.setString(2, user);
			  ps.executeUpdate();
			  return true;	
		  } else {
			  // username does not exist
			  return false;
		  }

		} catch (Exception e) {
			System.out.println("Exception at Login class changePassword method!");
			System.err.println(e.getMessage());
			e.printStackTrace();
			return false;
		}
	}
	public boolean changeProf(String user, int newRole) {
		try {
	        ResultSet rs=this.displayUser(user);
	        if (rs.next()) {
	          // if user exists, proceed to edit the record in the table
			  String stmt="Update user_master set role=? where username=?";
	          PreparedStatement ps=con.prepareStatement(stmt);
			  ps.setString(1, Integer.toString(newRole));
			  ps.setString(2, user);
			  ps.executeUpdate();
			  return true;	
	        } else {
	          // user does not exist	
	          return false;	
	        }
		} catch (Exception e) {
			System.out.println("Exception at Login class changeProf method!");
			System.err.println(e.getMessage());
			e.printStackTrace();
			return false;
		}
	}
	
	public void Logout() {
		this.userName = null;
		this.hashPword = null;
		this.role     = 0;
		con = null;
	}

}

package com.ravi.springboot.Repository;

import java.sql.*;

public class UserRepository {

	private static String url = "jdbc:mysql://localhost:3306/tasktrackerdb?autoReconnect=true&useSSL=false";
	private static String username = "root";
	private static String password = "root";
	
	public static void dbconnection() 
	{
//		try {
//			Class.forName("com.mysql.jdbc.Driver");
//		} catch (ClassNotFoundException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
		System.out.println("Connecting database...");

		try (Connection connection = DriverManager.getConnection(url, username, password)) {
		    System.out.println("Database connected!");
		    Statement stmt = connection.createStatement();
		    ResultSet rs = stmt.executeQuery("select * from user");
		    while (rs.next()) {
		    	System.out.println(rs.getString("firstName"));
		    }
		} catch (SQLException e) {
		    throw new IllegalStateException("Cannot connect the database!", e);
		}
	}
}

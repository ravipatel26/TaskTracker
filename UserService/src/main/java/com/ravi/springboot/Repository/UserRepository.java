package com.ravi.springboot.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.ravi.springboot.Model.User;

public class UserRepository {

	private static String url = "jdbc:mysql://localhost:3306/tasktrackerdb?autoReconnect=true&useSSL=false";
	private static String username = "root";
	private static String password = "root";
	
	public static List<User> executeQuery(String query) 
	{
		System.out.println("Connecting database...");

		try (Connection connection = DriverManager.getConnection(url, username, password)) {
			
		    System.out.println("Database connected!");
		    Statement statement = connection.createStatement();
		    ResultSet resultSet = statement.executeQuery(query);
		    
		    return convertResultSetToUserList(resultSet);
		} catch (SQLException e) {
		    throw new IllegalStateException("Cannot connect the database!", e);
		}
	}
	
	private static List<User> convertResultSetToUserList(ResultSet rs) {
		List<User> users = new ArrayList<User>();
		
		if (rs == null) {
			return users;
		}
		
		try {
			while (rs.next()) {
				users.add(new User(rs.getInt("id"), rs.getString("firstName"),
								   rs.getString("lastName"), rs.getString("username"),
								   rs.getString("password"), rs.getDate("dateOfBirth")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return users;
	}
}

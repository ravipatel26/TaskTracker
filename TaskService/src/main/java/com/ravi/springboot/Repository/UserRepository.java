package com.ravi.springboot.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.ravi.springboot.Model.Task;

public class UserRepository {

	private String url = "jdbc:mysql://localhost:3306/tasktrackerdb?autoReconnect=true&useSSL=false";
	private String username = "root";
	private String password = "root";
	
	public List<Task> executeRetrieveQuery(String query) 
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
	
	public int executeUpdateQuery(String query) 
	{
		System.out.println("Connecting database...");
		try (Connection connection = DriverManager.getConnection(url, username, password)) {
		    System.out.println("Database connected!");
		    Statement statement = connection.createStatement();
		    int result = statement.executeUpdate(query);
		    return result;
		} catch (SQLException e) {
		    throw new IllegalStateException("Cannot connect the database!", e);
		}
	}
	
	private static List<Task> convertResultSetToUserList(ResultSet rs) {
		List<Task> users = new ArrayList<Task>();
		
		if (rs == null)
			return users;
		
		try {
			while (rs.next()) {
				users.add(new Task(rs.getInt("id"), rs.getString("taskDescription"),
								   rs.getString("status"), rs.getInt("userId")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return users;
	}
}

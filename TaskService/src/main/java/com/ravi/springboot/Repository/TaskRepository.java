package com.ravi.springboot.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.ravi.springboot.Model.Task;

public class TaskRepository {

	private String url = "jdbc:mysql://localhost:3306/tasktrackerdb?autoReconnect=true&useSSL=false";
	private String username = "root";
	private String password = "root";
	
	public List<Task> executeRetrieveQuery(String query) 
	{
		try (Connection connection = DriverManager.getConnection(url, username, password)) {
		    Statement statement = connection.createStatement();
		    ResultSet resultSet = statement.executeQuery(query);
		    return convertResultSetToTaskList(resultSet);
		} catch (SQLException e) {
		    throw new IllegalStateException("Cannot connect the database!", e);
		}
	}
	
	public int executeUpdateQuery(String query) 
	{
		try (Connection connection = DriverManager.getConnection(url, username, password)) {
		    Statement statement = connection.createStatement();
		    int result = statement.executeUpdate(query);
		    return result;
		} catch (SQLException e) {
		    throw new IllegalStateException("Cannot connect the database!", e);
		}
	}
	
	private static List<Task> convertResultSetToTaskList(ResultSet rs) {
		List<Task> tasks = new ArrayList<Task>();
		
		if (rs == null)
			return tasks;
		
		try {
			while (rs.next()) {
				tasks.add(new Task(rs.getInt("id"), rs.getString("taskDescription"),
								   rs.getString("taskStatus"), rs.getInt("userId")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return tasks;
	}
}

package com.ravi.springboot.Model;

public class Task {

	private int id;
	private int userId;
	private String taskDescription;
	private String status;

    public Task() {}

	public Task(int id, String taskDescription, String status, int userId) {
		this.id = id;
		this.taskDescription = taskDescription;
		this.status = status;
		this.userId = userId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getTaskDescription() {
		return taskDescription;
	}

	public void setTaskDescription(String taskDescription) {
		this.taskDescription = taskDescription;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}

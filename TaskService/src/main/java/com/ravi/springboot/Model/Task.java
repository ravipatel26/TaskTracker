package com.ravi.springboot.Model;

public class Task {

	private int id;
	private int userId;
	private String description;
	private String status;

    public Task() {}

	public Task(int id, String taskDescription, String status, int userId) {
		this.id = id;
		this.description = taskDescription;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String taskDescription) {
		this.description = taskDescription;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null || !(obj instanceof Task))
            return false; 

		Task task = (Task) obj;
		return this.id == task.id &&
			   this.description.equals(task.description) &&
			   this.status.equals(task.status) &&
			   this.userId == task.userId;
	}
}

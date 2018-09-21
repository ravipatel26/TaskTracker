package com.ravi.springboot.Model;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

public class User {

	private int id;
	private String firstName;
	private String lastName;
	private String username;
	private String password;
    @JsonFormat(pattern="yyyy-MM-dd")
	private Date dateOfBirth;

    public User() {}

	public User(int id, String firstName, String lastName, String username, String password, Date dateOfBirth) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
		this.dateOfBirth = dateOfBirth;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null || !(obj instanceof User))
            return false; 

		User user = (User) obj;
		return this.id == user.id &&
			   this.firstName.equals(user.firstName) &&
			   this.lastName.equals(user.lastName) &&
			   this.username.equals(user.username) &&
			   this.password.equals(user.password) &&
			   this.dateOfBirth.equals(user.dateOfBirth);
	}
}

package com.techm.ms.model.swagger;

import com.techm.ms.model.User;

public class UserResponse {
	
	private Status status;
	private User user;

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}	
}

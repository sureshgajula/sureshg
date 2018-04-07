package com.techm.ms.service;


import com.techm.ms.model.User;
import com.techm.ms.model.swagger.UserResponse;


public interface UserService {
	
	public UserResponse createUser(User user);	
	public UserResponse getUserById(Long userId);	

}

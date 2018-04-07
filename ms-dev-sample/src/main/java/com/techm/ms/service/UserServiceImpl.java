package com.techm.ms.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.techm.ms.model.User;
import com.techm.ms.model.swagger.Status;
import com.techm.ms.model.swagger.UserResponse;

@Service
public class UserServiceImpl implements UserService {
	
	static Map<Long, User> userMap = new HashMap<>();

	@Override
	public UserResponse createUser(User user) {
		UserResponse userResponse = new UserResponse();
		Status status = new Status();
		if(user != null) {
			if(userMap.containsKey(user.getId())) {
				User existingUser = userMap.get(user.getId());	
				if(existingUser.getName().equals(user.getName())) {
					status.setStatusCode(HttpStatus.CONFLICT.value());
					status.setStatusDesc("Unable to create. A Acoount with the same already exist");
				}
			} else {
				if(user.getId() != 0) {
					createUserBean(user);
					status.setStatusCode(HttpStatus.OK.value());
					status.setStatusDesc("Created User Succesfully.");	
					userResponse.setStatus(status);
				} else {
					status.setStatusCode(HttpStatus.BAD_REQUEST.value());
					status.setStatusDesc("Please enter valid user Id.");	
					userResponse.setStatus(status);
				}
				
			}			
		}
		return userResponse;
		
	}
	
	private void createUserBean(User user) {
		User userObj = new User();
		userObj.setAccountId(user.getAccountId());		
		userObj.setId(user.getId());
		userObj.setName(user.getName());
		userObj.setAge(36);		
		userMap.put(user.getId(), userObj);		
	}


	@Override
	public UserResponse getUserById(Long userId) {
		UserResponse usrResp = null;
		if(userId != 0) {
			User user = userMap.get(userId);	
			usrResp = new UserResponse();
			Status status = new Status();
			if(user!= null) {
				status.setStatusCode(HttpStatus.OK.value());
				status.setStatusDesc("User Deatils Retrieved Succesfully for . " + userId);	
				usrResp.setUser(user);
			} else {
				status.setStatusCode(HttpStatus.NOT_FOUND.value());
				status.setStatusDesc("User Deatils not found for this id . " + userId);	
				usrResp.setUser(user);
			}
		}
		
		return usrResp;
	}

}

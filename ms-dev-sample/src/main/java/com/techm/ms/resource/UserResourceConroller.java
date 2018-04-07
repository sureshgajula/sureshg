package com.techm.ms.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.techm.ms.model.User;
import com.techm.ms.model.swagger.UserResponse;
import com.techm.ms.service.UserService;

@RestController
public class UserResourceConroller {
	
	
	@Autowired
	UserService userService;
	
	
	@PostMapping("/createUser")
	public UserResponse createUser(@RequestBody User user){
		UserResponse userResp = userService.createUser(user);
		return userResp ;
	}
	
	@GetMapping("/getUser/userId")
	public UserResponse createUser(@RequestParam(value = "user_id") Long userId){
		UserResponse userResponse  = null;
		try {
			userResponse = userService.getUserById(userId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return userResponse;	
	}
}

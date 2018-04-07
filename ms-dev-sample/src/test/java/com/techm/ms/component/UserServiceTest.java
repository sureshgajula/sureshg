package com.techm.ms.component;



import static org.junit.Assert.*;

import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.stubbing.OngoingStubbing;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.techm.ms.model.User;
import com.techm.ms.model.swagger.UserResponse;
import com.techm.ms.service.UserService;


public class UserServiceTest {
	
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private UserService userService;	

	String exampleUserJson = "{\r\n" + 
			"	\"name\" : \"John Smith\",\r\n" + 
			"	\"id\" : \"abc\",\r\n" + 
			"	\"accountId\" : \"1234\",\r\n" + 
			"	\"age\" : 36\r\n" + 
			"}";

	@Test
	public void retrieveDetailsForUser() throws Exception {
		User mockuser = new User(1, "Suresh",35, 1234);
		UserResponse userresponse = new UserResponse();
		userresponse.setUser(mockuser);

		Mockito.when(((OngoingStubbing) userService.getUserById(Mockito.anyLong())).thenReturn(userresponse));

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"/getUser/userid?user_id=1").accept(
				MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		System.out.println(result.getResponse());
		String expected = "{id:1,name:Suresh,age:35, account:1234}";


		JSONAssert.assertEquals(expected, result.getResponse()
				.getContentAsString(), false);
	}
	
	@Test
	public void createUser() throws Exception {
		User mockuser = new User(1, "Suresh",35, 1234);
		UserResponse userresponse = new UserResponse();
		userresponse.setUser(mockuser);
		Mockito.when(
				userService.createUser(Mockito.any(User.class))).thenReturn(userresponse);

		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/createUser")
				.accept(MediaType.APPLICATION_JSON).content(exampleUserJson)
				.contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();

		assertEquals(HttpStatus.CREATED.value(), response.getStatus());

		assertEquals("http://localhost/8080/createUser",
				response.getClass());

	}

}

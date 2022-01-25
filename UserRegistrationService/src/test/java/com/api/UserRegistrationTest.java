package com.api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.api.model.UserModel;
import com.api.model.UserResponseModel;

class UserRegistrationTest extends AbstractTest {

	@Override
	@BeforeEach
	public void setUp() {
		super.setUp();
	}

	@Test
	public void userRegistrationControllerTest() throws Exception {

		String uri = "/api/user/registration";

		UserModel model = new UserModel();
		model.setUsername("");
		model.setPassword("Saichand1994");
		model.setIpAddress("24.48.0.1");

		String inputJson = super.mapToJson(model);

		MvcResult mvcResult = mvc
				.perform(MockMvcRequestBuilders.post(uri)
						.contentType(MediaType.APPLICATION_JSON_VALUE)
						.content(inputJson))
						.andReturn();

		int status = mvcResult.getResponse().getStatus();
		String content = mvcResult.getResponse().getContentAsString();
		System.out.println(content);
		
		UserResponseModel response = super.mapFromJson(content, UserResponseModel.class);
		

		assertEquals(200, status);

		assertTrue(response != null);
		System.out.println(response.getMessage());
		assertTrue(response.getUuid() != null);
		assertEquals("Canada", response.getCountry());
	}

}

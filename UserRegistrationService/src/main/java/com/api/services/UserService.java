package com.api.services;

import java.util.Arrays;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import com.api.model.GeoLocationModel;
import com.api.model.UserModel;
import com.api.model.UserResponseModel;
import com.api.util.PasswordConstraintValidator;

@Service
public class UserService {

	@Autowired
	private RestTemplate restTemplate;

	public UserResponseModel userRegistration(UserModel model) {

		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<>(headers);

		GeoLocationModel body = restTemplate.exchange("http://ip-api.com/json/" + model.getIpAddress(), HttpMethod.GET,
				entity, GeoLocationModel.class).getBody();

		UserResponseModel response = new UserResponseModel();

		if (ObjectUtils.isEmpty(body) || !StringUtils.hasText(body.getCountry())) {
			response.setMessage("Location not found for given IP Address.");
		} else if (!body.getCountry().equals("Canada")) {
			response.setMessage("You are not eligible to register from this " + body.getCountry() + " location.");
		} else {
			response.setUsername(model.getUsername());
			response.setCountry(body.getCountry());
			response.setCity(body.getCity());
			UUID uuid = UUID.randomUUID();
			response.setUuid(uuid.toString());
			response.setMessage(" Welcome " + response.getUsername() + ". Successfully registered from "
					+ response.getCity() + ".");
		}
		response.setUsername(model.getUsername());

		return response;
	}

	public UserResponseModel validate(UserModel model) {

		UserResponseModel response = new UserResponseModel();

		if (!StringUtils.hasText(model.getUsername())) {
			response.setMessage("User Name must be required.");
		} else if (!StringUtils.hasText(model.getIpAddress())) {
			response.setMessage("IP Address must be required.");
		} else if (!StringUtils.hasText(model.getPassword())) {
			response.setMessage("Password must be required.");
		}

		return response;

	}
}

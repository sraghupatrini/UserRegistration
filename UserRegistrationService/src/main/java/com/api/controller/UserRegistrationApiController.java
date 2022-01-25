package com.api.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.model.UserModel;
import com.api.model.UserResponseModel;
import com.api.services.UserService;

@RestController
@RequestMapping(value = "/api")
public class UserRegistrationApiController {

	@Autowired
	private UserService service;

	@PostMapping(value = "/user/registration", produces = { "application/json" }, consumes = { "application/json" })
	public ResponseEntity<?> userRegistration(@Valid @RequestBody UserModel model) {

		UserResponseModel response = service.userRegistration(model);

		if (!StringUtils.hasText(response.getUuid())) {
			return new ResponseEntity<>(response.getMessage(), HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}

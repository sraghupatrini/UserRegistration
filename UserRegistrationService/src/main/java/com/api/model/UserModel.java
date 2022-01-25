package com.api.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.api.util.ValidPassword;

@Validated
public class UserModel {

	@NotBlank(message = "User Name must be required.")
	@NotNull
	private String username;

	@ValidPassword
	private String password;

	@NotBlank(message = "IP Address required.")
	private String ipAddress;

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

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

}

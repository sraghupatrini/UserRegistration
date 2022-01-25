package com.api.model;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.http.HttpStatus;

public class ApiError {

	private HttpStatus status;
	private String code;
	private Timestamp timestamp;
	private String message;
	private String path;
	private List<String> messages;

	public ApiError(HttpStatus status) {
		this.status = status;
		timestamp = new Timestamp(System.currentTimeMillis());
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<String> getMessages() {
		return messages;
	}

	public void setMessages(List<String> messages) {
		this.messages = messages;
	}

}

package net.softplayer.e_commerce.dto;

import jakarta.validation.constraints.NotNull;

public class LoginRequest {
	@NotNull
	private String username;
	
	@NotNull 
	private String password;

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}
}
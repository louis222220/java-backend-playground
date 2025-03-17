package net.softplayer.e_commerce.dto;

import jakarta.validation.constraints.NotNull;

public class LoginRequest {
	@NotNull
	private Integer id;

	public Integer getId() {
		return id;
	}
}
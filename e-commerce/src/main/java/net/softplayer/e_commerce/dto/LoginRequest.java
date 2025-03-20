package net.softplayer.e_commerce.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

public class LoginRequest {
	@NotNull
	@DecimalMin(value = "1", message = "id must be integer greater than 0")
	private String id;

	public Integer getId() {
		return Integer.parseInt(id);
	}
}
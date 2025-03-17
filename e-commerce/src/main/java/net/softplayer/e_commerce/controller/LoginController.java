package net.softplayer.e_commerce.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import net.softplayer.e_commerce.dto.LoginRequest;
import net.softplayer.e_commerce.dto.LoginResponse;

@RestController
@RequestMapping("/api")
public class LoginController {
	@PostMapping("/login")
	public LoginResponse login(@Valid @RequestBody LoginRequest request) {
		return new LoginResponse("dummy-token");
	}
}
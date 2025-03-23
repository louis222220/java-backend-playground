package net.softplayer.e_commerce.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import net.softplayer.e_commerce.dto.LoginRequest;
import net.softplayer.e_commerce.dto.LoginResponse;
import net.softplayer.e_commerce.entity.User;
import net.softplayer.e_commerce.repository.UserRepository;
import net.softplayer.e_commerce.service.MyUserDetails;
import net.softplayer.e_commerce.service.TokenService;

@RestController
@RequestMapping("/api")
public class LoginController {
	private final TokenService tokenService;

	private final UserRepository userRepository;
	public LoginController(TokenService tokenService, UserRepository userRepository) {
		this.tokenService = tokenService;
		this.userRepository = userRepository;
	}


	@PostMapping("/login")
	public LoginResponse login(@Valid @RequestBody LoginRequest request) {
		User user = userRepository.findByUsername(request.getUsername());

		String token = tokenService.generateToken(user.getId());
		return new LoginResponse(token);
	}


	@GetMapping("/me")
	public @ResponseBody MyUserDetails getCurrentUser(@AuthenticationPrincipal MyUserDetails userDetails) {
		// TODO: filter the sensitive data
		return userDetails;
	}

}

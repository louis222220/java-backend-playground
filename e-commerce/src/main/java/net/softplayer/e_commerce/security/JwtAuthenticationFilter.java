package net.softplayer.e_commerce.security;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.security.core.Authentication;

import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotNull;
import net.softplayer.e_commerce.service.MyUserDetails;
import net.softplayer.e_commerce.service.TokenService;
import net.softplayer.e_commerce.entity.User;
import net.softplayer.e_commerce.repository.UserRepository;
import java.io.IOException;
import jakarta.servlet.ServletException;



@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
	private final TokenService tokenService;
	private final UserRepository userRepository;

	public JwtAuthenticationFilter(TokenService tokenService, UserRepository userRepository) {
		this.tokenService = tokenService;
		this.userRepository = userRepository;
	}

	@Override
	protected void doFilterInternal(
		@NotNull final HttpServletRequest request,
		@NotNull final HttpServletResponse response,
		@NotNull final FilterChain filterChain) throws ServletException, IOException {
		String authHeader = request.getHeader("Authorization");

		if (authHeader == null || !authHeader.startsWith("Bearer ")) {
			filterChain.doFilter(request, response);
			return;
		}

		String jwtToken = authHeader.substring(7);
		Long userId = tokenService.verifyToken(jwtToken);
		User user = userRepository.findById(userId)
			.orElseThrow(() -> new RuntimeException("User not found"));
		UserDetails userDetails = new MyUserDetails(user);
		 
		Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(authentication);
		filterChain.doFilter(request, response);
	}
}

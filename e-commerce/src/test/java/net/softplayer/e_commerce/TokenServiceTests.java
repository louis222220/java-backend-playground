package net.softplayer.e_commerce;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import net.softplayer.e_commerce.service.TokenService;


class TokenServiceTests {
	@Test
	void testGenerateToken() {
		TokenService tokenService = new TokenService(
			"any-issuer",
			"SecretKeyGreaterThan32Characters"
		);

		String token = tokenService.generateToken(1);

		assertTrue(token instanceof String);
		assertTrue(token.length() > 0);
	}
}

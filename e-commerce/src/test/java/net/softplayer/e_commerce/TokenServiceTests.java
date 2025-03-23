package net.softplayer.e_commerce;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import net.softplayer.e_commerce.service.TokenService;


class TokenServiceTests {
	private static final Long TEST_USER_ID = 1L;
	private static TokenService makeTokenService() {
		return new TokenService(
			"any-issuer",
			"SecretKeyGreaterThan32Characters"
		);
	}

	@Test
	void testGenerateToken() {
		TokenService tokenService = makeTokenService();

		String token = tokenService.generateToken(TEST_USER_ID);

		assertTrue(token instanceof String);
		assertTrue(token.length() > 0);
	}

	void testVerifyToken() {
		TokenService tokenService = makeTokenService();
		String token = tokenService.generateToken(TEST_USER_ID);

		Long userId = tokenService.verifyToken(token);

		assertEquals(TEST_USER_ID, userId);
	}
}

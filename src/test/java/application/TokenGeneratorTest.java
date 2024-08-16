package application;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import warner.application.TokenGenerator;
import warner.domain.model.User;

class TokenGeneratorTest {

    private TokenGenerator tokenGenerator;

    @BeforeEach
    void setUp() {
        tokenGenerator = new TokenGenerator();
    }

    @Test
    void shouldGenerateTokenForUser() {
        User user = new User();
        user.setEmail("test@example.com");

        tokenGenerator.generateJWTToken(user);

        String token = user.getToken();
        assertNotNull(token, "Token should not be null");

        String[] parts = token.split("\\.");
        assertTrue(parts.length == 3, "Token should have 3 parts separated by '.'");
    }
}

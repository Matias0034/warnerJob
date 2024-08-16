package warner.infrastructure.security;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class JwtAuthenticationToken extends UsernamePasswordAuthenticationToken {

    public JwtAuthenticationToken(Map<String, Object> claims) {
        super(claims.get("email"), null); // Asumiendo que "sub" es el ID del usuario
    }
}

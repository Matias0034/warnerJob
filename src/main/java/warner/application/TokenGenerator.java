package warner.application;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;
import warner.domain.model.User;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class TokenGenerator {

    private final String SECRET_KEY = "b6G7Z!kP@9Qw4sT$2j%LnX1rUv&8yE3H";

    public void generateJWTToken(User user) {
            Map<String, Object> claims = new HashMap<>();
            claims.put("name", user.getEmail());


            String token = Jwts.builder()
                    .setClaims(claims)
                    .setSubject(user.getEmail())
                    .setIssuedAt(new Date(System.currentTimeMillis()))
                    .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // 10 horas
                    .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                    .compact();
            user.setToken(token);
    }
}

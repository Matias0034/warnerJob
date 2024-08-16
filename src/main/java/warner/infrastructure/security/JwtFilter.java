package warner.infrastructure.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import org.springframework.security.core.context.SecurityContextHolder;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtFilter extends GenericFilterBean {

    private static final String SECRET_KEY = "b6G7Z!kP@9Qw4sT$2j%LnX1rUv&8yE3H";

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        String header = httpRequest.getHeader("Authorization");
        if (header != null && header.startsWith("Bearer ")) {
            String token = header.substring(7);

            try {
                Claims claims = Jwts.parser()
                        .setSigningKey(SECRET_KEY)
                        .parseClaimsJws(token)
                        .getBody();

                // Aquí puedes construir una autenticación basada en el token
                String username = claims.getSubject();
                if (username != null) {
                    // Crear un objeto de autenticación con los detalles del usuario
                    UsernamePasswordAuthenticationToken authentication =
                            new UsernamePasswordAuthenticationToken(username, null, new ArrayList<>());
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            } catch (SignatureException e) {
                httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return;
            }
            catch (ExpiredJwtException e) {
                httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                Map<String, Object> responseBody = new HashMap<>();
                responseBody.put("message", "Token expired");
                ObjectMapper mapper = new ObjectMapper();
                String jsonResponse = mapper.writeValueAsString(responseBody);
                httpResponse.getWriter().write(jsonResponse);
                return;
            }
            catch (Exception e) {
                httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                httpResponse.getWriter().write("Authentication error");
                return;
            }

        }
        chain.doFilter(request, response);
    }
}

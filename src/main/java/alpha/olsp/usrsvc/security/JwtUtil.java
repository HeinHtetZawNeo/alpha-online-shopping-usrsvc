package alpha.olsp.usrsvc.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;
import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtil {

    // Generate a secure key for HS256
    private final SecretKey SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    // Ensure the same key is used for both generating and verifying tokens
    //private final SecretKey SECRET_KEY = Keys.hmacShaKeyFor("apsd-online-shopping".getBytes());

    /**
     * Generates a JWT token with the username and role as claims.
     *
     * @param username The username to be included in the token.
     * @param role     The user role to be included as a claim.
     * @return The generated JWT token.
     */
    public String generateToken(String username, String role) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("role", role); // Add the role to claims

        return createToken(claims, username);
    }

    /**
     * Creates a JWT token with the given claims and subject.
     *
     * @param claims  The claims to include in the token.
     * @param subject The subject (usually the username).
     * @return The generated JWT token.
     */
    private String createToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // 10-hour expiration
                .signWith(SECRET_KEY) // Sign with the secure key
                .compact();
    }

    /**
     * Validates the JWT token and checks if it matches the provided username.
     *
     * @param token    The JWT token to validate.
     * @param username The username to match against the token's subject.
     * @return True if the token is valid and matches the username, false otherwise.
     */
    public Boolean validateToken(String token, String username) {
        final String extractedUsername = extractUsername(token);
        return (extractedUsername.equals(username) && !isTokenExpired(token));
    }

    /**
     * Extracts the username from the JWT token.
     *
     * @param token The JWT token.
     * @return The username from the token's subject.
     */
    public String extractUsername(String token) {
        return extractAllClaims(token).getSubject();
    }

    /**
     * Extracts the user role from the JWT token.
     *
     * @param token The JWT token.
     * @return The user role from the token.
     */
    public String extractUserRole(String token) {
        return extractAllClaims(token).get("role", String.class);
    }

    /**
     * Extracts all claims from the JWT token.
     *
     * @param token The JWT token.
     * @return The claims in the token.
     */
    public Claims extractAllClaims(String token) {
        return Jwts.parserBuilder().setSigningKey(SECRET_KEY).build().parseClaimsJws(token).getBody();
    }

    /**
     * Checks if the JWT token is expired.
     *
     * @param token The JWT token.
     * @return True if the token is expired, false otherwise.
     */
    private Boolean isTokenExpired(String token) {
        return extractAllClaims(token).getExpiration().before(new Date());
    }
}
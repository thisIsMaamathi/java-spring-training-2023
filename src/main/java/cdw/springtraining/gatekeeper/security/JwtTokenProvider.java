package cdw.springtraining.gatekeeper.security;


import cdw.springtraining.gatekeeper.constant.CommonConstants;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import java.security.Key;
import java.util.Date;

/**
 * Jwt helper class responsible for generating, parsing, and validating Jwt used for authentication.
 * This class handles the creation of JWT tokens, validation of token , and extraction of user information from tokens.
 */
@Component
public class JwtTokenProvider {

    private static final Logger logger = LoggerFactory.getLogger(JwtTokenProvider.class);

    @Value("${app.jwt-secret}")
    private String jwtSecret;

    @Value("${app-jwt-expiration-milliseconds}")
    private long jwtExpirationDate;

    /**
     * Generates a JWT token based on user authentication information.
     *
     * @param authentication the user's authentication details.
     * @return a JWT token as a string.
     */
    public String generateToken(Authentication authentication) {
        String username = authentication.getName();

        Date currentDate = new Date();

        Date expireDate = new Date(currentDate.getTime() + jwtExpirationDate);

        String token = Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(expireDate)
                .signWith(key())
                .compact();

        return token;
    }
    private Key key() {
        return Keys.hmacShaKeyFor(
                Decoders.BASE64.decode(jwtSecret)
        );
    }

    /**
     * Retrieves the username from a JWT token.
     *
     * @param token the JWT token as a string.
     * @return the username extracted from the token.
     */
    public String getUsername(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key())
                .build()
                .parseClaimsJws(token)
                .getBody();
        String username = claims.getSubject();
        return username;
    }

    /**
     * Validates the integrity and expiration of a JWT token.
     *
     * @param token the JWT token to be validated.
     * @return true if the token is valid; false otherwise.
     */
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(key())
                    .build()
                    .parse(token);
            return true;
        } catch (MalformedJwtException e) {
            logger.error(CommonConstants.MAL_INFORMED_TOKEN, e.getMessage());
        } catch (ExpiredJwtException e) {
            logger.error(CommonConstants.EXPIRED_JWT, e.getMessage());
        } catch (UnsupportedJwtException e) {
            logger.error(CommonConstants.UNSUPPORTED_JWT, e.getMessage());
        } catch (IllegalArgumentException e) {
            logger.error(CommonConstants.ILLEGAL_ARGUMENT, e.getMessage());
        }
        return false;
    }
}


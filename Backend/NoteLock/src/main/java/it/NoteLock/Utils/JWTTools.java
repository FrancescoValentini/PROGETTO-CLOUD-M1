package it.NoteLock.Utils;

import java.util.Base64;
import java.util.Date;
import java.util.Map;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

/**
 * Class for generating and verifying a JWT token
 * 
 * @author Francesco Valentini
 */
@Component
public class JWTTools {
	@Value("${security.jwt.secret}")
	String b64HmacKey;
	
	@Value("${security.jwt.issuer}")
	String jwtIssuer;
	
	@Value("${security.jwt.expiration}")
	int jwtExpiration;
	
    /**
     * Generates a signed JWT for the given user ID.
     *
     * @param uid The user ID to be included as the subject of the JWT.
     * @return A signed JWT as a compact string.
     */
	public String signToken(String uid) {
		SecretKey hmacKey = Keys.hmacShaKeyFor(Base64.getDecoder().decode(b64HmacKey));
		return Jwts.builder()
				.issuer(jwtIssuer)
				.subject(uid)
				.expiration(new Date(getExpirationTimeInMillis(jwtExpiration)))
				.issuedAt(new Date(System.currentTimeMillis()))
				.signWith(hmacKey)
				.compact();
	}
	
    /**
     * Verifies the given JWT and returns its claims.
     *
     * @param token The JWT to verify.
     * @return A map containing all the claims of the JWT.
     * @throws JwtException If the token is invalid or cannot be verified.
     */
    public Map<String, Object> verifyToken(String token) throws JwtException {
        SecretKey hmacKey = Keys.hmacShaKeyFor(Base64.getDecoder().decode(b64HmacKey));

        Jws<Claims> jws = Jwts.parser()
                .verifyWith(hmacKey)
                .build()
                .parseSignedClaims(token);
        
        return jws.getPayload();
    }
	
    private long getExpirationTimeInMillis(int days) {
        long daysInMillis = days * 24L * 60 * 60 * 1000; // Converts days to milliseconds
        return System.currentTimeMillis() + daysInMillis; // Add current time to get expiration timestamp
    }
}
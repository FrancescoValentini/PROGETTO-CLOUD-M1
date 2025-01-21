package it.NoteLock.Exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.IncorrectClaimException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.MissingClaimException;
import io.jsonwebtoken.PrematureJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.SignatureException;

/**
 * Global Exception Handler
 * 
 * @author Francesco Valentini
 */
@ControllerAdvice
public class GlobalExceptionHandler {
	
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    
	@ExceptionHandler(value=ResourceNotFoundException.class)
	public ResponseEntity<Object> resourceNotFoundException(ResourceNotFoundException ex){
		logger.warn("ResourceNotFoundException: {}",ex.getMessage());
		return new ResponseEntity<>(ex.getMessage(),HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value=UnauthorizedException.class)
	public ResponseEntity<Object> unauthorizedException(UnauthorizedException ex){
		logger.warn("UnauthorizedException: {}",ex.getMessage());
		return new ResponseEntity<>(ex.getMessage(),HttpStatus.UNAUTHORIZED);
	}
	
	@ExceptionHandler(value=InvalidTokenException.class)
	public ResponseEntity<Object> invalidTokenException(InvalidTokenException ex){
		logger.warn("InvalidTokenException: {}",ex.getMessage());
		return new ResponseEntity<>(ex.getMessage(),HttpStatus.UNAUTHORIZED);
	}
	
	@ExceptionHandler(value=InvalidCredentialsException.class)
	public ResponseEntity<Object> invalidCredentialsException(InvalidCredentialsException ex){
		logger.warn("InvalidCredentialsException: {}",ex.getMessage());
		return new ResponseEntity<>(ex.getMessage(),HttpStatus.UNAUTHORIZED);
	}
	
	// JWT
    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<String> handleExpiredJwtException(ExpiredJwtException ex) {
        logger.error("Expired JWT token: {}", ex.getMessage(), ex);
        return new ResponseEntity<>("Token has expired", HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(UnsupportedJwtException.class)
    public ResponseEntity<String> handleUnsupportedJwtException(UnsupportedJwtException ex) {
        logger.error("Unsupported JWT token: {}", ex.getMessage(), ex);
        return new ResponseEntity<>("Token is not supported", HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(MalformedJwtException.class)
    public ResponseEntity<String> handleMalformedJwtException(MalformedJwtException ex) {
        logger.error("Malformed JWT token: {}", ex.getMessage(), ex);
        return new ResponseEntity<>("Token is malformed", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(SignatureException.class)
    public ResponseEntity<String> handleSignatureException(SignatureException ex) {
        logger.error("Invalid JWT signature: {}", ex.getMessage(), ex);
        return new ResponseEntity<>("Token signature is invalid", HttpStatus.UNAUTHORIZED);
    }


    @ExceptionHandler(PrematureJwtException.class)
    public ResponseEntity<String> handlePrematureJwtException(PrematureJwtException ex) {
        logger.error("JWT token is not yet valid: {}", ex.getMessage(), ex);
        return new ResponseEntity<>("Token is not yet valid", HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(IncorrectClaimException.class)
    public ResponseEntity<String> handleIncorrectClaimException(IncorrectClaimException ex) {
        logger.error("Incorrect JWT claim: {}", ex.getMessage(), ex);
        return new ResponseEntity<>("Token contains incorrect claims", HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(MissingClaimException.class)
    public ResponseEntity<String> handleMissingClaimException(MissingClaimException ex) {
        logger.error("Missing JWT claim: {}", ex.getMessage(), ex);
        return new ResponseEntity<>("Token is missing required claims", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(JwtException.class)
    public ResponseEntity<String> handleGenericJwtException(JwtException ex) {
        logger.error("JWT error: {}", ex.getMessage(), ex);
        return new ResponseEntity<>("Token validation failed", HttpStatus.UNAUTHORIZED);
    }
	
}

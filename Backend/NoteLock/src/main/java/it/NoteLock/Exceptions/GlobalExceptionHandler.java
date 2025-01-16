package it.NoteLock.Exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

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
		logger.error("ResourceNotFoundException: {}",ex.getMessage());
		return new ResponseEntity<>(ex.getMessage(),HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value=UnauthorizedException.class)
	public ResponseEntity<Object> unauthorizedException(UnauthorizedException ex){
		logger.error("UnauthorizedException: {}",ex.getMessage());
		return new ResponseEntity<>(ex.getMessage(),HttpStatus.UNAUTHORIZED);
	}
	
	@ExceptionHandler(value=InvalidTokenException.class)
	public ResponseEntity<Object> invalidTokenException(InvalidTokenException ex){
		logger.error("InvalidTokenException: {}",ex.getMessage());
		return new ResponseEntity<>(ex.getMessage(),HttpStatus.UNAUTHORIZED);
	}
	
	@ExceptionHandler(value=InvalidCredentialsException.class)
	public ResponseEntity<Object> invalidCredentialsException(InvalidCredentialsException ex){
		logger.error("InvalidCredentialsException: {}",ex.getMessage());
		return new ResponseEntity<>(ex.getMessage(),HttpStatus.UNAUTHORIZED);
	}
}

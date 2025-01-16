package it.NoteLock.Exceptions;
/**
 * This exception is thrown in case of errors during authentication token validation
 * 
 * @author Francesco Valentini
 */
public class InvalidTokenException extends RuntimeException {
	private static final long serialVersionUID = 6808160681073921386L;

	public InvalidTokenException(String message) {
		super(message);
	}

}

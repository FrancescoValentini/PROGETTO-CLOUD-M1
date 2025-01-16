package it.NoteLock.Exceptions;

/**
 * This exception is thrown in case of invalid user credentials
 * 
 * @author Francesco Valentini
 */
public class InvalidCredentialsException extends RuntimeException {

	private static final long serialVersionUID = 6252797946625731944L;

	public InvalidCredentialsException(String message) {
		super(message);
	}
}

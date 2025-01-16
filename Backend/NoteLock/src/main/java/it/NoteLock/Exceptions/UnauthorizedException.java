package it.NoteLock.Exceptions;

/**
 * This exception is thrown if the user is not authorized
 * 
 * @author Francesco Valentini
 */
public class UnauthorizedException extends RuntimeException {
	private static final long serialVersionUID = 8972363558880406698L;
	
	public UnauthorizedException(String message) {
		super(message);
	}
}

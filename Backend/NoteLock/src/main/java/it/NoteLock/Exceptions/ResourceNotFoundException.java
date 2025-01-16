package it.NoteLock.Exceptions;

/**
 * Exception that is thrown when the requested resource cannot be found
 * 
 * @author Francesco Valentini
 */
public class ResourceNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 6612112222891132894L;
	
	public ResourceNotFoundException(String message) {
		super(message);
	}
}

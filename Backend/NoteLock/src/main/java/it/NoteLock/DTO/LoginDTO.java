package it.NoteLock.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class LoginDTO {
    @NotBlank(message = "Username obbligatorio")
    @Pattern(regexp = "^[A-Za-z0-9-_\\.]+$", message = "Username può contenere solo lettere, numeri, e i simboli - _ .")
	private String username;
    
    @NotBlank(message = "Password obbligatoria")
	private String password;
	
	
	
	/**
	 * @param username
	 * @param password
	 */
	public LoginDTO(String username, String password) {
		this.username = username;
		this.password = password;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}

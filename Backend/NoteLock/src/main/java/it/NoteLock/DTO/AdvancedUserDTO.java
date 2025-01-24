package it.NoteLock.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class AdvancedUserDTO {
    @NotBlank(message = "Nome è obbligatorio")
    @Size(max = 50, message = "Il nome non può superare i 50 caratteri")
    @Pattern(regexp = "^[A-Za-zàèéìòùùA-ÿ]+$", message = "Il nome può contenere solo lettere")
	private String nome;
    
    @NotBlank(message = "Cognome è obbligatorio")
    @Size(max = 50, message = "Il cognome non può superare i 50 caratteri")
    @Pattern(regexp = "^[A-Za-zàèéìòùùA-ÿ]+$", message = "Il cognome può contenere solo lettere")
	private String cognome;
    
    @NotBlank(message = "L'email è obbligatoria")
    @Email(message = "Email non valida")
	private String email;
    
    @NotBlank(message = "Username obbligatorio")
    @Pattern(regexp = "^[A-Za-z0-9-_\\.]+$", message = "Username può contenere solo lettere, numeri, e i simboli - _ .")
	private String username;
    
    @NotBlank(message = "Password obbligatoria")
	private String password;
	
	public AdvancedUserDTO(String nome, String cognome, String username, String email, String password) {
		this.nome = nome;
		this.cognome = cognome;
		this.username = username;
		this.email = email;
		this.password = password;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
}

package it.NoteLock.DTO;

public class RegisterDTO {
	private String nome;
	private String cognome;
	private String email;
	private String username;
	private String password;
	/**
	 * @param nome
	 * @param cognome
	 * @param email
	 * @param username
	 * @param password
	 */
	public RegisterDTO(String nome, String cognome, String email, String username, String password) {
		this.nome = nome;
		this.cognome = cognome;
		this.email = email;
		this.username = username;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
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

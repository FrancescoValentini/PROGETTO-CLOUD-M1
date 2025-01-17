package it.NoteLock.Models;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import io.jsonwebtoken.lang.Arrays;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity 
public class UserAccount implements UserDetails{
	@Id
	private String id;
	private String nome;
	private String cognome;
	private String username;
	private String email;
	private String password;
	@OneToMany(mappedBy = "utente", cascade = CascadeType.ALL, orphanRemoval = true , fetch = FetchType.EAGER)
	private List<Folder> cartelle;
	
	public UserAccount() {}
	
	public UserAccount(String id, String nome, String cognome ,String username, String email , String password) {
		this.id = id;
		this.nome = nome;
		this.cognome = cognome;
		this.username = username;
		this.email = email;
		this.password = password;
		this.cartelle = new ArrayList<>();
	}
	
	public List<Folder> getCartelle() {
		return cartelle;
	}

	public void setCartelle(List<Folder> cartelle) {
		this.cartelle = cartelle;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
	    return Arrays.asList(new SimpleGrantedAuthority[] { new SimpleGrantedAuthority("Utente") });
	}


	@Override
	public String getUsername() {
		
		return username;
	}
	
}

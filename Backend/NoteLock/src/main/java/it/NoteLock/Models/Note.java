package it.NoteLock.Models;

import java.util.Date;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
@Entity
public class Note {
	@Id
	private String id;
	private Date timestamp;
	private String title;
	private String body;
	@ManyToOne
	@JoinColumn(name = "utente_id", nullable = false)
	private UserAccount utente;
	@ManyToOne
	@JoinColumn(name = "cartella_id", nullable = false)
	private Folder cartella;
	
	
	@Enumerated(EnumType.STRING)
	EncryptionOptions  encrypted;
	

	public Note(String id, Date timestamp , String title , String body , UserAccount utente , Folder cartella, EncryptionOptions  encrypted) {
		this.id = id;
		this.timestamp = timestamp;
		this.title = title;
		this.body = body;
		this.utente= utente;
		this.cartella = cartella;
		this.encrypted = encrypted;
	}
	
	public Note() {
		
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}
	public UserAccount getUtente() {
		return utente;
	}

	public void setUtente(UserAccount utente) {
		this.utente = utente;
	}

	public Folder getCartella() {
		return cartella;
	}

	public void setCartella(Folder cartella) {
		this.cartella = cartella;
	}

	public EncryptionOptions getEncrypted() {
		return encrypted;
	}

	public void setEncrypted(EncryptionOptions encrypted) {
		this.encrypted = encrypted;
	}
}

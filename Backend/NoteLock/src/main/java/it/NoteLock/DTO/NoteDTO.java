package it.NoteLock.DTO;

import it.NoteLock.Models.EncryptionOptions;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class NoteDTO {
	
	@NotBlank(message = "Titolo della nota obbligatorio")
	private String subject;
	private String body;
	
	@NotBlank(message = "Identificativo della cartella obbligatorio")
	private String folderId;
	
	@NotNull(message = "Opzioni di cifratura obbligatorie")
	private EncryptionOptions encrypted;

	public NoteDTO() {

	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getFolderId() {
		return folderId;
	}

	public void setFolderId(String folderId) {
		this.folderId = folderId;
	}

	public EncryptionOptions getEncrypted() {
		return encrypted;
	}

	public void setEncrypted(EncryptionOptions encrypted) {
		this.encrypted = encrypted;
	}

}

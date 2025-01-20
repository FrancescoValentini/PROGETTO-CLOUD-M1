package it.NoteLock.DTO;

import it.NoteLock.Models.EncryptionOptions;

public class NoteDTO {
	private String subject;
	private String body;
	private String folderId;
	private EncryptionOptions encrypted;
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


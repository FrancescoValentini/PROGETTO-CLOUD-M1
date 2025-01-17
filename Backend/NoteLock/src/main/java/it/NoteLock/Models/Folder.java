package it.NoteLock.Models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
@Entity
public class Folder {
	@Id
	private String id;
	private String folderName;
	private Date creationTimestamp;
	@OneToMany(mappedBy = "cartella", cascade = CascadeType.ALL, orphanRemoval = true , fetch = FetchType.EAGER)
	private List<Note>notesList;
	@ManyToOne
	@JoinColumn(name = "utente_id", nullable = false )
	private UserAccount utente;
	
	public Folder(String id , String folderName , Date creationTimeStamp,UserAccount utente) {
		this.id = id;
		this.folderName = folderName;
		this.creationTimestamp = creationTimestamp;
		this.utente = utente;
		this.notesList = new ArrayList<>();
		
	}
	
	public Folder() {
		
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFolderName() {
		return folderName;
	}

	public void setFolderName(String folderName) {
		this.folderName = folderName;
	}

	public Date getCreationTimestamp() {
		return creationTimestamp;
	}

	public void setCreationTimestamp(Date creationTimestamp) {
		this.creationTimestamp = creationTimestamp;
	}
}

package it.NoteLock.Models;

import java.util.Date;

public class Folder {
	private String id;
	private String folderName;
	private Date creationTimestamp;
	
	public Folder(String id , String folderName , Date creationTimeStamp) {
		this.id = id;
		this.folderName = folderName;
		this.creationTimestamp = creationTimestamp;
		
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

package it.NoteLock.DTO;

public class FolderDTO {
	private String folderName;

	public FolderDTO(String folderName) {
		this.folderName = folderName;
	}

	public String getFolderName() {
		return folderName;
	}

	public void setFolderName(String folderName) {
		this.folderName = folderName;
	}
	
}

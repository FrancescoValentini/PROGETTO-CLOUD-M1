package it.NoteLock.DTO;

import jakarta.validation.constraints.NotBlank;

public class FolderDTO {
    @NotBlank(message = "Nome della cartella obbligatorio")
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

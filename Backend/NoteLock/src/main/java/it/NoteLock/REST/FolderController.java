package it.NoteLock.REST;

import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.NoteLock.DTO.FolderDTO;
import it.NoteLock.Exceptions.ResourceNotFoundException;
import it.NoteLock.Models.Folder;
import it.NoteLock.Models.UserAccount;
import it.NoteLock.Repositories.FolderRepository;
import jakarta.transaction.Transactional;

/**
 * @author Giulia Balestra
 * @author Francesco Valentini
 */
@RestController
@RequestMapping("/api/folders")
public class FolderController {
	
	@Autowired
	FolderRepository repoCartelle;
	
	@GetMapping
	public ResponseEntity<Object> getAllFolders(@AuthenticationPrincipal UserAccount utente){
		return new ResponseEntity<>(repoCartelle.getUserFolders(utente.getId()),HttpStatus.OK);

	}
	
	@GetMapping("/{id}")
	public ResponseEntity <Object> getFolder(@AuthenticationPrincipal UserAccount utente,@PathVariable("id") String id) {
		if(repoCartelle.isFolderOwnedByUser(utente.getId(),id))
			return new ResponseEntity<>(repoCartelle.findFolderByUser(utente.getId(), id), HttpStatus.OK);
		
		throw new ResourceNotFoundException("Folder not found");
	}
	
	@GetMapping("/notes/{id}")
	public ResponseEntity <Object> getNotesByFolderID(@AuthenticationPrincipal UserAccount utente, @PathVariable("id") String id) {
		if(repoCartelle.isFolderOwnedByUser(utente.getId(),id))
			return new ResponseEntity<>(repoCartelle.findAllNotesByFolderId(id), HttpStatus.OK);
		throw new ResourceNotFoundException("Folder not found.");
	}
	
	@PostMapping
	public ResponseEntity <Object> createFolder(
			@AuthenticationPrincipal UserAccount utente,
			@RequestBody FolderDTO folder){
		if(!repoCartelle.findUserByfolderName(utente.getId(),folder.getFolderName()).isPresent()) {
			Folder f = new Folder(
					UUID.randomUUID().toString(),
					folder.getFolderName(),
					new Date(System.currentTimeMillis()),
					utente);
			repoCartelle.save(f);
			return new ResponseEntity<>(f.getId(),HttpStatus.CREATED);
		}
		return new ResponseEntity<>("Folder name already in use",HttpStatus.CONFLICT);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Object> updateFolder(@PathVariable("id") String id,@AuthenticationPrincipal UserAccount utente,
			@RequestBody FolderDTO folder){
		if(repoCartelle.isFolderOwnedByUser(utente.getId(),id)) {
			Folder f = repoCartelle.findFolderByUser(utente.getId(),id).get();
			f.setFolderName(folder.getFolderName());
			repoCartelle.save(f);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		
		throw new ResourceNotFoundException("Folder not found");
	}
	
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<Object> deleteFolder(@AuthenticationPrincipal UserAccount utente, @PathVariable("id") String id){
		if(repoCartelle.isFolderOwnedByUser(utente.getId(),id)) {
			Folder f = repoCartelle.findFolderByUser(utente.getId(),id).get();
			if(!f.hasNotes()) {
				repoCartelle.deleteFolderById(id);
				return new ResponseEntity<>(HttpStatus.OK);
			}else {
				return new ResponseEntity<>("Folder is not empty.",HttpStatus.BAD_REQUEST);
			}
		}
		throw new ResourceNotFoundException("Folder not found");
	}
}

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
	public ResponseEntity<Object> getAllFolders(){
		return new ResponseEntity<>(repoCartelle.findAll(),HttpStatus.OK);

	}
	
	@GetMapping("/{id}")
	public ResponseEntity <Object> getFolder(@PathVariable("id") String id) {
		return new ResponseEntity<>(repoCartelle.findById(id), HttpStatus.OK);
		
	}
	
	@PostMapping
	public ResponseEntity <Object> createFolder(
			@AuthenticationPrincipal UserAccount utente,
			@RequestBody FolderDTO folder){
		if(!repoCartelle.findByfolderName(folder.getFolderName()).isPresent()) {
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
		if(repoCartelle.existsById(id)) {
			Folder f = repoCartelle.findById(id).get();
			f.setFolderName(folder.getFolderName());
			repoCartelle.save(f);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		
		throw new ResourceNotFoundException("Folder not found");
	}
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteFolder(@PathVariable("id") String id){
		// TODO RIVEDERE DELETE
		if(repoCartelle.existsById(id)) {
			repoCartelle.deleteById(id);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		throw new ResourceNotFoundException("Folder not found");
	}
}

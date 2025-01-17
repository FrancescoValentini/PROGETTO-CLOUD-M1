package it.NoteLock.REST;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Giulia Balestra
 * @author Francesco Valentini
 */
@RestController
@RequestMapping("/api/folders")
public class FolderController {
	
	public ResponseEntity<Object> getAllFolders(){
		return new ResponseEntity<>(HttpStatus.OK);

	}
	
	@GetMapping("/{id}")
	public ResponseEntity <Object> getFolder() {
		return new ResponseEntity<>( HttpStatus.OK);
		
	}
	
	@PostMapping
	public ResponseEntity <Object> createFolder(){
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PutMapping
	public ResponseEntity<Object> updateFolder(){
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteFolder(){
		return new ResponseEntity<>(HttpStatus.OK);
	}
}

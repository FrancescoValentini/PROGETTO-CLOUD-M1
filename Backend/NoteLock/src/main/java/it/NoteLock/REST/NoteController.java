package it.NoteLock.REST;

import java.util.Date;
import java.util.UUID;

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
import it.NoteLock.DTO.NoteDTO;
import it.NoteLock.Exceptions.ResourceNotFoundException;
import it.NoteLock.Models.Folder;
import it.NoteLock.Models.Note;
import it.NoteLock.Models.UserAccount;
import it.NoteLock.Repositories.NoteRepository;
import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/api/notes")
public class NoteController {
	
	NoteRepository repoNote;
	
	public ResponseEntity<Object> getAllNote(){
		return new ResponseEntity<>(repoNote.findAll(),HttpStatus.OK);
		
	}

	@GetMapping("/{id}")
	public ResponseEntity <Object> getNote(@PathVariable("id") String id) {
		return new ResponseEntity<>(repoNote.findById(id), HttpStatus.OK);
	}
	

}

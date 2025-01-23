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

import it.NoteLock.DTO.NoteDTO;
import it.NoteLock.Exceptions.ResourceNotFoundException;
import it.NoteLock.Models.Folder;
import it.NoteLock.Models.Note;
import it.NoteLock.Models.UserAccount;
import it.NoteLock.Repositories.FolderRepository;
import it.NoteLock.Repositories.NoteRepository;
import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/api/notes")
public class NoteController {
	@Autowired
	NoteRepository repoNote;

	@Autowired
	FolderRepository repoCartelle;

	@GetMapping
	public ResponseEntity<Object> getAllNote(@AuthenticationPrincipal UserAccount utente) {
		return new ResponseEntity<>(repoNote.getUserNotes(utente.getId()), HttpStatus.OK);

	}

	@GetMapping("/{id}")
	public ResponseEntity<Object> getNote(@AuthenticationPrincipal UserAccount utente, @PathVariable("id") String id) {
	
		if(repoNote.isNoteOwnedByUser(utente.getId(), id)) {
			return new ResponseEntity<>(repoNote.findNoteByUser(utente.getId(), id), HttpStatus.OK);
		}
		
		throw new ResourceNotFoundException("Note not found");

	}

	@PostMapping
	public ResponseEntity<Object> createNote(@AuthenticationPrincipal UserAccount utente, @RequestBody NoteDTO note) {
		System.out.println(note.getFolderId());
		if (repoCartelle.isFolderOwnedByUser(utente.getId(),note.getFolderId())) {
			Folder f = repoCartelle.findFolderByUser(utente.getId(),note.getFolderId()).get();
			Note n = new Note(UUID.randomUUID().toString(),
					new Date(System.currentTimeMillis()),
					note.getSubject(),
					note.getBody(),
					utente,
					f, 
					note.getEncrypted());
			repoNote.save(n);
			return new ResponseEntity<>(HttpStatus.CREATED);
		}
		throw new ResourceNotFoundException("Folder not found");
	}

	@PutMapping("/{id}")
	public ResponseEntity<Object> updateNote(@PathVariable("id") String id, @AuthenticationPrincipal UserAccount utente,
			@RequestBody NoteDTO note) {
		if (repoNote.isNoteOwnedByUser(utente.getId(), id)) {
			Folder f = repoCartelle.findFolderByUser(utente.getId(),note.getFolderId()).get();
			Note n = repoNote.findNoteByUser(utente.getId(), id).get();
			n.setTitle(note.getSubject());
			n.setBody(note.getBody());
			n.setEncrypted(note.getEncrypted());
			n.setCartella(f);
			repoNote.save(n);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		throw new ResourceNotFoundException("Note not found");
	}

	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<Object> deleteNote(@AuthenticationPrincipal UserAccount utente, @PathVariable("id") String id) {
		System.out.println(repoNote.isNoteOwnedByUser(utente.getId(), id));
		if (repoNote.isNoteOwnedByUser(utente.getId(), id)) {
			repoNote.deleteNoteById(id);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		throw new ResourceNotFoundException("Note not found");
	}
}

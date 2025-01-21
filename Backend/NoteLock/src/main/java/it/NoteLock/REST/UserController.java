package it.NoteLock.REST;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.NoteLock.Exceptions.ResourceNotFoundException;
import it.NoteLock.Repositories.UserRepository;

/**
 * @author Giulia Balestra
 * @author Francesco Valentini
 */

@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	UserRepository repoUtenti;
	
	@GetMapping
	public ResponseEntity<Object> getAllUser(){
		return new ResponseEntity<>(repoUtenti.findAll(),HttpStatus.OK);
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity <Object> getUser(@PathVariable("id") String id) {
		return new ResponseEntity<>(repoUtenti.findById(id),HttpStatus.OK);
	}
	
	
	@PostMapping("/{id}")
	public ResponseEntity <Object> createUser(){
		
		return new ResponseEntity<>(HttpStatus.OK);
		
	}

	@PutMapping
	public ResponseEntity<Object> updateUser(){
		return new ResponseEntity<>(HttpStatus.OK);
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteUser(@PathVariable("id") String id){
		if(repoUtenti.existsById(id)) {
			repoUtenti.deleteById(id);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		throw new ResourceNotFoundException("User not found");
	}
}

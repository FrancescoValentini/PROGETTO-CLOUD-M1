package it.NoteLock.REST;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {


	public ResponseEntity <Object> getUser() {
		return new ResponseEntity<>( HttpStatus.OK);
		
	}
	@PostMapping ("/{id}")
	public ResponseEntity <Object> createUser(){
		return new ResponseEntity<>(HttpStatus.OK);
	}
	@GetMapping
	public ResponseEntity<Object> getAllUser(){
		return new ResponseEntity<>(HttpStatus.OK);

	}
	@PutMapping
	public ResponseEntity<Object> updateUser(){
		return new ResponseEntity<>(HttpStatus.OK);
	}
	@DeleteMapping
	public ResponseEntity<Object> deleteUser(){
		return new ResponseEntity<>(HttpStatus.OK);
	}
}

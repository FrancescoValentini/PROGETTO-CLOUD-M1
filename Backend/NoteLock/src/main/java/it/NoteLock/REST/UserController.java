package it.NoteLock.REST;

import java.util.UUID;

import org.apache.catalina.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.NoteLock.DTO.AdvancedUserDTO;
import it.NoteLock.Exceptions.GlobalExceptionHandler;
import it.NoteLock.Exceptions.ResourceNotFoundException;
import it.NoteLock.Models.UserAccount;
import it.NoteLock.Repositories.UserRepository;
import it.NoteLock.Utils.PasswordEncoder;

/**
 * @author Giulia Balestra
 * @author Francesco Valentini
 */

@RestController
@RequestMapping("/api/users")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	UserRepository repoUtenti;
	
	@Autowired
	PasswordEncoder argon2encoder;
	
	@GetMapping
	public ResponseEntity<Object> getAllUser(){
		return new ResponseEntity<>(repoUtenti.findAll(),HttpStatus.OK);
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity <Object> getUser(@PathVariable("id") String id) {
		return new ResponseEntity<>(repoUtenti.findById(id),HttpStatus.OK);
	}
	
	@PostMapping("/{userRole}")
	public ResponseEntity <Object> createUser(
			@AuthenticationPrincipal UserAccount admin,
			@RequestBody AdvancedUserDTO utente,
			@PathVariable("userRole") String role){
		if (!repoUtenti.findByUsername(utente.getUsername()).isPresent()) {
			String encodedPassword = argon2encoder.encodePassword(utente.getPassword());
			UserAccount account = new UserAccount(
					UUID.randomUUID().toString(), 
					utente.getNome(), 
					utente.getCognome(),
					utente.getUsername(), 
					utente.getEmail(), 
					encodedPassword, 
					new SimpleGrantedAuthority(role));
			repoUtenti.save(account);
			logger.info(admin.getUsername() + " Added new user: " + account.getUsername());
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>("Username already in use", HttpStatus.CONFLICT);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Object> updateUser(@RequestBody AdvancedUserDTO utente){
		if(repoUtenti.findByUsername(utente.getUsername()).isPresent()) {
			UserAccount u = repoUtenti.findByUsername(utente.getUsername()).get();
			u.setNome(utente.getNome());
			u.setCognome(utente.getCognome());
			u.setUsername(utente.getUsername());
			u.setEmail(utente.getEmail());
			repoUtenti.save(u);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		throw new ResourceNotFoundException("User not found");
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

package it.NoteLock.REST;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.NoteLock.DTO.LoginDTO;
import it.NoteLock.DTO.RegisterDTO;
import it.NoteLock.Exceptions.InvalidCredentialsException;
import it.NoteLock.Exceptions.UnauthorizedException;
import it.NoteLock.Models.UserAccount;
import it.NoteLock.Repositories.UserRepository;
import it.NoteLock.Utils.JWTTools;
import it.NoteLock.Utils.PasswordEncoder;
import jakarta.validation.Valid;

/**
 * AUTHENTICATION REST CONTROLLER
 * 
 * @author Francesco Valentini
 * @author Giulia Balestra
 */
@RestController
@RequestMapping("/auth")
public class AuthController {
    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

	@Autowired
	UserRepository repo;

	@Autowired
	JWTTools jwtUtils;

	@Autowired
	PasswordEncoder argon2encoder;

	@GetMapping(value = "/whoami")
	public ResponseEntity<Object> whoami(@AuthenticationPrincipal UserAccount utente) {
		if (utente != null) {
			return new ResponseEntity<>(utente, HttpStatus.OK);
		}
		throw new UnauthorizedException("Unauthorized.");

	}

	@PostMapping(value = "/login")
	public ResponseEntity<Object> login(@Valid @RequestBody LoginDTO utente) {
		UserAccount account = repo.findByUsername(utente.getUsername()).get();

		boolean validPassword = argon2encoder.verifyPassword(utente.getPassword(), account.getPassword());

		if (account != null && validPassword) {
			logger.info(utente.getUsername() + " Logged in.");
			return new ResponseEntity<>(jwtUtils.signToken(account.getId()), HttpStatus.OK);
		}
		throw new InvalidCredentialsException("Invalid Credentials.");
	}

	@PostMapping(value = "/register")
	public ResponseEntity<Object> register(@Valid @RequestBody RegisterDTO utente) {
		if (!repo.findByUsername(utente.getUsername()).isPresent()) {
			String encodedPassword = argon2encoder.encodePassword(utente.getPassword());
			UserAccount account = new UserAccount(UUID.randomUUID().toString(), utente.getNome(), utente.getCognome(),
					utente.getUsername(), utente.getEmail(), encodedPassword);
			repo.save(account);
			logger.info(utente.getUsername() + " Registered.");
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>("Username already in use", HttpStatus.CONFLICT);
	}
}

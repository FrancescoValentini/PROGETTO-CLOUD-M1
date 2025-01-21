package it.NoteLock.TESTDATA;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import it.NoteLock.Exceptions.GlobalExceptionHandler;
import it.NoteLock.Models.UserAccount;
import it.NoteLock.Repositories.UserRepository;
import it.NoteLock.Utils.PasswordEncoder;

@Component
public class DataInitializer implements CommandLineRunner  {
	
	@Autowired
	UserRepository usersRepo;
	
	@Autowired
	PasswordEncoder argon2Encoder;
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	@Override
	public void run(String... args) throws Exception {
		
		String password1 = argon2Encoder.encodePassword("admin");
		String password2 = argon2Encoder.encodePassword("utente");
		
		
		UserAccount user1 = new UserAccount("c64b769e-0bb9-4627-aa02-276e383efea7", "Admin_Nome", "Admin_Cognome", "admin", "Admin@admin.it", password1, new SimpleGrantedAuthority("Admin"));
		
		UserAccount user2 = new UserAccount("7527e5fb-b597-48ef-b3fd-f1ba129e98b2", "User_Nome", "User_Cognome", "user", "user@gmail.com", password2);
		
		usersRepo.save(user1);
		usersRepo.save(user2);
		
		logger.info("Loaded fake data");
	}

}

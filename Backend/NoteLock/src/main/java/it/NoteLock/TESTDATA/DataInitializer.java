package it.NoteLock.TESTDATA;

import java.util.Date;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import it.NoteLock.Exceptions.GlobalExceptionHandler;
import it.NoteLock.Models.EncryptionOptions;
import it.NoteLock.Models.Folder;
import it.NoteLock.Models.Note;
import it.NoteLock.Models.UserAccount;
import it.NoteLock.Repositories.FolderRepository;
import it.NoteLock.Repositories.NoteRepository;
import it.NoteLock.Repositories.UserRepository;
import it.NoteLock.Utils.PasswordEncoder;

@Component
public class DataInitializer implements CommandLineRunner  {
	
	@Autowired
	UserRepository usersRepo;
	
	@Autowired
	FolderRepository repoCartelle;
	
	@Autowired
	NoteRepository repoNote;
	
	@Autowired
	PasswordEncoder argon2Encoder;
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	@Override
	public void run(String... args) throws Exception {
		
		String password1 = argon2Encoder.encodePassword("admin");
		String password2 = argon2Encoder.encodePassword("utente");
		
		
		UserAccount user1 = new UserAccount("c64b769e-0bb9-4627-aa02-276e383efea7", "Admin_Nome", "Admin_Cognome", "admin", "Admin@admin.it", password1, new SimpleGrantedAuthority("Admin"));
		
		UserAccount user2 = new UserAccount("7527e5fb-b597-48ef-b3fd-f1ba129e98b2", "User_Nome", "User_Cognome", "user", "user@gmail.com", password2);
		
		Folder f = new Folder("02464980-2f22-4bf1-b01e-d0ce143528fb","Prova-admin-1",new Date(System.currentTimeMillis()),user1);
		Folder f1 = new Folder("1bc24a4c-f13d-42d4-a190-886ef238baa4","Prova-user-1",new Date(System.currentTimeMillis()),user2);
		
		Note n = new Note("219132f3-06c5-41cb-8807-13126dbfa1ca",
				new Date(System.currentTimeMillis()),
				"Titolo nota di Admin",
				"Nota di admin",
				user1,
				f, 
				EncryptionOptions.PLAINTEXT);
		
		Note n1 = new Note("1b5b7ed9-dec9-4c68-b787-5347ed901712",
				new Date(System.currentTimeMillis()),
				"Titolo nota di User",
				"Nota di User",
				user2,
				f1, 
				EncryptionOptions.PLAINTEXT);
		
		usersRepo.save(user1);
		usersRepo.save(user2);
		
		repoCartelle.save(f);
		repoCartelle.save(f1);
		
		repoNote.save(n);
		repoNote.save(n1);
		
		logger.info("Loaded fake data");
	}

}

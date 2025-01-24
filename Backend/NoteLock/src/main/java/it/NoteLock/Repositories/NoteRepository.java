package it.NoteLock.Repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import it.NoteLock.Models.Note;

public interface NoteRepository extends CrudRepository<Note, String>{

	@Modifying
	@Query("DELETE FROM Note n WHERE n.id = :id")
	void deleteNoteById(@Param("id") String id);
	
	@Query("SELECT n FROM Note n WHERE n.utente.id = :userID")
	List<Note> getUserNotes(@Param("userID") String userID);
	
	@Query("SELECT CASE WHEN COUNT(n) > 0 THEN true ELSE false END FROM Note n WHERE n.id = :noteID AND n.utente.id = :userID")
	boolean isNoteOwnedByUser(@Param("userID") String userID, @Param("noteID") String noteID);

	@Query("SELECT n FROM Note n WHERE n.id = :noteID AND n.utente.id = :userID")
	Optional<Note> findNoteByUser(@Param("userID") String userID, @Param("noteID") String noteID);


}

package it.NoteLock.Repositories;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import it.NoteLock.Models.Note;

public interface NoteRepository extends CrudRepository<Note, String>{

	@Modifying
	@Query("DELETE FROM Note n WHERE n.id = :id")
	void deleteNoteById(@Param("id") String id);
}

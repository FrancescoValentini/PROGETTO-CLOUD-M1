package it.NoteLock.Repositories;

import org.springframework.data.repository.CrudRepository;

import it.NoteLock.Models.Note;

public interface NoteRepository extends CrudRepository<Note, String>{

}

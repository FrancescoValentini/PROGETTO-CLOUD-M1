package it.NoteLock.Repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import it.NoteLock.Models.Folder;
import it.NoteLock.Models.Note;

public interface FolderRepository extends CrudRepository<Folder, String>{
	Optional<Folder> findByfolderName(@Param("folderName") String folderName);
	
	@Modifying
	@Query("DELETE FROM Folder f WHERE f.id = :id")
	void deleteFolderById(@Param("id") String id);
	
    @Query("SELECT n FROM Note n WHERE n.cartella.id = :folderId")
    List<Note> findAllNotesByFolderId(@Param("folderId") String folderId);
    
    @Query("SELECT f FROM Folder f WHERE f.utente.id = :userID")
    List<Folder> getUserFolders(@Param("userID") String userID);
    
	@Query("SELECT f FROM Folder f WHERE f.id = :folderID AND f.utente.id = :userID")
    Optional<Folder> findFolderByUser(@Param("userID") String userID, @Param("folderID") String folderID);

	@Query("SELECT CASE WHEN COUNT(f) > 0 THEN true ELSE false END FROM Folder f WHERE f.id = :folderID AND f.utente.id = :userID")
	boolean isFolderOwnedByUser(@Param("userID") String userID, @Param("folderID") String folderID);
}

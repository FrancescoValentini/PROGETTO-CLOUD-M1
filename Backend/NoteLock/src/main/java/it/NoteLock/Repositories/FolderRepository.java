package it.NoteLock.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import it.NoteLock.Models.Folder;

public interface FolderRepository extends CrudRepository<Folder, String>{
	Optional<Folder> findByfolderName(@Param("folderName") String folderName);
	
	@Modifying
	@Query("DELETE FROM Folder f WHERE f.id = :id")
	void deleteFolderById(@Param("id") String id);

}

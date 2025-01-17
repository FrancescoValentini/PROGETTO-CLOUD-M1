package it.NoteLock.Repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import it.NoteLock.Models.Folder;
import it.NoteLock.Models.UserAccount;

public interface FolderRepository extends CrudRepository<Folder, String>{
	Optional<Folder> findByfolderName(@Param("folderName") String folderName);
}

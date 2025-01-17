package it.NoteLock.Repositories;

import org.springframework.data.repository.CrudRepository;

import it.NoteLock.Models.Folder;

public interface FolderRepository extends CrudRepository<Folder, String>{

}

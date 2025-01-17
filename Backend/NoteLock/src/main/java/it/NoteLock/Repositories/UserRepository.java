package it.NoteLock.Repositories;

import org.springframework.data.repository.CrudRepository;

import it.NoteLock.Models.UserAccount;

public interface UserRepository extends CrudRepository<UserAccount, String>{

}

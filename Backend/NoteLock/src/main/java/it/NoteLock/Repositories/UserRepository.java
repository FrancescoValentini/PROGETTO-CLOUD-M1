package it.NoteLock.Repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import it.NoteLock.Models.UserAccount;

public interface UserRepository extends CrudRepository<UserAccount, String>{
    Optional<UserAccount> findByUsername(@Param("username") String username);
}

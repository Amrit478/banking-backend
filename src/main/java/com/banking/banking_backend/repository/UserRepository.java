package com.banking.banking_backend.repository;

import com.banking.banking_backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
//@Repository
//
//Marks a class as a Data Access Object (DAO) that interacts with the database.
//Database access using by application.properties

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByIdAndName(int id, String name);
}

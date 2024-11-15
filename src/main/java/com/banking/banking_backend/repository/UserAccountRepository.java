package com.banking.banking_backend.repository;
import com.banking.banking_backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserAccountRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}

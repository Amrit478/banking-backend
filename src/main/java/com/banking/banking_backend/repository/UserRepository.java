package com.banking.banking_backend.repository;
import com.banking.banking_backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByIdAndName(int id, String name);
}

package com.banking.banking_backend.controller;
import com.banking.banking_backend.model.Login;
import com.banking.banking_backend.model.User;
import com.banking.banking_backend.repository.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserAccountRepository userAccountRepository;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody User userAccount) {
        if (userAccountRepository.findByUsername(userAccount.getUsername()).isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Username already exists!");
        }

        userAccountRepository.save(userAccount);
        return ResponseEntity.ok("User registered successfully!");
    }
    @GetMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody Login authRequest) {
        Optional<User> user = userAccountRepository.findByUsername(authRequest.getUsername());
        if (user.isPresent() && user.get().getPassword().equals(authRequest.getPassword())) {
            return ResponseEntity.ok("Login successful!");
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password!");
    }
}

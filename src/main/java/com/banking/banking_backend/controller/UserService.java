package com.banking.banking_backend.controller;
import com.banking.banking_backend.model.User;
import com.banking.banking_backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestParam String username, @RequestParam String password) {
        User user = userRepository.findByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            return ResponseEntity.ok("Login successful!"); // Return success message
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }
    }


    @PostMapping("/updatelogin")
    public boolean registerUser(String username, String password) {
        // Check if the username already exists
        if (userRepository.findByUsername(username) != null) {
            return false;
        }
        User newUser = new User();
        newUser.setUsername(username);
        newUser.setPassword(password);  // Plain-text storage (for testing only)

        userRepository.save(newUser);
        return true;
    }
}

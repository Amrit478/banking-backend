package com.banking.banking_backend.controller;
import com.banking.banking_backend.exception.UsernotFoundException;
import com.banking.banking_backend.model.User;
import com.banking.banking_backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

//@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/user")
public class Account {

    @Autowired
    private UserRepository userRepository;

    // Retrieve all users
    @GetMapping("/all")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Create a new user
    @PostMapping("/create")
    public User addUser(@RequestBody User newUser) {
        return userRepository.save(newUser);
    }

    // Delete a user by ID
    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteUser(@RequestBody Map<String, Long> request) {
        Long id = request.get("id");

        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return ResponseEntity.ok("User deleted successfully.");
        } else {
            throw new UsernotFoundException("User not found with id: " + id);
        }
    }

    // Update an existing user
    @PutMapping("/update")
    public ResponseEntity<User> updateUser(@RequestBody User userDetails) {
        Long id = userDetails.getId();

        User user = userRepository.findById(id)
                .orElseThrow(() -> new UsernotFoundException("User not found with id " + id));

        // Update user details
        user.setName(userDetails.getName());
        user.setEmail(userDetails.getEmail());
        user.setBalance(userDetails.getBalance());
        user.setSalary(userDetails.getSalary());
        user.setRent(userDetails.getRent());
        user.setAge(userDetails.getAge());

        User updatedUser = userRepository.save(user);
        return ResponseEntity.ok(updatedUser);
    }
}

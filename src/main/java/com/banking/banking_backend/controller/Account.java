package com.banking.banking_backend.controller;
import com.banking.banking_backend.exception.Methodmismatch;
import com.banking.banking_backend.exception.UsernotFoundException;
import com.banking.banking_backend.model.User;
import com.banking.banking_backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class Account {

    @Autowired
    private UserRepository userRepository;

    // Retrieve all users
    @GetMapping("/findall")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    // Create a new user
    @PostMapping("/adduser")
    public User addUser(@RequestBody User newUser) {
        return userRepository.save(newUser);
    }

    // Delete a user by ID
    @DeleteMapping("/deleteuser")
    public String deleteUser(@RequestBody Map<String, Long> request) {
        Long id = request.get("id");

        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return "User deleted successfully.";
        } else {
            throw new UsernotFoundException("User not found with id: " + id);
        }
    }
}
package com.banking.banking_backend.controller;

import com.banking.banking_backend.repository.UserRepository;
import com.banking.banking_backend.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    // Create a new user
    @PostMapping("/add")
    public User addUser(@RequestBody User newUser) {
        return userRepository.save(newUser);
    }

    // Retrieve all users
    @GetMapping("/all")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Delete a user by ID
    @DeleteMapping("/delete")
    public String deleteUser(@RequestParam Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return "User deleted successfully.";
        } else {
            return "User not found.";
        }
    }

    // Check credit card approval
    @GetMapping("/approval")
    public String getApproval(@RequestParam double balance) {
        return balance > 100 ? "Approved for credit card" : "Rejected for credit card";
    }

    // Add money to a user’s balance
    @PostMapping("/addBalance")
    public User addBalance(@RequestParam Long id, @RequestParam double amount) {
        User user = userRepository.findById(id).orElseThrow();
        user.setBalance(user.getBalance() + amount);
        return userRepository.save(user);
    }

    // Check account balance
    @GetMapping("/balance")
    public double getAccountBalance(@RequestParam Long id) {
        return userRepository.findById(id).orElseThrow().getBalance();
    }

    // Deduct money from a user’s balance
    @PostMapping("/deductBalance")
    public User deductBalance(@RequestParam Long id, @RequestParam double amount) {
        User user = userRepository.findById(id).orElseThrow();
        user.setBalance(user.getBalance() - amount);
        return userRepository.save(user);
    }

    @GetMapping("/Loanapproval")
    public String getLoanApprove(@RequestParam double balance) {
        return balance > 0 ? "Approved for Loan" : "Rejected for Loan";
    }

    // Placeholder for product exploration (update based on your product requirements)
    @GetMapping("/exploreProducts")
    public List<User> exploreProducts() {
        return userRepository.findAll();
    }
}

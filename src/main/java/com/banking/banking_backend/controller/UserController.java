package com.banking.banking_backend.controller;

import com.banking.banking_backend.exception.Methodmismatch;
import com.banking.banking_backend.repository.UserRepository;
import com.banking.banking_backend.model.User;
import com.banking.banking_backend.exception.UsernotFoundException;
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
            throw new UsernotFoundException("User not found with id: " + id);
        }
    }

    // Check credit card approval
    @GetMapping("/approval")
    public String getApproval(@RequestParam double balance) {
        return balance > 100 ? "Approved for credit card" : "Rejected for credit card";
    }

    // Add money to a user’s balance
    // Add money to a user’s balance
    @PostMapping("/addBalance")
    public User addBalance(@RequestParam Long id, @RequestParam String amount) {
        try {
            double parsedAmount = Double.parseDouble(amount); // Validating data type
            User user = userRepository.findById(id)
                    .orElseThrow(() -> new UsernotFoundException("User not found with id: " + id));
            user.setBalance(user.getBalance() + parsedAmount);
            return userRepository.save(user);
        } catch (NumberFormatException e) {
            throw new Methodmismatch("balance", "number");
        }
    }
    // Check account balance
    @GetMapping("/balance")
    public double getAccountBalance(@RequestParam Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UsernotFoundException("User not found with id: " + id))
                .getBalance();
    }

    // Deduct money from a user’s balance
    @PostMapping("/deductBalance")
    public User deductBalance(@RequestParam Long id, @RequestParam double amount) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UsernotFoundException("User not found with id: " + id));
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

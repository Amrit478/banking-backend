package com.banking.banking_backend.controller;

import com.banking.banking_backend.exception.Methodmismatch;
import com.banking.banking_backend.exception.UsernotFoundException;
import com.banking.banking_backend.model.User;
import com.banking.banking_backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/user")
public class Balance {

    @Autowired
    private UserRepository userRepository;

    // Check account balance
    @GetMapping("/checkbalance")
    public double getAccountBalance(@RequestBody Map<String, Long> request) {
        Long id = request.get("id");
        return userRepository.findById(id)
                .orElseThrow(() -> new UsernotFoundException("User not found with id: " + id))
                .getBalance();
    }

    // Deduct money from a user’s balance
    @PostMapping("/deductBalance")
    public User deductBalance(@RequestBody Map<String, String> request) {
        try {
            Long id = Long.parseLong(request.get("id"));
            double amount = Double.parseDouble(request.get("amount"));
            User user = userRepository.findById(id)
                    .orElseThrow(() -> new UsernotFoundException("User not found with id: " + id));
            user.setBalance(user.getBalance() - amount);
            return userRepository.save(user);
        } catch (NumberFormatException e) {
            throw new Methodmismatch("balance", "number");
        }
    }

    // Add money to a user’s balance
    @PostMapping("/addBalance")
    public User addBalance(@RequestBody Map<String, String> request) {
        try {
            Long id = Long.parseLong(request.get("id"));
            double amount = Double.parseDouble(request.get("amount"));
            User user = userRepository.findById(id)
                    .orElseThrow(() -> new UsernotFoundException("User not found with id: " + id));
            user.setBalance(user.getBalance() + amount);
            return userRepository.save(user);
        } catch (NumberFormatException e) {
            throw new Methodmismatch("balance", "number");
        }
    }
}

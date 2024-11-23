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

    // Add money to a user’s balance
    @PostMapping("/add")
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

    // Deduct money from a user’s balance
    @PostMapping("/deduct")
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
// Check account balance
        @GetMapping("/check")
        public double getAccountBalance(@RequestParam Long id) {  // Use @RequestParam for query parameter
            return userRepository.findById(id)
                    .orElseThrow(() -> new UsernotFoundException("User not found with id: " + id))
                    .getBalance();
        }
}

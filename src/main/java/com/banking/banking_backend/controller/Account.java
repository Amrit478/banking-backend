package com.banking.banking_backend.controller;
import com.banking.banking_backend.exception.Methodmismatch;
import com.banking.banking_backend.exception.UsernotFoundException;
import com.banking.banking_backend.model.User;
import com.banking.banking_backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/user")
public class Account {

    @Autowired
    private UserRepository userRepository;

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
}

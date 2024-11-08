package com.banking.banking_backend.controller;

import com.banking.banking_backend.exception.UsernotFoundException;
import com.banking.banking_backend.model.User;
import com.banking.banking_backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class CreditCards {

    // Check credit card approval
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/approval")
    public String getApproval(@RequestBody Map<String, String> request) {
        int id = Integer.parseInt(request.get("id"));
        String name = request.get("name");
        int age = Integer.parseInt(request.get("age"));
        int salary = Integer.parseInt(request.get("salary"));

        // Check if a user with the given id and name exists in the database
        Optional<User> userOptional = userRepository.findByIdAndName(id, name);

        if (userOptional.isPresent()) {
            // Proceed with approval decision
            return (salary > 50000 && age >= 18) ?
                    "Approved for credit card" :
                    "Rejected for credit card";
        } else {
            // If no user with the given id and name is found
            return "User not found. Approval cannot be processed.";
        }
    }

    // Check a user by ID for credit increase eligibility based on balance and apply increase if eligible
    @PostMapping("/CreditIncrease")
    public String checkCreditIncreaseEligibility(@RequestBody Map<String, Long> request) {
        Long id = request.get("id");
        Long increaseAmount = request.get("increaseAmount"); // Get the increase amount from the request

        if (increaseAmount == null || increaseAmount <= 0) {
            return "Invalid increase amount provided.";
        }

        if (userRepository.existsById(id)) {
            User user = userRepository.findById(id).orElseThrow(() -> new UsernotFoundException("User not found with id: " + id));

            // Check the balance to make the decision
            if (user.getBalance() >= 1000) { // Adjust the balance threshold as needed
                double newBalance = user.getBalance() + increaseAmount;
                user.setBalance(newBalance);
                userRepository.save(user); // Save the updated user balance

                return "User is eligible for credit increase. Balance increased by " + increaseAmount + ", new balance: " + newBalance;
            } else {
                return "User is not eligible for credit increase due to insufficient balance.";
            }
        } else {
            throw new UsernotFoundException("User not found with id: " + id);
        }
    }
}

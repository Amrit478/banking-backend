package com.banking.banking_backend.controller;

import com.banking.banking_backend.model.User;
import com.banking.banking_backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}

package com.banking.banking_backend.controller;
import com.banking.banking_backend.exception.Methodmismatch;
import com.banking.banking_backend.repository.UserRepository;
import com.banking.banking_backend.model.User;
import com.banking.banking_backend.exception.UsernotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class LoanApproval {

    @Autowired
    private UserRepository userRepository;

    //Need to add age entity in the database

    @PostMapping("/loanApproval")
    public String getLoanApprove(@RequestBody Map<String, Object> request) {
        Long id = Long.valueOf((int) request.get("id")); // Get the user ID from the request
        int rent = (int) request.get("rent");  // Retrieve rent as Integer
        int age = (int) request.get("age");
        int salary = (int) request.get("salary");

        // Check if user exists by ID
        User user = userRepository.findById(id).orElseThrow(() -> new UsernotFoundException("User  not found with id: " + id));

        // Loan approval criteria
        if (salary > 50000 && age >= 18 && rent < (0.4 * salary)) {
            return "Approved for Loan";
        } else {
            return "Rejected for Loan";
        }
    }
}

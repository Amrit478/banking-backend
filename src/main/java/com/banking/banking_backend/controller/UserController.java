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
public class UserController {

    @Autowired
    private UserRepository userRepository;

    // Create a new user
    @PostMapping("/adduser")
    public User addUser(@RequestBody User newUser) {
        return userRepository.save(newUser);
    }

    // Retrieve all users
    @GetMapping("/findall")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Delete a user by ID
    @DeleteMapping("/deleteuser")
    public String deleteUser(@RequestParam Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return "User deleted successfully.";
        } else {
            throw new UsernotFoundException("User not found with id: " + id);
        }
    }

    @PostMapping("/loanApproval")
    public String getLoanApprove(@RequestBody Map<String, Object> request) {
        double rent = (int) request.get("rent");
        int age = (int) request.get("age");
        int salary = (int) request.get("salary");

        if (salary > 50000 && age >= 18 && rent < (0.4 * salary)) {
            return "Approved for Loan";
        } else {
            return "Rejected for Loan";
        }
    }


}

package com.banking.banking_backend.controller;

import com.banking.banking_backend.repository.UserRepository;
import com.banking.banking_backend.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/adduser")
    public User newUser(@RequestBody User newUser) {
        return userRepository.save(newUser);
    }

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @PostMapping("/deluser")
    public List<User> deluser(@RequestBody User user) {
        userRepository.delete(user);
        return userRepository.findAll();
    }

    @GetMapping("/getapproval")
    public String getApproval(@RequestParam("balance") double balance) {
        return balance > 100 ? "Approved for credit card" : "Rejected for credit card";
    }

    @PostMapping("/addmoney")
    public User addMoney(@RequestParam("id") Long userId, @RequestParam("amount") double amount) {
        User user = userRepository.findById(userId).orElseThrow();
        user.setbalance(user.getbalance() + amount);
        return userRepository.save(user);
    }

    @GetMapping("/checkbalance")
    public double getAccountBalance(@RequestParam("id") Long userId) {
        return userRepository.findById(userId).orElseThrow().getbalance();
    }
}

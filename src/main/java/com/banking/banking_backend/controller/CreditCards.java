package com.banking.banking_backend.controller;

import com.banking.banking_backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/user")
public class CreditCards {

    @Autowired
    private UserRepository userRepository;

    // Check credit card approval
    @PostMapping("/approval")
    public String getApproval(@RequestBody Map<String, String> request) {
        int age = Integer.parseInt(request.get("age"));
        int salary = Integer.parseInt(request.get("salary"));
        return (salary > 50000 && age >= 18) ? "Approved for credit card" : "Rejected for credit card";
    }
}

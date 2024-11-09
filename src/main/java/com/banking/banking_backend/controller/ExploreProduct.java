package com.banking.banking_backend.controller;
import com.banking.banking_backend.exception.Methodmismatch;
import com.banking.banking_backend.exception.UsernotFoundException;
import com.banking.banking_backend.model.User;
import com.banking.banking_backend.repository.UserRepository;
import org.hibernate.sql.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class ExploreProduct {

    @Autowired
    private UserRepository userRepository;

    // Placeholder for product exploration (update based on your product requirements)
    @GetMapping("/exploreProducts")
    public List<User> exploreProducts() {
        return userRepository.findAll();
    }

}

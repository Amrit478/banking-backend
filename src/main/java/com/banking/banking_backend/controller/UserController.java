package com.banking.banking_backend.controller;
import com.banking.banking_backend.model.Login;
import com.banking.banking_backend.model.User;
import com.banking.banking_backend.repository.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    @Autowired
    private UserAccountRepository userAccountRepository;
    private User userService;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody User userAccount) {
        if (userAccountRepository.findByUsername(userAccount.getUsername()).isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Username already exists!");
        }
        userAccountRepository.save(userAccount);
        return ResponseEntity.ok("User registered successfully!");
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody Map<String, String> credentials) {
        String username = credentials.get("username");
        String password = credentials.get("password");

        Optional<User> user = userAccountRepository.findByUsername(username);

        if (user.isPresent() && user.get().getPassword().equals(password)) {
            User loggedUser = user.get();

            // Create a new User object to send as JSON response
            User response = new User();
            response.setId(loggedUser.getId());
            response.setName(loggedUser.getName());
            response.setEmail(loggedUser.getEmail());
            response.setBalance(loggedUser.getBalance());
            response.setSalary(loggedUser.getSalary());
            response.setRent(loggedUser.getRent());
            response.setAge(loggedUser.getAge());
            response.setCreditbalance(loggedUser.getCreditbalance());
            response.setLoan(loggedUser.getLoan());
            return ResponseEntity.ok(response); // Return JSON response
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password!");
    }

}
// WORKING WITH POSTMAN THIS METHOD
//    @PostMapping("/login")
//    public ResponseEntity<?> loginUser(@RequestBody Login loginRequest) {
//        String username = loginRequest.getUsername();
//        String password = loginRequest.getPassword();
//
//        Optional<User> user = userAccountRepository.findByUsername(username);
//
//        if (user.isPresent() && user.get().getPassword().equals(password)) {
//            User loggedUser = user.get();
//
//            // Create a new User object to send as JSON response
//            User response = new User();
    //User response = new User();
    //            response.setId(loggedUser.getId());
    //        response.setName(loggedUser.getName());
    //        response.setEmail(loggedUser.getEmail());
    //        response.setBalance(loggedUser.getBalance());
    //        response.setSalary(loggedUser.getSalary());
    //        response.setRent(loggedUser.getRent());
    //        response.setAge(loggedUser.getAge());
    //        response.setCreditbalance(loggedUser.getCreditbalance());
    //        response.setLoan(loggedUser.getLoan());
    //        return ResponseEntity.ok(response);//
//            return ResponseEntity.ok(response); // Return JSON response
//        }
//
//        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password!");
//    }
//}
package com.banking.banking_backend.controller;
import com.banking.banking_backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.banking.banking_backend.model.User;
import java.util.List;

@RestController
public class Loaneligible {
    
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/Loanapplication"){
        List<balances> getBalances(){
            return userRepository.getById(id);
        }
        @PostMapping("/approved"){
            //How much is the that 
        }
    }
}

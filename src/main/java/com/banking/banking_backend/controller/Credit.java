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
public class Credit {
    
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/getapproval")
    List<Balance> balances;
    if(balances > 100){
        System.out.println("Approved for credit card");
    }
    else {
        System.out.println("Reject for credit card");
    }
    //Now write a method that how much that person is aprroved here
}

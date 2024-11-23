package com.banking.banking_backend.controller;
import com.banking.banking_backend.exception.UsernotFoundException;
import com.banking.banking_backend.model.User;
import com.banking.banking_backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/user")
public class CreditCards {

    // Check credit card approval
    @Autowired
    private UserRepository userRepository;

    //Checks if the user is approved for credit card
    @PostMapping("/approval")
    public String getApproval(@RequestBody Map<String, String> request) {
        int id = Integer.parseInt(request.get("id"));
        String name = request.get("name");
        int age = Integer.parseInt(request.get("age"));
        int salary = Integer.parseInt(request.get("salary"));

        // Check if a user with the given id and name exists in the database
        Optional<User> userOptional = userRepository.findByIdAndName(id, name);

        if (userOptional.isPresent()) {
            // Proceed with approval decision and after approving it should open credit-type bottom method
            return (salary > 5000 && age >= 18) ?
                    "Approved for credit card" :
                    "Rejected for credit card";
        } else {
            // If no user with the given id and name is found
            return "User not found. Approval cannot be processed.";
        }
    }

    // Check a user by ID for credit increase eligibility based on balance and apply increase if eligible
    @PostMapping("/credit-type")
    public ResponseEntity<String> getCreditCardType(@RequestBody Map<String, Object> request) {
        // Extract the id from the request
        Long userId = Long.valueOf(request.get("id").toString());
        // Fetch the user by id from the database
        Optional<User> userOptional = userRepository.findById(userId);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            double balance = user.getBalance();

            // Initialize card type variable
            String cardType;
            if (balance > 1000) {
                user.setCreditbalance(25000);  // Update the credit balance
                cardType = "It is Platinum Card and your limit is $25000";
            } else if (balance > 500) {
                user.setCreditbalance(10000);  // Update the credit balance
                cardType = "It is Gold Card and your limit is $10000";
            } else if (balance >= 0) {
                user.setCreditbalance(5000);  // Update the credit balance
                cardType = "It is Silver Card and your limit is $5000";
            } else {
                cardType = "No Card Eligibility";
            }

            // Save the updated user object to the database (only once)
            userRepository.save(user);

            // Return the card type as the response
            return ResponseEntity.ok("User is eligible for: " + cardType + " and the user balance is " + user.getCreditbalance());
        } else {
            // User not found
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("User with ID " + userId + " does not exist.");
        }
    }

    //This method will open after the above method
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
            if (user.getSalary() >= 1000) { // Adjust the balance threshold as needed
                int newBalance = (int) (user.getCreditbalance() + increaseAmount);
                user.setCreditbalance(newBalance);
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

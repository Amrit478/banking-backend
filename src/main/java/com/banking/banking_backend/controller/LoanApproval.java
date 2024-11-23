package com.banking.banking_backend.controller;
import com.banking.banking_backend.exception.Methodmismatch;
import com.banking.banking_backend.repository.UserRepository;
import com.banking.banking_backend.model.User;
import com.banking.banking_backend.exception.UsernotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/user")
public class LoanApproval {

    @Autowired
    private UserRepository userRepository;

    //Might have to remove this method
    @PostMapping("/loan")
    public String getLoanApprove(@RequestBody Map<String, Object> request) {
        Long id = Long.valueOf((int) request.get("id")); // Get the user ID from the request
        int rent = (int) request.get("rent");  // Retrieve rent as Integer
        int age = (int) request.get("age");
        int salary = (int) request.get("salary");

        // Check if user exists by ID
        User user = userRepository.findById(id).orElseThrow(() -> new UsernotFoundException("User  not found with id: " + id));

        // Loan approval criteria
        if (salary > 50000 && age >= 18 && rent < (0.4 * salary)) {
            return "Qualify for next steps for Loan";
        } else {
            return "Rejected for Loan";
        }
    }
    //The method works but does not make changes into database
        @PostMapping("/approval-amount")
        public ResponseEntity<String> getLoanApprovalAmount(@RequestBody Map<String, Object> request) {
            try {
            Long id = Long.valueOf(request.get("id").toString());
            int rent = Integer.parseInt(request.get("rent").toString());
            int salary = Integer.parseInt(request.get("salary").toString());

            // Check if user exists by ID
            User user = userRepository.findById(id)
                    .orElseThrow(() -> new UsernotFoundException("User not found with id: " + id));

            // Loan approval criteria
            if (salary > 500 && rent < (0.4 * salary)) {
                int approvedLoanAmount = (int) (0.8 * salary); // 80% of salary
                user.setCreditbalance(approvedLoanAmount); // Set loan amount
                userRepository.save(user); // Save updated user to the database
                return ResponseEntity.ok("Approved Loan Amount: " + approvedLoanAmount +
                        ". The due date for loan payment is the 1st of every month.");
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("Loan Rejected: Not eligible for loan amount calculation.");
            }
        } catch (NumberFormatException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Invalid input format: Please provide valid numeric values for id, rent, age, and salary.");
        }
    }

    @PostMapping("/loan-pay")
    public ResponseEntity<String> loanPay(@RequestBody Map<String, Object> request) {
        try {
            // Extract input data from request
            Long id = Long.valueOf((int) request.get("id"));
            String name = (String) request.get("name");
            double payment = Double.parseDouble(request.get("payment").toString());

            // Check if the user exists
            User user = userRepository.findById(id)
                    .orElseThrow(() -> new UsernotFoundException("User not found with id: " + id));

            // Check if the user has an existing loan balance and need to create new entity loan
            if ((user.getCreditbalance() == 0) || (user.getBalance() <= 0)) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("No loan balance available for payment.");
            }

            // Validate payment amount
            if (payment > user.getCreditbalance()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("Payment Rejected: Amount exceeds remaining loan balance of " + user.getBalance());
            }

            // Deduct payment from loan balance
            user.setBalance(user.getCreditbalance() - payment);
            userRepository.save(user); // Update user in the database

            return ResponseEntity.ok("Payment Successful: " + payment
                    + " deducted. Remaining Loan Balance: " + user.getCreditbalance());
        } catch (NumberFormatException e) {
            throw new Methodmismatch("payment", "number");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred: " + e.getMessage());
        }
    }


}
//Need to make front end for car loan and home loan

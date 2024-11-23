package com.banking.banking_backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "user")
public class User {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private String username;
        private String password;
        private String name;
        private String email;
        private double balance;
        private double salary;
        private double rent;
        private int age;
        private double creditbalance;
        private double loan;

        public User(){

        };

        public User(Long id, String username, String password, String name, String email, double balance, double salary, double rent, int age, double creditbalance, double loan) {
            this.id = id;
            this.username = username;
            this.password = password;
            this.name = name;
            this.email = email;
            this.balance = balance;
            this.salary = salary;
            this.rent = rent;
            this.age = age;
            this.creditbalance = creditbalance;
            this.loan = loan;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public double getBalance() {
            return balance;
        }

        public void setBalance(double balance) {
            this.balance = balance;
        }

        public double getSalary() {
            return salary;
        }

        public void setSalary(double salary) {
            this.salary = salary;
        }

        public double getRent() {
            return rent;
        }

        public void setRent(double rent) {
            this.rent = rent;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }
        public double getCreditbalance(){
            return creditbalance;
        }

    public void setCreditbalance(double creditbalance) {
        this.creditbalance = creditbalance;
    }

    public double getLoan() {
        return loan;
    }

    public void setLoan(double loan) {
        this.loan = loan;
    }
}

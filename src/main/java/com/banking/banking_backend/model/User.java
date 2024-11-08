package com.banking.banking_backend.model;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "user") // Optional, only if the table name differs from the class name
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Specifying ID generation strategy
    private Long id;
    private String username;
    private String name;
    private String email;
    private double balance;
    private int salary;
    private int rent;
    private int age;

    // Default constructor
    public User() {
    }

    // Parameterized constructor
    public User(Long id, String username, String name, String email, double balance, int salary, int rent, int age) {
        this.id = id;
        this.username = username;
        this.name = name;
        this.email = email;
        this.balance = balance;
        this.salary = salary;
        this.rent = rent;
        this.age = age;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public double getBalance() {
        return balance;
    }

    public int getSalary() {
        return salary;
    }
    public int getRent() {
        return rent;
    }
    public int getAge() {
        return age;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }
    public void setRent(int rent) {
        this.rent = rent;
    }

    public void setAge(int age) {
        this.age = age;
    }
}

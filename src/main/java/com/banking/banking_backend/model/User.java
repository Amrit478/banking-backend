package com.banking.banking_backend.model;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.lang.annotation.Inherited;
import javax.annotation.processing.Generated;

@Entity
public class User {

    //Need annotations at the top for the Id, Entity and the GeneratedValue

    @Id
    @GeneratedValue
    private Long id;
    private String username;
    private String name;
    private String email;
    private int balance;
    private int salary;
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
    public int getbalance() {
        return balance;
    }
    public void setbalance(double balance) {
        balance = balance;
    }
    public int getSalary() {
        return salary;
    }
    public void setSalary(int salary) {
        this.salary = salary;
    }


}

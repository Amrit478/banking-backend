package com.banking.banking_backend.model;
import java.lang.annotation.Inherited;
import javax.annotation.processing.Generated;

@Entity
public class User {

    //Need annotations at the top for the Id, Entity and the GeneratedValue

    @Id
    @GeneratedValue
    private Long id;
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
    public int getAccountbalance() {
        return Accountbalance;
    }
    public void setAccountbalance(int accountbalance) {
        Accountbalance = accountbalance;
    }
    public int getSalary() {
        return salary;
    }
    public void setSalary(int salary) {
        this.salary = salary;
    }
    private String username;
    private String name;
    private String email;
    private int Accountbalance;
    private int salary;
}

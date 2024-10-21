package com.banking.banking_backend.controller;

import java.lang.annotation.Inherited;

import javax.annotation.processing.Generated;

@Entity
public class UserController {

    @Id
    @GeneratedValue
    private Long id;
    private String username;
    private String name;
    private String email;
}

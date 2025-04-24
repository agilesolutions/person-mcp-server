package com.agilesolutions.mcp.model;

import jakarta.persistence.*;

@Entity
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private int age;
    private String nationality;
    @Enumerated(EnumType.STRING)
    private Gender gender;

    // ... getters and setters

}
package com.um.main.models;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "passengers")
public class Passenger implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void logIn() {
        // Implementation of logIn method
    }

    public void logOut() {
        // Implementation of logOut method
    }

    public void register() {
        // Implementation of register method
    }

}


package com.um.main.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import java.io.Serializable;

@Entity
@Table(name = "passengers")
public class Passenger implements Serializable {

    @Id
    @Getter @Setter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Getter @Setter
    @Column(name = "name")
    private String name;

    @NotNull
    @Getter @Setter
    @Column(name = "last_name")
    private String lastName;

    @NotNull
    @Getter @Setter
    @Column(name = "email")
    private String email;

    @NotNull
    @Getter @Setter
    @Column(name = "password")
    private String password;

}


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
@Table(name = "companies")
public class Company implements Serializable {

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
    @Column(name = "address")
    private String address;

    @NotNull
    @Getter @Setter
    @Column(name = "phone")
    private String phone;

}

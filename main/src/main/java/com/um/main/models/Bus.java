package com.um.main.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Column;
import java.io.Serializable;

import org.hibernate.annotations.Cascade;


@Entity
@Table(name = "buses")
public class Bus implements Serializable{

    @Id
    @Getter @Setter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter @Setter
    @Column(name = "plate_number")
    private String plateNumber;

    @Getter @Setter
    @Column(name = "capacity")
    private int capacity;

    @Getter @Setter
    @Column(name = "model")
    private String model;

    @ManyToOne
    @Getter @Setter
    @JoinColumn(name = "company_id")
    @Cascade(org.hibernate.annotations.CascadeType.PERSIST)
    private Company company;
}
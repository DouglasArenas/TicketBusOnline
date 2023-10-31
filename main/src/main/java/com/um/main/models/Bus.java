package com.um.main.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "plate_number")
    private String plateNumber;

    @Column(name = "capacity")
    private int capacity;

    @Column(name = "model")
    private String model;

    @ManyToOne
    @JoinColumn(name = "company_id")
    @Cascade(org.hibernate.annotations.CascadeType.PERSIST)
    private Company company;

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public boolean checkAvailability() {
        // Implementation of checkAvailability method
        return true;
    }
}
package com.um.main.models;

import javax.persistence.*;
import java.io.Serializable;


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
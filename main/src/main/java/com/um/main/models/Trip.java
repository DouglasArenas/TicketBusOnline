package com.um.main.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Column;
import java.util.Date;

import org.hibernate.annotations.Cascade;

import java.io.Serializable;

@Entity
@Table(name = "trips")
public class Trip implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "origin_id")
    @Cascade(org.hibernate.annotations.CascadeType.PERSIST)
    private City origin;

    @ManyToOne
    @JoinColumn(name = "destination_id")
    @Cascade(org.hibernate.annotations.CascadeType.PERSIST)
    private City destination;

    @Column(name = "departure_time")
    private Date departureTime;

    @Column(name = "duration")
    private int duration;

    @ManyToOne
    @JoinColumn(name = "bus_id")
    @Cascade(org.hibernate.annotations.CascadeType.PERSIST)
    private Bus bus;

    public City getOrigin() {
        return origin;
    }

    public void setOrigin(City origin) {
        this.origin = origin;
    }

    public City getDestination() {
        return destination;
    }

    public void setDestination(City destination) {
        this.destination = destination;
    }

    public Date getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(Date departureTime) {
        this.departureTime = departureTime;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public Bus getBus() {
        return bus;
    }

    public void setBus(Bus bus) {
        this.bus = bus;
    }

    public void assignBus() {
        // Implementation of assignBus method
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id2) {
    }
}




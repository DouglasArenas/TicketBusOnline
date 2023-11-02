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
import java.util.Date;

import org.hibernate.annotations.Cascade;

import java.io.Serializable;

@Entity
@Table(name = "trips")
public class Trip implements Serializable{

    @Id
    @Getter @Setter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @Getter @Setter
    @JoinColumn(name = "origin")
    private City origin;

    @ManyToOne
    @Getter @Setter
    @JoinColumn(name = "destination")
    private City destination;

    @Getter @Setter
    @Column(name = "departure_time")
    private Date departureTime;

    @Getter @Setter
    @Column(name = "duration")
    private int duration;

    @ManyToOne
    @Getter @Setter
    @JoinColumn(name = "bus_id")
    @Cascade(org.hibernate.annotations.CascadeType.PERSIST)
    private Bus bus;

}




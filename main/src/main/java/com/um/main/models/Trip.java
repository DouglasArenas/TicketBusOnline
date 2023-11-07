package com.um.main.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Column;

import org.hibernate.annotations.Cascade;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "trips")
public class Trip implements Serializable{

    @Id
    @Getter @Setter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne
    @Getter @Setter
    @JoinColumn(name = "origin")
    @Cascade(org.hibernate.annotations.CascadeType.PERSIST)
    private City origin;

    @NotNull
    @ManyToOne
    @Getter @Setter
    @JoinColumn(name = "destination")
    @Cascade(org.hibernate.annotations.CascadeType.PERSIST)
    private City destination;

    @NotNull
    @Getter @Setter
    @Column(name = "departure_time")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm")
    private Timestamp departureTime;

    @NotNull
    @Getter @Setter
    @Column(name = "duration")
    private int duration;

    @NotNull
    @ManyToOne
    @Getter @Setter
    @JoinColumn(name = "bus_id")
    @Cascade(org.hibernate.annotations.CascadeType.PERSIST)
    private Bus bus;

}




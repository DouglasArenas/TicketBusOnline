package com.um.main.repositories;

import com.um.main.models.Trip;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


public interface TripRepository extends JpaRepository<Trip, Long> {
    List<Trip> findByDepartureTime(Date departureTime);
}



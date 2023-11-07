package com.um.main.repositories;

import com.um.main.models.City;
import com.um.main.models.Trip;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface TripRepository extends JpaRepository<Trip, Long> {
    List<Trip> findByDepartureTimeGreaterThanEqual(Timestamp departure_time);
    @Query("SELECT t FROM Trip t WHERE t.origin = :origin AND t.destination = :destination AND t.departureTime >= :departureTimeStart AND t.departureTime < :departureTimeEnd")
List<Trip> findByOriginAndDestinationAndDate(@Param("origin") City origin, @Param("destination") City destination, @Param("departureTimeStart") Timestamp departureTimeStart, @Param("departureTimeEnd") Timestamp departureTimeEnd);
}
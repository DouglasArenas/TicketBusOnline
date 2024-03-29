package com.um.main.controllers;

import java.sql.Timestamp;
import java.util.List;

import com.um.main.services.TripService;
import com.um.main.models.City;
import com.um.main.models.Trip;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/trip")
public class TripController {
    @Autowired
    private TripService tripService;

    @GetMapping("/{id}")
    public ResponseEntity<Trip> getTrip(@PathVariable Long id) {
        Trip trip = tripService.getTrip(id);
        return new ResponseEntity<>(trip, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Trip> addTrip(@RequestBody Trip trip) {
        Trip newTrip = tripService.addTrip(trip);
        return new ResponseEntity<>(newTrip, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Trip> updateTrip(@PathVariable Long id, @RequestBody Trip trip) {
        trip.setId(id);
        Trip updateTrip = tripService.updateTrip(id, trip);
        return new ResponseEntity<>(updateTrip, HttpStatus.OK);
    }
 
    @DeleteMapping("/{id}")
    public ResponseEntity<Trip> deleteTrip(@PathVariable Long id) {
        tripService.deleteTrip(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Trip>> getAllTrips() {
        List<Trip> trips = tripService.getAllTrips();
        return new ResponseEntity<>(trips, HttpStatus.OK);
    }

    @GetMapping("/filter")
    public List<Trip> filterTrips(@RequestParam String origin, @RequestParam String destination, @RequestParam String date) {
        return tripService.getTripsByOriginAndDestinationAndDate(origin, destination, date);
    }
}
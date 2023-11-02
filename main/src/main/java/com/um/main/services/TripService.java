package com.um.main.services;

import com.um.main.repositories.TripRepository;
import com.um.main.models.Trip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class TripService {
    @Autowired
    private TripRepository tripRepository;

    public Trip addTrip(Trip trip) {
        return tripRepository.save(trip);
    }

    public Trip updateTrip(Trip trip) {
        return tripRepository.save(trip);
    }

    public void deleteTrip(Long id) {
        tripRepository.deleteById(id);
    }

    public Trip getTrip(Long id) {
        return tripRepository.findById(id).orElse(null);
    }

    public List<Trip> getTripsByDepartureDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM");
        LocalDate departureDate = LocalDate.parse(date, formatter).withYear(LocalDate.now().getYear());

        java.util.Date utilDate = java.sql.Date.from(departureDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        java.sql.Date departureTime = new java.sql.Date(utilDate.getTime());

        return tripRepository.findByDepartureTime(departureTime);
    }

    public List<Trip> getAllTrips() {
        return tripRepository.findAll();
    }
}

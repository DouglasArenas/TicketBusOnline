package com.um.main.services;

import com.um.main.repositories.BusRepository;
import com.um.main.repositories.CityRepository;
import com.um.main.repositories.TripRepository;
import com.um.main.exceptions.ResourceNotFound;
import com.um.main.models.Bus;
import com.um.main.models.City;
import com.um.main.models.Trip;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
public class TripService {
    @Autowired
    private TripRepository tripRepository;

    @Autowired
    private BusRepository busRepository;

    @Autowired
    private CityRepository cityRepository;

    public Trip addTrip(Trip trip) {
        if (!IsNotEmpty.isNotEmpty(trip)) {
            return null;
        }
        Optional<Bus> bus = busRepository.findById(trip.getBus().getId());
        Optional<City> origin = Optional.ofNullable(cityRepository.findByName(trip.getOrigin().getName()));
        Optional<City> destination = Optional.ofNullable(cityRepository.findByName(trip.getDestination().getName()));
        if (!bus.isPresent() || !origin.isPresent() || !destination.isPresent()) {
            return null;
        }
        trip.setBus(bus.get());
        trip.setOrigin(origin.get());
        trip.setDestination(destination.get());
        return tripRepository.save(trip);
    }

    public Trip updateTrip(Long id, Trip newTrip) {
        Trip trip = getTrip(id);
        if (IsNotEmpty.updateObject(trip, newTrip)) {
            return trip;
        }
        return tripRepository.save(trip);
    }

    public void deleteTrip(Long id) {
        getTrip(id);
        tripRepository.deleteById(id);
    }

    public Trip getTrip(Long id) {
        return tripRepository.findById(id).orElseThrow(() -> new ResourceNotFound(id));
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

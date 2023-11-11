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

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class TripService {
    @Autowired
    private TripRepository tripRepository;

    @Autowired
    private BusRepository busRepository;

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private CityService cityService;

    public Trip addTrip(Trip trip) {
        Objects.requireNonNull(trip, "Trip cannot be null");

        Bus bus = busRepository.findById(trip.getBus().getId())
                .orElseThrow(() -> new ResourceNotFound("Bus"));
        City origin = Optional.ofNullable(cityRepository.findByName(trip.getOrigin().getName()))
                .orElseThrow(() -> new ResourceNotFound("Origin city"));
        City destination = Optional.ofNullable(cityRepository.findByName(trip.getDestination().getName()))
                .orElseThrow(() -> new ResourceNotFound("Destination city"));

        trip.setBus(bus);
        trip.setOrigin(origin);
        trip.setDestination(destination);

        return tripRepository.save(trip);
    }

    public Trip updateTrip(Long id, Trip newTrip) {
        Trip trip = getTrip(id);
        if (trip == null || newTrip == null) {
            throw new IllegalArgumentException("Trip or newTrip is null");
        }
        Optional.ofNullable(newTrip.getBus())
        .map(Bus::getId)
        .flatMap(busRepository::findById)
        .ifPresent(trip::setBus);
        Optional.ofNullable(newTrip.getOrigin())
        .map(City::getName)
        .flatMap(name -> Optional.ofNullable(cityRepository.findByName(name)))
        .ifPresent(trip::setOrigin);
        Optional.ofNullable(newTrip.getDestination())
        .map(City::getName)
        .flatMap(name -> Optional.ofNullable(cityRepository.findByName(name)))
        .ifPresent(trip::setDestination);
        Optional.ofNullable(newTrip.getDepartureTime())
        .ifPresent(trip::setDepartureTime);
        Optional.ofNullable(newTrip.getDuration())
        .ifPresent(trip::setDuration);
        return tripRepository.save(trip);
    }

    public void deleteTrip(Long id) {
        getTrip(id);
        tripRepository.deleteById(id);
    }

    public Trip getTrip(Long id) {
        return tripRepository.findById(id).orElseThrow(() -> new ResourceNotFound("Trip"));
    }

    public List<Trip> getAllTrips() {
        return tripRepository.findAll();
    }

    public List<Trip> getTripsByOriginAndDestinationAndDate(String originCity, String destinationCity, String date) {
        City origin = cityService.getCityByName(originCity);
        City destination = cityService.getCityByName(destinationCity);

        DateTimeFormatter formatterWithTime = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        DateTimeFormatter formatterWithoutTime = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        ZonedDateTime departureDateTime;

        try {
            departureDateTime = ZonedDateTime.parse(date, formatterWithTime.withZone(ZoneId.of("UTC")));
        } catch (DateTimeParseException e) {
            departureDateTime = LocalDate.parse(date, formatterWithoutTime).atStartOfDay(ZoneId.of("UTC"));
        }

        ZonedDateTime startOfDay = departureDateTime.toLocalDate().atStartOfDay(departureDateTime.getZone());
        ZonedDateTime endOfDay = startOfDay.plusDays(1);
        Timestamp departureTimeStart = Timestamp.valueOf(startOfDay.toLocalDateTime());
        Timestamp departureTimeEnd = Timestamp.valueOf(endOfDay.toLocalDateTime());

        return tripRepository.findByOriginAndDestinationAndDate(origin, destination, departureTimeStart, departureTimeEnd);
    }

}
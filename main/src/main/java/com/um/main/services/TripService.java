package com.um.main.services;

import com.um.main.services.CityService;
import com.um.main.repositories.BusRepository;
import com.um.main.repositories.CityRepository;
import com.um.main.repositories.TripRepository;
import com.um.main.exceptions.ResourceNotFound;
import com.um.main.models.Bus;
import com.um.main.models.City;
import com.um.main.models.Trip;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.NoSuchElementException;
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
        Bus bus = busRepository.findById(trip.getBus().getId())
                .orElseThrow(() -> new NoSuchElementException("Bus no encontrado"));
        City origin = Optional.ofNullable(cityRepository.findByName(trip.getOrigin().getName()))
                .orElseThrow(() -> new NoSuchElementException("Ciudad de origen no encontrada"));
        City destination = Optional.ofNullable(cityRepository.findByName(trip.getDestination().getName()))
                .orElseThrow(() -> new NoSuchElementException("Ciudad de destino no encontrada"));

        trip.setBus(bus);
        trip.setOrigin(origin);
        trip.setDestination(destination);
        trip.setDepartureTime(trip.getDepartureTime());
        trip.setDuration(trip.getDuration());
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
        return tripRepository.findById(id).orElseThrow(() -> new ResourceNotFound(id));
    }

    public List<Trip> getTripsByDepartureDate(String json) {
        JSONObject jsonObject = new JSONObject(json);
        String date = jsonObject.getString("departure_date");
        if (date == null || date.isEmpty()) {
            throw new IllegalArgumentException("Date is null");
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate departureDate = LocalDate.parse(date + "-" + LocalDate.now().getYear(), formatter);

        java.util.Date utilDate = java.sql.Date.from(departureDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        java.sql.Date departureTime = new java.sql.Date(utilDate.getTime());

        return tripRepository.findByDepartureTime(departureTime);
    }

    public List<Trip> getAllTrips() {
        return tripRepository.findAll();
    }

}
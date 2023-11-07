package com.um.main.services;

import com.um.main.repositories.BookingRepository;
import com.um.main.repositories.BusRepository;
import com.um.main.repositories.TripRepository;
import com.um.main.exceptions.ResourceNotFound;
import com.um.main.models.Booking;
import com.um.main.models.Bus;
import com.um.main.models.Passenger;
import com.um.main.models.Trip;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class BookingService {
    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private TripRepository tripRepository;

    @Autowired
    private BusRepository busRepository;

    public Booking addBooking(Booking booking) {
        Trip trip = tripRepository.findById(booking.getTrip().getId())
                .orElseThrow(() -> new NoSuchElementException());
        booking.setTrip(trip);
        return bookingRepository.save(booking);
    }

    public Booking updateBooking(Long id, Booking newBooking) {
        Booking booking = getBooking(id);
        if (IsNotEmpty.updateObject(booking, booking)) {
            return bookingRepository.save(booking);
        }
        return bookingRepository.save(newBooking);
    }

    public void deleteBooking(Long id) {
        getBooking(id);
        bookingRepository.deleteById(id);
    }

    public Booking getBooking(Long id) {
        return bookingRepository.findById(id).orElseThrow(() -> new ResourceNotFound("Booking"));
    }

    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    public Booking addPassengers(Long id, Booking newBooking) {
        Booking booking = getBooking(id);
        Trip trip = tripRepository.findById(booking.getTrip().getId())
                .orElseThrow(() -> new NoSuchElementException("there is no trip with id: " + booking.getTrip().getId()));
        Bus bus = busRepository.findById(trip.getBus().getId())
                .orElseThrow(() -> new NoSuchElementException());
        List<Passenger> newPassengers = newBooking.getPassengers().stream()
                .filter(p -> !booking.getPassengers().contains(p))
                .collect(Collectors.toList());
        int numberOfNewPassengers = newPassengers.size();
        if(bus.getCapacity() >= numberOfNewPassengers) {
            bus.setCapacity(bus.getCapacity() - numberOfNewPassengers);
            busRepository.save(bus);
            booking.getPassengers().addAll(newPassengers);
            return bookingRepository.save(booking);
        } else {
            throw new RuntimeException("Bus is full");
        }
    }
}

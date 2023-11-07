package com.um.main.services;

import com.um.main.repositories.BookingRepository;
import com.um.main.repositories.BusRepository;
import com.um.main.repositories.PassengerRepository;
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
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
public class BookingService {
    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private TripRepository tripRepository;

    @Autowired
    private BusRepository busRepository;

    @Autowired
    private PassengerRepository passengerRepository;

    @Autowired
    private EmailService emailService;

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
        LOGGER.info("Adding passengers to booking " + id);
        Booking booking = getBooking(id);
        LOGGER.info("Booking " + id + " found");
        Trip trip = tripRepository.findById(booking.getTrip().getId())
                .orElseThrow(() -> new NoSuchElementException("there is no trip with id: " + booking.getTrip().getId()));
        LOGGER.info("Trip " + trip.getId() + " found");
        Bus bus = busRepository.findById(trip.getBus().getId())
                .orElseThrow(() -> new NoSuchElementException());
        LOGGER.info("Bus " + bus.getId() + " found");
        List<Passenger> newPassengers = newBooking.getPassengers().stream()
            .map(p -> passengerRepository.findById(p.getId()).orElseThrow(() -> new NoSuchElementException()))
            .peek(p -> LOGGER.info("Passenger " + p.getName() + " found"))
            .filter(p -> {
                LOGGER.info("Checking if passenger " + p.getName() + " is already in booking " + booking.getId());
                boolean notInBooking = !booking.getPassengers().contains(p);
                LOGGER.info("Passenger is " + (notInBooking ? "not ": "") + "already in Booking");
                return notInBooking;
            })
            .collect(Collectors.toList());
        int numberOfNewPassengers = newPassengers.size();
        if(!newPassengers.isEmpty()) {
            Passenger lastPassenger = newPassengers.get(newPassengers.size() - 1);
            bus.setCapacity(bus.getCapacity() - numberOfNewPassengers);
            busRepository.save(bus);
            booking.getPassengers().addAll(newPassengers);
            System.out.println("booking passengers: " + booking.getPassengers());
            System.out.println("nombre de booking passengers: " + booking.getPassengers().stream().map(Passenger::getName).collect(Collectors.toList()));
            System.out.println("Adding " + lastPassenger.getName() + " to booking " + booking.getId());
            if (lastPassenger.getEmail() != null) {
                System.out.println("Sending email to " + lastPassenger.getEmail());
                sendBookingConfirmation(lastPassenger, booking);
            }
            return bookingRepository.save(booking);
        } else {
            LOGGER.info("No new passengers to add");
            // throw new RuntimeException("Bus is full");
        }
        return booking;
    }

    private void sendBookingConfirmation(Passenger passenger, Booking booking) {
        emailService.sendSimpleMessage(
            passenger.getEmail(),
            "Confirmación de reserva",
            "¡Hola, " + passenger.getName() + "! Has sido añadido a la reserva " + booking.getId() + "."
        );
    }

    private static final Logger LOGGER = Logger.getLogger(BookingService.class.getName());

}

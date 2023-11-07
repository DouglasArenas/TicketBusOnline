package com.um.main.services;

import com.um.main.repositories.PassengerRepository;
import com.um.main.exceptions.PassengerNotFoundException;
import com.um.main.exceptions.ResourceNotFound;
import com.um.main.models.Passenger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PassengerService {
    @Autowired
    private PassengerRepository passengerRepository;

    public Passenger addPassenger(Passenger passenger) {
        if (IsNotEmpty.isNotEmpty(passenger)) {
            return passengerRepository.save(passenger);
        }
        return null;
    }

    public Passenger updatePassenger(Long id, Passenger newPassenger) {
        Passenger passenger = getPassenger(id);
        if (IsNotEmpty.updateObject(passenger, newPassenger)) {
            return passengerRepository.save(passenger);
        }
        return passengerRepository.save(newPassenger);
    }

    public void deletePassenger(Long id) {
        getPassenger(id);
        passengerRepository.deleteById(id);
    }

    public Passenger getPassenger(Long id) {
        return passengerRepository.findById(id).orElseThrow(() -> new ResourceNotFound("Passenger"));
    }

    public List<Passenger> getAllPassengers() {
        return passengerRepository.findAll();
    }
}

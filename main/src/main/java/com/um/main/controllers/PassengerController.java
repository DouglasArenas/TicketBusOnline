package com.um.main.controllers;

import com.um.main.services.PassengerService;
import com.um.main.models.Passenger;

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
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/passenger")
public class PassengerController {
   @Autowired
   private PassengerService passengerService;

   @GetMapping("/{id}")
   public ResponseEntity<Passenger> getPassenger(@PathVariable Long id) {
      Passenger passenger = passengerService.getPassenger(id);
      return new ResponseEntity<>(passenger, HttpStatus.OK);
   }

   @PostMapping
   public ResponseEntity<Passenger> addPassenger(@RequestBody Passenger passenger) {
      Passenger newPassenger = passengerService.addPassenger(passenger);
      return new ResponseEntity<>(newPassenger, HttpStatus.CREATED);
   }

   @PutMapping("/{id}")
   public ResponseEntity<Passenger> updatePassenger(@PathVariable Long id, @RequestBody Passenger passenger) {
      passenger.setId(id);
      Passenger updatePassenger = passengerService.updatePassenger(passenger);
    return new ResponseEntity<>(updatePassenger, HttpStatus.OK);
   }

   @DeleteMapping("/{id}")
   public ResponseEntity<Void> deletePassenger(@PathVariable Long id) {
      passengerService.deletePassenger(id);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
   }
}

package com.um.main.controllers;

import com.um.main.models.Bus;
import com.um.main.services.BusService;

import org.springframework.web.bind.annotation.RestController;
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

@RestController
@RequestMapping("/bus")
public class BusController {
    
    @Autowired
    private BusService busService;

    @GetMapping("/{id}")
    public ResponseEntity<Bus> getBus(@PathVariable Long id) {
        Bus bus = busService.getBus(id);
        return new ResponseEntity<Bus>(bus, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<Iterable<Bus>> getAllBuses() {
        Iterable<Bus> buses = busService.getAllBuses();
        return new ResponseEntity<Iterable<Bus>>(buses, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Bus> addBus(@RequestBody Bus bus) {
        Bus newBus = busService.addBus(bus);
        return new ResponseEntity<Bus>(newBus, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Bus> updateBus(@PathVariable Long id, @RequestBody Bus bus) {
        Bus updatedBus = busService.updateBus(id, bus);
        return new ResponseEntity<Bus>(updatedBus, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBus(@PathVariable Long id) {
        busService.deleteBus(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}

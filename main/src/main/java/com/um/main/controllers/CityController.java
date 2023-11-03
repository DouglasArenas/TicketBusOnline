package com.um.main.controllers;

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

import com.um.main.models.City;
import com.um.main.services.CityService;

@RestController
@RequestMapping("/city")
public class CityController {
    
    @Autowired
    private CityService cityService;

    @GetMapping("/{id}")
    public ResponseEntity<City> getCity(@PathVariable Long id) {
        City city = cityService.getCity(id);
        return new ResponseEntity<>(city, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<Iterable<City>> getAllCities() {
        Iterable<City> cities = cityService.getAllCities();
        return new ResponseEntity<>(cities, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<City> addCity(@RequestBody City city) {
        City newCity = cityService.addCity(city);
        return new ResponseEntity<>(newCity, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<City> updateCity(@PathVariable Long id, @RequestBody City city) {
        City updatedCity = cityService.updateCity(id, city);
        return new ResponseEntity<>(updatedCity, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCity(@PathVariable Long id) {
        cityService.deleteCity(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<City> getCityByName(@PathVariable String name) {
        City city = cityService.getCityByName(name);
        return new ResponseEntity<>(city, HttpStatus.OK);
    }

}

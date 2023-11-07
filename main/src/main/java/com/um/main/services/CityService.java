package com.um.main.services;

import com.um.main.repositories.CityRepository;
import com.um.main.exceptions.ResourceNotFound;
import com.um.main.models.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CityService {
    @Autowired
    private CityRepository cityRepository;

    public City addCity(City city) {
        if (IsNotEmpty.isNotEmpty(city)) {
            return cityRepository.save(city);
        }
        return null;
    }

    public City updateCity(Long id, City newCity) {
        City city = getCity(id);
        if (IsNotEmpty.updateObject(city, newCity)) {
            return cityRepository.save(city);
        }
        return city;
    }

    public void deleteCity(Long id) {
        getCity(id);
        cityRepository.deleteById(id);
    }

    public City getCity(Long id) {
        return cityRepository.findById(id).orElseThrow(() -> new ResourceNotFound("City"));
    }

    public List<City> getAllCities() {
        return cityRepository.findAll();
    }

    public City getCityByName(String name) {
        return cityRepository.findByName(name);
    }

    // public City findCityByName(String name) {
    //     Optional<City> city = Optional.ofNullable(cityRepository.findByName(name));
    //     if (!city.isPresent()) {
    //         throw new ResourceNotFound(null);
    //     }
    //     return city.get();
    // }

}

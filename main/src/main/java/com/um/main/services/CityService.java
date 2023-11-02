package com.um.main.services;

import com.um.main.repositories.CityRepository;
import com.um.main.exceptions.CityNotFoundException;
import com.um.main.models.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CityService {
    @Autowired
    private CityRepository cityRepository;

    public City addCity(City city) {
        return cityRepository.save(city);
    }

    public City updateCity(Long id, City city) {
        City existingCity = cityRepository.findById(id).orElseThrow(() -> new CityNotFoundException(id));
        existingCity.setName(city.getName() == null ? existingCity.getName() : city.getName());
        return cityRepository.save(existingCity);
    }

    public void deleteCity(Long id) {
        cityRepository.deleteById(id);
    }

    public City getCity(Long id) {
        return cityRepository.findById(id).orElse(null);
    }

    public List<City> getAllCities() {
        return cityRepository.findAll();
    }
}

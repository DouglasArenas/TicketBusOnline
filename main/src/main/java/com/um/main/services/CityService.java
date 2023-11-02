package com.um.main.services;

import com.um.main.repositories.CityRepository;
import com.um.main.exceptions.CityNotFoundException;
import com.um.main.exceptions.IllegalArgument;
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
        if (isNotEmpty(city)) {
            return cityRepository.save(city);
        }
        return null;
    }

    public City updateCity(Long id, City newCity) {
        City city = getCity(id);
        if (isNotEmpty(newCity)) {
            city.setName(newCity.getName());
        }
        return cityRepository.save(city);
    }

    public void deleteCity(Long id) {
        getCity(id);
        cityRepository.deleteById(id);
    }

    public City getCity(Long id) {
        return cityRepository.findById(id).orElseThrow(() -> new ResourceNotFound(id));
    }

    public List<City> getAllCities() {
        return cityRepository.findAll();
    }

    public boolean isNotEmpty(City city) {
        if (city.getName() == null || city.getName().isEmpty()) {
            throw new IllegalArgument("city");
        }
        return true;
    }
}

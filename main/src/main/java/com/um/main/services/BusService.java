package com.um.main.services;

import com.um.main.repositories.BusRepository;
import com.um.main.exceptions.BusNotFoundException;
import com.um.main.models.Bus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BusService {
    @Autowired
    private BusRepository busRepository;

    public Bus addBus(Bus bus) {
        return busRepository.save(bus);
    }

    public Bus updateBus(Bus bus) {
        return busRepository.save(bus);
    }

    public void deleteBus(Long id) {
        busRepository.deleteById(id);
    }

    public Bus getBus(Long id) {
        return busRepository.findById(id).orElseThrow(() -> new BusNotFoundException(id));
    }

    public List<Bus> getAllBuses() {
        return busRepository.findAll();
    }
}

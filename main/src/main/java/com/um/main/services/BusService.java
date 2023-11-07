package com.um.main.services;

import com.um.main.repositories.BusRepository;
import com.um.main.repositories.CompanyRepository;
import com.um.main.exceptions.ResourceNotFound;
import com.um.main.models.Bus;
import com.um.main.models.Company;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class BusService {
    @Autowired
    private BusRepository busRepository;

    @Autowired 
    private CompanyRepository companyRepository;

    public Bus addBus(Bus bus) {
        if (!IsNotEmpty.isNotEmpty(bus)) {
            return null;
        }
        Optional<Company> company = companyRepository.findById(bus.getCompany().getId());
        if (!company.isPresent()) {
            return null;
        }
        bus.setCompany(company.get());
        return busRepository.save(bus);
    }

    public Bus updateBus(Long id, Bus newBus) {
        Bus bus = getBus(id);
        if (IsNotEmpty.updateObject(bus, newBus)) {
            return bus;
        }
        Optional<Company> company = companyRepository.findById(bus.getCompany().getId());
        if (!company.isPresent()) {
            return null;
        }
        newBus.setCompany(company.get());
        return busRepository.save(newBus);
    }

    public void deleteBus(Long id) {
        getBus(id);
        busRepository.deleteById(id);
    }

    public Bus getBus(Long id) {
        return busRepository.findById(id).orElseThrow(() -> new ResourceNotFound("Bus"));
    }

    public List<Bus> getAllBuses() {
        return busRepository.findAll();
    }

}
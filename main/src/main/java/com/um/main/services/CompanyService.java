package com.um.main.services;

import com.um.main.repositories.CompanyRepository;
import com.um.main.exceptions.ResourceNotFound;
import com.um.main.models.Company;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CompanyService {
    @Autowired
    private CompanyRepository companyRepository;

    public Company addCompany(Company company) {
        if (IsNotEmpty.isNotEmpty(company)) {
            return companyRepository.save(company);
        }
        return null;
    }

    public Company updateCompany(Long id, Company newCompany) {
        Company company = getCompany(id);
        if (IsNotEmpty.updateObject(company, newCompany)) {
            return companyRepository.save(company);
        }
        return company;
    }

    public void deleteCompany(Long id) {
        getCompany(id);
        companyRepository.deleteById(id);
    }

    public Company getCompany(Long id) {
        return companyRepository.findById(id).orElseThrow(() -> new ResourceNotFound("Company"));
    }

    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }
}

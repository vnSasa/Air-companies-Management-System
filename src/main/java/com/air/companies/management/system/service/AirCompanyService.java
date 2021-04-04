package com.air.companies.management.system.service;

import com.air.companies.management.system.exception.MyException;
import com.air.companies.management.system.model.AirCompany;
import com.air.companies.management.system.repository.AirCompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class AirCompanyService {

    private final AirCompanyRepository airCompanyRepository;

    @Autowired
    public AirCompanyService(AirCompanyRepository airCompanyRepository) {
        this.airCompanyRepository = airCompanyRepository;
    }

    public void addNewAirCompany(AirCompany airCompany) {
        airCompanyRepository.save(airCompany);
    }

    public List<AirCompany> findAllAirCompany() throws MyException {
        List<AirCompany> list = new LinkedList<>();
        airCompanyRepository.findAll().iterator().forEachRemaining(list::add);
        if (list.isEmpty()) {
            throw new MyException("not found any air company");
        }
        return list;
    }

    public AirCompany findAirCompanyByName(String name) throws MyException {
        Optional<AirCompany> airCompanyOptional = Optional.ofNullable(airCompanyRepository.findByName(name));
        AirCompany airCompany = airCompanyOptional.orElseThrow(() -> new MyException("not found air company with name: " + name));
        return airCompany;
    }

    public void deleteAirCompany(String name) throws MyException {
        airCompanyRepository.deleteById(findAirCompanyByName(name).getId());
    }
}
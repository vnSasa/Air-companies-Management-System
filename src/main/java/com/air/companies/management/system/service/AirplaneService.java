package com.air.companies.management.system.service;

import com.air.companies.management.system.exception.MyException;
import com.air.companies.management.system.model.AirCompany;
import com.air.companies.management.system.model.Airplane;
import com.air.companies.management.system.model.Flight;
import com.air.companies.management.system.repository.AirplaneRepository;
import com.air.companies.management.system.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AirplaneService {

    private final AirplaneRepository airplaneRepository;
    private final AirCompanyService airCompanyService;
    private final FlightRepository flightRepository;

    @Autowired
    public AirplaneService(AirplaneRepository airplaneRepository, AirCompanyService airCompanyService, FlightRepository flightRepository) {
        this.airplaneRepository = airplaneRepository;
        this.airCompanyService = airCompanyService;
        this.flightRepository = flightRepository;
    }

    public void addNewAirplane(Airplane airplane, String airCompanyName) throws MyException {
        airplane.setAirCompanyId(airCompanyService.findAirCompanyByName(airCompanyName).getId());
        Optional<Airplane> airplaneOptional = Optional.ofNullable(airplaneRepository
                .findByFactorySerialNumber(airplane.getFactorySerialNumber()));
        if(airplaneOptional.isPresent()){
            throw new MyException("This airplane exists");
        }
        airplaneRepository.save(airplane);
    }

    public Airplane findAirplaneByFactorySerialNumber(String factorySerialNumber) throws MyException {
        return Optional.ofNullable(airplaneRepository
                .findByFactorySerialNumber(factorySerialNumber)).orElseThrow(() -> new MyException("This airplane doesn't exist"));
    }

    public void changeAirCompany(String factorySerialNumber, String airCompanyName) throws MyException {
        Airplane airplane = findAirplaneByFactorySerialNumber(factorySerialNumber);
        AirCompany airCompany = airCompanyService.findAirCompanyByName(airCompanyName);
        List<Flight> flights = flightRepository.findAllByAirPlaneId(airplane.getId());
        flights.forEach(f -> f.setAirCompanyId(airCompany.getId()));
        airplane.setAirCompanyId(airCompany.getId());
        airplaneRepository.save(airplane);
        flights.forEach(f -> flightRepository.save(f));
    }

    public Iterable<Airplane> allAirplane() {
        return airplaneRepository.findAll();
    }

}

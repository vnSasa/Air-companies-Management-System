package com.air.companies.management.system.controller;

import com.air.companies.management.system.exception.MyException;
import com.air.companies.management.system.model.Airplane;
import com.air.companies.management.system.service.AirplaneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AirplaneController {

    private final AirplaneService airplaneService;

    @Autowired
    public AirplaneController(AirplaneService airplaneService) {
        this.airplaneService = airplaneService;
    }

    @PutMapping("addNewAirplane")
    public ResponseEntity<String> addNewAirplane(@RequestBody Airplane airplane, @RequestParam String airCompanyName) throws MyException {
        airplaneService.addNewAirplane(airplane, airCompanyName);
        return ResponseEntity.ok("New airplane has added!!!");
    }

    @PostMapping("/changeAirCompany")
    public ResponseEntity<String> changeAirCompany(@RequestParam String factorySerialNumber, @RequestParam String airCompanyName) throws MyException {
        airplaneService.changeAirCompany(factorySerialNumber, airCompanyName);
        return ResponseEntity.ok("Air company has changed");
    }

    @GetMapping("/allAirplane")
    public ResponseEntity<Iterable<Airplane>> allAirplane () {
        return ResponseEntity.ok(airplaneService.allAirplane());
    }
}

package com.air.companies.management.system.repository;

import com.air.companies.management.system.model.Airplane;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AirplaneRepository extends CrudRepository<Airplane, Long> {
    Airplane findByFactorySerialNumber(String factorySerialNumber);
}

package com.air.companies.management.system.repository;

import com.air.companies.management.system.model.AirCompany;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AirCompanyRepository extends CrudRepository<AirCompany, Long> {
    AirCompany findByName(String name);
}

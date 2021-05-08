package com.genesiscode.quotation.repository;

import com.genesiscode.quotation.domain.unit.AdministrativeUnit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UnitAdministrativeRepository extends JpaRepository<AdministrativeUnit, Long> {


}

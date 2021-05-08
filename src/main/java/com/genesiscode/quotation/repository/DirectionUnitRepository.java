package com.genesiscode.quotation.repository;

import com.genesiscode.quotation.domain.unit.DirectionUnit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DirectionUnitRepository extends JpaRepository<DirectionUnit, Long> {

}

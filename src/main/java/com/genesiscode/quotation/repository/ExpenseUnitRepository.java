package com.genesiscode.quotation.repository;

import com.genesiscode.quotation.domain.ExpenseUnit;
import com.genesiscode.quotation.domain.unit.DirectionUnit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpenseUnitRepository extends JpaRepository<ExpenseUnit, Long> {
}

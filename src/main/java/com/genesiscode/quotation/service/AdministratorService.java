package com.genesiscode.quotation.service;
import com.genesiscode.quotation.domain.unit.AdministrativeUnit;
import com.genesiscode.quotation.repository.UnitAdministrativeRepository;
import static com.genesiscode.quotation.utils.Preconditions.*;
import lombok.*;
import org.springframework.stereotype.*;
import java.util.*;

@AllArgsConstructor
@Service
public class AdministratorService {

    private final UnitAdministrativeRepository unitAdministrativeRepository;

    public void createUnitAdministrative(AdministrativeUnit unit) {
        checkArgument(unit != null, "Unit Administrative must not be null");
        checkDataType(unit, AdministrativeUnit.class , "Data Type Incompatible");

        unitAdministrativeRepository.save(unit);
    }

    public List<AdministrativeUnit> getUnitsAdministrative() {
        return unitAdministrativeRepository.findAll();
    }

    public AdministrativeUnit getUnitAdministrativeById(Long id) {
        checkArgument(id != null, "Id most not be null");
        checkDataType(id, Long.class , "Data Type Incompatible");

        return unitAdministrativeRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException
                        ("No exists this unit administrative with id " + id));
    }


}

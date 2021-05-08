package com.genesiscode.quotation.service;
import com.genesiscode.quotation.domain.unit.AdministrativeUnit;
import com.genesiscode.quotation.domain.user.Responsible;
import com.genesiscode.quotation.repository.ResponsibleRepository;
import com.genesiscode.quotation.repository.AdministrativeUnitRepository;
import static com.genesiscode.quotation.utils.Preconditions.*;

import com.genesiscode.quotation.security.RoleResponsible;
import lombok.*;
import org.springframework.stereotype.*;
import java.util.*;

@AllArgsConstructor
@Service
public class AdministratorService {

    private final AdministrativeUnitRepository administrativeUnitRepository;
    private final ResponsibleRepository responsibleRepository;

    public void createAdministrativeUnit(AdministrativeUnit unit) {
        checkArgument(unit != null, "Administrative Unit must not be null");
        checkDataType(unit, AdministrativeUnit.class , "Data Type Incompatible");

        administrativeUnitRepository.save(unit);
    }

    public void createHeadOfAdministrativeUnit(Responsible headOfAdministrativeUnit) {
        checkArgument(headOfAdministrativeUnit != null, "Responsible must not be null");
        checkDataType(headOfAdministrativeUnit, Responsible.class, "Data Type Incompatible");

        if(headOfAdministrativeUnit.getRole() != RoleResponsible.HEAD_OF_ADMINISTRATIVE_UNIT)
            throw new IllegalArgumentException("You couldn't create this Role");

        responsibleRepository.save(headOfAdministrativeUnit);
    }

    public List<AdministrativeUnit> getAdministrativeUnits() {
        return administrativeUnitRepository.findAll();
    }

    public AdministrativeUnit getAdministrativeUnitById(Long id) {
        checkArgument(id != null, "Id most not be null");
        checkDataType(id, Long.class , "Data Type Incompatible");

        return administrativeUnitRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException
                        ("No exists this unit administrative with id " + id));
    }
}

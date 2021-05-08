package com.genesiscode.quotation.service;

import com.genesiscode.quotation.domain.unit.DirectionUnit;
import com.genesiscode.quotation.domain.user.Responsible;
import com.genesiscode.quotation.repository.DirectionUnitRepository;
import com.genesiscode.quotation.repository.ResponsibleRepository;
import static com.genesiscode.quotation.utils.Preconditions.*;

import com.genesiscode.quotation.security.RoleResponsible;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class ResponsibleService {

    private final DirectionUnitRepository directionUnitRepository;
    private final ResponsibleRepository responsibleRepository;

    public void createDirectionUnit(DirectionUnit directionUnit) {
        checkArgument(directionUnit != null, "Direction Unit cannot be null");
        checkDataType(directionUnit, DirectionUnit.class, "Data Type incompatible");

        directionUnitRepository.save(directionUnit);
    }

    public void createHeadOfDirectionUnit(Responsible responsible) {
        checkArgument(responsible != null, "Responsible cannot be null");
        checkDataType(responsible, Responsible.class, "Data Type incompatible");

        if(responsible.getRole() != RoleResponsible.HEAD_OF_DIRECTION)
            throw new IllegalStateException("You couldn't create this Role");
        responsibleRepository.save(responsible);

    }
}

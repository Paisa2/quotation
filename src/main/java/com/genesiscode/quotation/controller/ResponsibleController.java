package com.genesiscode.quotation.controller;

import com.genesiscode.quotation.domain.unit.DirectionUnit;
import com.genesiscode.quotation.domain.user.Responsible;
import com.genesiscode.quotation.service.ResponsibleService;
import lombok.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping(path = "/api/responsible")
public class ResponsibleController {

    private final ResponsibleService responsibleService;

    // hasRole('ROLE_') ó hasAnyRole('ROLE_')
    // hasAuthority('permission') ó hasAnyAuthority('permission')

    @PostMapping(path = "/headOfAdministrativeUnit/write:directionUnit")
    @PreAuthorize("hasAuthority('write:directionUnit')")
    public void createDirectionUnit(@RequestBody DirectionUnit directionUnit) {
        responsibleService.createDirectionUnit(directionUnit);
    }

    @PostMapping(path = "/headOfAdministrativeUnit/write:headOfDirectionUnit")
    @PreAuthorize("hasAuthority('write:headOfDirectionUnit')")
    public void createHeadOfDirectionUnit(@RequestBody Responsible responsible) {
        responsibleService.createHeadOfDirectionUnit(responsible);
    }

}

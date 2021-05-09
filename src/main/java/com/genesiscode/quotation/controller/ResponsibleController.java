package com.genesiscode.quotation.controller;

import com.genesiscode.quotation.domain.ExpenseUnit;
import com.genesiscode.quotation.domain.unit.DirectionUnit;
import com.genesiscode.quotation.domain.user.Responsible;
import com.genesiscode.quotation.service.ResponsibleService;
import lombok.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public void createHeadOfDirectionUnit(@RequestBody Responsible headOfDirectionUnit) {
        responsibleService.createHeadOfDirectionUnit(headOfDirectionUnit);
    }

    @GetMapping
    public List<DirectionUnit> getDirectionUnits() {
        return responsibleService.getDirectionUnits();
    }

    @GetMapping(path = "{id}")
    public DirectionUnit getDirectionUnitById(@PathVariable Long id) {
        return responsibleService.getDirectionUnitById(id);
    }

    @PostMapping(path = "/headOfDirectionUnit")
    public void createExpenseUnit(ExpenseUnit unit) {
        responsibleService.createExpenseUnit(unit);
    }

    @PostMapping
    public void createHeadOfExpenseUnit(@RequestBody Responsible responsible) {
        responsibleService.createHeadOfExpenseUnit(responsible);
    }

    @GetMapping
    public List<ExpenseUnit> getExpenseUnits() {
        return responsibleService.getExpenseUnits();
    }

    @GetMapping(path = "{id}")
    public ExpenseUnit getExpenseUnitById(@PathVariable("id") Long id) {
        return responsibleService.getExpenseUnitById(id);
    }

    
    
}

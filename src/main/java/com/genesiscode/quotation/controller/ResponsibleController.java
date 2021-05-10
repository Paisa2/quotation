package com.genesiscode.quotation.controller;
import com.genesiscode.quotation.domain.unit.*;
import com.genesiscode.quotation.domain.user.Responsible;
import com.genesiscode.quotation.service.ResponsibleService;
import org.springframework.security.access.prepost.*;
import org.springframework.web.bind.annotation.*;
import lombok.*;
import java.util.*;

@AllArgsConstructor  @RestController
@RequestMapping(path = "/api/responsible")
public class ResponsibleController {

    private final ResponsibleService responsibleService;

    // hasRole('ROLE_') ó hasAnyRole('ROLE_')
    // hasAuthority('permission') ó hasAnyAuthority('permission')

    @PreAuthorize("hasRole('ROLE_HEAD_OF_ADMINISTRATIVE_UNIT')")
    @PostMapping(path = "/headOfAdministrativeUnit/write:directionUnit")
    public void createDirectionUnit(@RequestBody DirectionUnit directionUnit) {
        responsibleService.createDirectionUnit(directionUnit);
    }

    @PreAuthorize("hasRole('ROLE_HEAD_OF_ADMINISTRATIVE_UNIT')")
    @PostMapping(path = "/headOfAdministrativeUnit/write:headOfDirectionUnit")
    public void createHeadOfDirectionUnit(@RequestBody Responsible headOfDirectionUnit) {
        responsibleService.createHeadOfDirectionUnit(headOfDirectionUnit);
    }

    @PreAuthorize("hasRole('ROLE_HEAD_OF_ADMINISTRATIVE_UNIT')")
    @GetMapping(path = "/headOfAdministrativeUnit/read:directionUnits")
    public List<DirectionUnit> getDirectionUnits() {
        return responsibleService.getDirectionUnits();
    }

    @PreAuthorize("hasRole('ROLE_HEAD_OF_ADMINISTRATIVE_UNIT')")
    @GetMapping(path = "/headOfAdministrativeUnit/read:directionUnitById/{id}")
    public DirectionUnit getDirectionUnitById(@PathVariable Long id) {
        return responsibleService.getDirectionUnitById(id);
    }

    @PreAuthorize("hasRole('ROLE_HEAD_OF_DIRECTION')")
    @PostMapping(path = "/headOfDirectionUnit/write:expenseUnit")
    public void createExpenseUnit(ExpenseUnit unit) {
        responsibleService.createExpenseUnit(unit);
    }

    @PreAuthorize("hasRole('ROLE_HEAD_OF_DIRECTION')")
    @PostMapping(path = "/headOfDirectionUnit/write:headOfExpenseUnit")
    public void createHeadOfExpenseUnit(@RequestBody Responsible responsible) {
        responsibleService.createHeadOfExpenseUnit(responsible);
    }

    @PreAuthorize("hasRole('ROLE_HEAD_OF_DIRECTION')")
    @GetMapping(path = "/headOfDirectionUnit/read:expenseUnits")
    public List<ExpenseUnit> getExpenseUnits() {
        return responsibleService.getExpenseUnits();
    }

    @PreAuthorize("hasRole('ROLE_HEAD_OF_DIRECTION')")
    @GetMapping(path = "/headOfDirectionUnit/read:expenseUnitById/{id}")
    public ExpenseUnit getExpenseUnitById(@PathVariable("id") Long id) {
        return responsibleService.getExpenseUnitById(id);
    }

}

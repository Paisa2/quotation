package com.genesiscode.quotation.controller;
import com.genesiscode.quotation.domain.unit.AdministrativeUnit;
import com.genesiscode.quotation.domain.user.Responsible;
import com.genesiscode.quotation.service.AdministratorService;
import org.springframework.web.bind.annotation.*;
import java.util.*;
import lombok.*;

@AllArgsConstructor
@RestController @RequestMapping(path = "/api/administrator")
public class AdministratorController {

    private final AdministratorService administratorService;

    // hasRole('ROLE_') ó hasAnyRole('ROLE_')
    // hasAuthority('permission') ó hasAnyAuthority('permission')

    @PostMapping(path = "/write:administrativeUnit")
    public void createAdministrativeUnit(AdministrativeUnit unit) {
        administratorService.createAdministrativeUnit(unit);
    }

    @PostMapping(path = "/write:headOfAdministrativeUnit")
    public void createHeadOfAdministrativeUnit(Responsible headOfAdministrativeUnit) {
        administratorService.createHeadOfAdministrativeUnit(headOfAdministrativeUnit);
    }

    @GetMapping(path = "/read:administrativeUnits")
    public List<AdministrativeUnit> getAdministrativeUnits() {
        return administratorService.getAdministrativeUnits();
    }

    @GetMapping(path = "/read:administrativeUnitById/{id}")
    public AdministrativeUnit getAdministrativeUnitById(@PathVariable("id") Long id) {
        return administratorService.getAdministrativeUnitById(id);
    }
}

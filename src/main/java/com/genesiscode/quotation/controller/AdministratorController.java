package com.genesiscode.quotation.controller;
import com.genesiscode.quotation.domain.unit.AdministrativeUnit;
import com.genesiscode.quotation.service.AdministratorService;
import org.springframework.web.bind.annotation.*;
import java.util.*;
import lombok.*;

@AllArgsConstructor
@RestController @RequestMapping(path = "/api/administrator")
public class AdministratorController {

    private final AdministratorService administratorService;

    @PostMapping
    public void createUnitAdministrative(AdministrativeUnit unit) {
        administratorService.createUnitAdministrative(unit);
    }

    @GetMapping
    public List<AdministrativeUnit> getUnitsAdministrative() {
        return administratorService.getUnitsAdministrative();
    }

    @GetMapping(path = "{id}")
    public AdministrativeUnit getUnitAdministrativeById(@PathVariable("id") Long id) {
        return administratorService.getUnitAdministrativeById(id);
    }
}

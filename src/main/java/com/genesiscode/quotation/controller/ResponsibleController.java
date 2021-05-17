package com.genesiscode.quotation.controller;
import com.genesiscode.quotation.domain.Functionality;
import com.genesiscode.quotation.domain.Permission;
import com.genesiscode.quotation.domain.Responsible;
import com.genesiscode.quotation.domain.Role;
import com.genesiscode.quotation.service.ResponsibleService;

import org.springframework.web.bind.annotation.*;
import lombok.*;
import java.util.*;

@AllArgsConstructor
@RestController
@RequestMapping(path = "/api/responsible")
public class ResponsibleController {

    private final ResponsibleService responsibleService;

    // hasRole('ROLE_') ó hasAnyRole('ROLE_')
    // hasAuthority('permission') ó hasAnyAuthority('permission')

    @PostMapping("/role")
    public Role createRole(@RequestBody Role role) {
        return responsibleService.createRole(role);
    }

    @DeleteMapping("/role/{id}")
    public void deleteRoleById(@PathVariable("id") Long id) {
        responsibleService.deleteRoleById(id);
    }

    @GetMapping("/role/permissions/{id}")
    public List<Functionality> getListPermissionWithIdRole(@PathVariable("id") Long id) {
        return responsibleService.getListPermissionWithIdRole(id);
    }

    @PutMapping("/role/update_permissions/{idRole}")
    public void updatePermissionOfTheRole(@PathVariable("idRole") Long id,
                                          @RequestBody List<Permission> permissions) {
        responsibleService.updatePermissionOfTheRole(id, permissions);
    }

    @PostMapping
    public String createResponsible(@RequestBody Responsible responsible) {
        return responsibleService.createResponsible(responsible);
    }

    public void makeRequest() {}
    public void reviewRequest() {}
    public void answerRequest() {}
    public void makeQuote() {}
    public void makeQuotationReport() {}
    public void respondingQuotationReport() {}

}

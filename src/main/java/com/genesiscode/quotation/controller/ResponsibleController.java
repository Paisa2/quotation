package com.genesiscode.quotation.controller;
import com.genesiscode.quotation.domain.*;
import com.genesiscode.quotation.dto.*;
import com.genesiscode.quotation.dto.ResponsibleView;
import com.genesiscode.quotation.service.RegistrationService;
import com.genesiscode.quotation.service.ResponsibleService;

import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import lombok.*;
import java.util.*;

@AllArgsConstructor
@RestController
@RequestMapping(path = "/api/responsible")
public class ResponsibleController {

    private final ResponsibleService responsibleService;
    private final RegistrationService registrationService;

    // hasRole('ROLE_') ó hasAnyRole('ROLE_')
    // hasAuthority('permission') ó hasAnyAuthority('permission')

    @ApiOperation(value = "return Role")
    @PostMapping("/role")
    public Role createRole(@RequestBody Role role) {
        return responsibleService.createRole(role);
    }

    @ApiOperation(value = "update permissions of the role")
    @PutMapping("/role/update_permissions/{idRole}")
    public void updatePermissionOfTheRole(@PathVariable("idRole") Long id,
                                          @RequestBody List<Permission> permissions) {
        responsibleService.updatePermissionOfTheRole(id, permissions);
    }

    @ApiOperation(value = "delete the role")
    @DeleteMapping("/role/{id}")
    public void deleteRoleById(@PathVariable("id") Long id) {
        responsibleService.deleteRoleById(id);
    }

    @ApiOperation(value = "List<Role>")
    @GetMapping
    public List<Role> getListRoles() {
        return responsibleService.getListRoles();
    }

    @ApiOperation(value = "List<Functionality>")
    @GetMapping("/role/permissions/{id}")
    public List<Functionality> getListPermissionWithIdRole(@PathVariable("id") Long id) {
        return responsibleService.getListPermissionWithIdRole(id);
    }

    @ApiOperation(value = "String token")
    @PostMapping(path = "/registration")
    public String register(@RequestBody RegistrationRequest request) {
        return registrationService.register(request);
    }

    @ApiOperation(value = "String token")
    @GetMapping(path = "/registration/confirm")
    public String confirm(@RequestParam("token") String token) {
        return registrationService.confirmToken(token);
    }

    @GetMapping(path = "/allResponsible")
    public List<ResponsibleView> getListResponsible() {
        return responsibleService.getListResponsible();
    }

    public void makeRequest() {}

    /*
    * {
    *   "numberRequest":
    *   "projectAcronym":
    *   "date":
    *   "technicalSpecifications": []
    *   "delivered": boolean
    *   "received": boolean
    *   "approved" boolean
    *   "applicant":
    * }
    * */
    public void reviewRequest() {}
    public void answerRequest() {}
    public void makeQuote() {}
    public void makeQuotationReport() {}
    public void respondingQuotationReport() {}

}

package com.genesiscode.quotation.service;
import com.genesiscode.quotation.domain.*;
import com.genesiscode.quotation.registration.token.ConfirmationToken;
import com.genesiscode.quotation.registration.token.ConfirmationTokenService;
import com.genesiscode.quotation.repository.*;
import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.genesiscode.quotation.utils.Preconditions.*;

import java.time.LocalDateTime;
import java.util.*;

@AllArgsConstructor
@Service
public class ResponsibleService /*implements UserDetailsService */{

    private final static String USER_NOT_FOUND_MESSAGE = "Responsible with email %s not found";
    private final static String MUST_NOT_BE_NULL = "%s must not be null";
    private static final String TYPE_INCOMPATIBLE = "Data Type Incompatible";
    private static final String ROLE_MUST_NOT_BE_CREATED = "You couldn't create this Role";

    private final RoleRepository roleRepository;
    private final ResponsibleRepository responsibleRepository;
    private final PasswordEncoder passwordEncoder;
    private final ConfirmationTokenService confirmationTokenService;

    /*@Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return responsibleRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException
                        (String.format("User with email %s not found", email)));
    }*/

    public Role createRole(Role role) {
        checkArgument(role != null, String.format(MUST_NOT_BE_NULL, "Role"));
        checkDataType(role, Role.class, TYPE_INCOMPATIBLE);

        boolean exists = roleRepository.existsByName(role.getName());
        if(exists)
            throw new IllegalStateException("The role: " + role.getName() + " already exists");
        return roleRepository.save(role);
    }

    public void deleteRoleById(Long id) {
        checkArgument(id != null, String.format(MUST_NOT_BE_NULL, id));
        checkDataType(id, Long.class, TYPE_INCOMPATIBLE);

        boolean exists = roleRepository.existsById(id);
        if(! exists)
            throw new IllegalStateException("Role with id: " + id + " does not exists" );

        roleRepository.deleteById(id);
    }

    public Role getRoleById(Long id) {
        checkArgument(id != null, String.format(MUST_NOT_BE_NULL, id));
        checkDataType(id, Long.class, TYPE_INCOMPATIBLE);

        return roleRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Role with id: " + id + " does not exists"));
    }

    public List<Functionality> getListPermissionWithIdRole(Long idRole) {
        Role role = getRoleById(idRole);
        List<Functionality> functionalities = new ArrayList<>();
        for(Permission permission : role.getPermission())
            functionalities.add(permission.getFunctionality());
        return functionalities;
    }

    public void updatePermissionOfTheRole(Long idRole, List<Permission> permissions) {
        checkArgument( permissions != null, MUST_NOT_BE_NULL);
        boolean exists = roleRepository.existsById(idRole);
        if(! exists)
            throw new IllegalStateException("Role with id: " + idRole + " does not exists");

        roleRepository.updatePermissionById(idRole, permissions);

    }

    @Transactional
    public String createResponsible(Responsible responsible) {
        boolean responsibleExists = responsibleRepository
                                        .findByEmail(responsible.getEmail()).isPresent();

        if(responsibleExists)
            throw new IllegalStateException("Email already taken");

        String token = UUID.randomUUID().toString();
        responsible.setPassword(token);
        responsibleRepository.save(responsible);
        ConfirmationToken confirmationToken = new ConfirmationToken(token, LocalDateTime.now(),
                                            LocalDateTime.now().plusMinutes(15), responsible);
        confirmationTokenService.saveConfirmationToken(confirmationToken);

        return token;
    }


    public void enableResponsible(String email) {
        responsibleRepository.enableResponsible(email);
    }
/*
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return responsibleRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException
                        (String.format(USER_NOT_FOUND_MESSAGE, email)));
    }

    @Transactional
    public String signUp(Responsible responsible) {
        boolean responsibleExists = responsibleRepository
                                        .findByEmail(responsible.getEmail())
                                        .isPresent();
        if(responsibleExists)
            throw new IllegalStateException("Email already taken");
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = bCryptPasswordEncoder.encode(responsible.getPassword());
        responsible.setPassword(encodedPassword);

        responsibleRepository.save(responsible);

        //TODO: Send Confirmation token
        String token = UUID.randomUUID().toString();
        ConfirmationToken confirmationToken = new ConfirmationToken(token, LocalDateTime.now(),
                                            LocalDateTime.now().plusMinutes(15), responsible);

        confirmationTokenService.saveConfirmationToken(confirmationToken);

        //TODO: send email

        return token;
    }

    public void createDirectionUnit(DirectionUnit directionUnit) {
        checkArgument(directionUnit != null, String.format(MUST_NOT_BE_NULL, "Direction Unit"));
        checkDataType(directionUnit, DirectionUnit.class, TYPE_INCOMPATIBLE);

        directionUnitRepository.save(directionUnit);
    }

    public void createHeadOfDirectionUnit(Responsible headOfDirectionUnit) {
        checkArgument(headOfDirectionUnit != null, String.format(MUST_NOT_BE_NULL, "Responsible"));
        checkDataType(headOfDirectionUnit, Responsible.class, TYPE_INCOMPATIBLE);

        if(headOfDirectionUnit.getRole() != RoleResponsible.HEAD_OF_DIRECTION)
            throw new IllegalArgumentException(ROLE_MUST_NOT_BE_CREATED);
        responsibleRepository.save(headOfDirectionUnit);

    }

    public List<DirectionUnit> getDirectionUnits() {
        return directionUnitRepository.findAll();
    }

    public DirectionUnit getDirectionUnitById(Long id) {
        checkArgument(id != null, String.format(MUST_NOT_BE_NULL, "ID"));
        checkDataType(id, Long.class , TYPE_INCOMPATIBLE);

        return directionUnitRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("No exists this direction unit with id " + id));
    }

    public void createExpenseUnit(ExpenseUnit unit) {
        checkArgument(unit != null, String.format(MUST_NOT_BE_NULL, "Expense Unit"));
        checkDataType(unit, ExpenseUnit.class, TYPE_INCOMPATIBLE);

        expenseUnitRepository.save(unit);
    }

    public void createHeadOfExpenseUnit(Responsible headOFExpenseUnit) {
        checkArgument(headOFExpenseUnit != null, String.format(MUST_NOT_BE_NULL, "Head of Expense Unit"));
        checkDataType(headOFExpenseUnit, Responsible.class, TYPE_INCOMPATIBLE);

        if(headOFExpenseUnit.getRole() != RoleResponsible.HEAD_OF_EXPENSE_UNIT)
            throw new IllegalArgumentException(ROLE_MUST_NOT_BE_CREATED);

        responsibleRepository.save(headOFExpenseUnit);
    }

    public List<ExpenseUnit> getExpenseUnits() {
        return expenseUnitRepository.findAll();
    }

    public ExpenseUnit getExpenseUnitById(Long id) {
        checkArgument(id != null, String.format(MUST_NOT_BE_NULL, "ID"));
        checkDataType(id, Long.class, TYPE_INCOMPATIBLE);

        return expenseUnitRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException
                        ("No exists this expense unit with id " + id));
    }*/
}

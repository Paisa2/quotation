package com.genesiscode.quotation.service;

import com.genesiscode.quotation.domain.ExpenseUnit;
import com.genesiscode.quotation.domain.unit.DirectionUnit;
import com.genesiscode.quotation.domain.user.Responsible;
import com.genesiscode.quotation.repository.*;
import static com.genesiscode.quotation.utils.Preconditions.*;
import com.genesiscode.quotation.security.RoleResponsible;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.*;

@AllArgsConstructor
@Service
public class ResponsibleService implements UserDetailsService {

    private final static String USER_NOT_FOUND_MESSAGE = "Responsible with email %s not found";
    private static final String TYPE_INCOMPATIBLE = "Data Type Incompatible";

    private final DirectionUnitRepository directionUnitRepository;
    private final ResponsibleRepository responsibleRepository;
    private final ExpenseUnitRepository expenseUnitRepository;

//    @Autowired
//    public ResponsibleService(DirectionUnitRepository directionUnitRepository,
//                              ResponsibleRepository responsibleRepository,
//                              ExpenseUnitRepository expenseUnitRepository) {
//        this.directionUnitRepository = directionUnitRepository;
//        this.responsibleRepository = responsibleRepository;
//        this.expenseUnitRepository = expenseUnitRepository;
//    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return responsibleRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException
                        (String.format(USER_NOT_FOUND_MESSAGE, email)));
    }

    public String signUp(Responsible responsible) {
        boolean responsibleExists = responsibleRepository.findByEmail(responsible.getEmail())
                                        .isPresent();
        if(responsibleExists)
            throw new IllegalStateException("Email already taken");

        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = bCryptPasswordEncoder.encode(responsible.getPassword());
        responsible.setPassword(encodedPassword);
        responsibleRepository.save(responsible);
        //TODO: Send Confirmation token
        return "it works";
    }

    public void createDirectionUnit(DirectionUnit directionUnit) {
        checkArgument(directionUnit != null, "Direction Unit cannot be null");
        checkDataType(directionUnit, DirectionUnit.class, TYPE_INCOMPATIBLE);

        directionUnitRepository.save(directionUnit);
    }

    public void createHeadOfDirectionUnit(Responsible responsible) {
        checkArgument(responsible != null, "Responsible cannot be null");
        checkDataType(responsible, Responsible.class, "Data Type incompatible");

        if(responsible.getRole() != RoleResponsible.HEAD_OF_DIRECTION)
            throw new IllegalArgumentException("You couldn't create this Role");
        responsibleRepository.save(responsible);

    }

    public List<DirectionUnit> getDirectionUnits() {
        return directionUnitRepository.findAll();
    }

    public DirectionUnit getDirectionUnitById(Long id) {
        checkArgument(id != null, "Id must not be null");
        checkDataType(id, Long.class , TYPE_INCOMPATIBLE);

        return directionUnitRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("No exists this direction unit with id " + id));
    }

    public void createExpenseUnit(ExpenseUnit unit) {
        checkArgument(unit != null, "Expense Unit cannot be null");
        checkDataType(unit, ExpenseUnit.class, TYPE_INCOMPATIBLE);

        expenseUnitRepository.save(unit);
    }


    public void createHeadOfExpenseUnit(Responsible headOFExpenseUnit) {
        checkArgument(headOFExpenseUnit != null, "Head of Expense Unit must not be null");
        checkDataType(headOFExpenseUnit, Responsible.class, TYPE_INCOMPATIBLE);

        if(headOFExpenseUnit.getRole() != RoleResponsible.HEAD_OF_EXPENSE_UNIT)
            throw new IllegalArgumentException("You must not create this role");

        responsibleRepository.save(headOFExpenseUnit);
    }

    public List<ExpenseUnit> getExpenseUnits() {
        return expenseUnitRepository.findAll();
    }


    public ExpenseUnit getExpenseUnitById(Long id) {
        checkArgument(id != null, "Id must not be null");
        checkDataType(id, Long.class, TYPE_INCOMPATIBLE);

        return expenseUnitRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException
                        ("No exists this expense unit with id " + id));
    }
}

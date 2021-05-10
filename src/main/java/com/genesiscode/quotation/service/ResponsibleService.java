package com.genesiscode.quotation.service;

import com.genesiscode.quotation.domain.unit.ExpenseUnit;
import com.genesiscode.quotation.domain.unit.DirectionUnit;
import com.genesiscode.quotation.domain.user.Responsible;
import com.genesiscode.quotation.registration.token.ConfirmationToken;
import com.genesiscode.quotation.registration.token.ConfirmationTokenService;
import com.genesiscode.quotation.repository.*;
import static com.genesiscode.quotation.utils.Preconditions.*;
import com.genesiscode.quotation.security.RoleResponsible;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;

@AllArgsConstructor
@Service
public class ResponsibleService implements UserDetailsService {

    private final static String USER_NOT_FOUND_MESSAGE = "Responsible with email %s not found";
    private final static String MUST_NOT_BE_NULL = "%s must not be null";
    private static final String TYPE_INCOMPATIBLE = "Data Type Incompatible";
    private static final String ROLE_MUST_NOT_BE_CREATED = "You couldn't create this Role";

    private final DirectionUnitRepository directionUnitRepository;
    private final ResponsibleRepository responsibleRepository;
    private final ExpenseUnitRepository expenseUnitRepository;

    private final ConfirmationTokenService tokenService;

    public int enableResponsible(String email) {
        return responsibleRepository.enableResponsible(email);
    }
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
    @Transactional
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
        String token = UUID.randomUUID().toString();
        ConfirmationToken confirmationToken = new ConfirmationToken(token, LocalDateTime.now(),
                                            LocalDateTime.now().plusMinutes(15), responsible);

        tokenService.saveConfirmationToken(confirmationToken);

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
    }
}

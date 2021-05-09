package com.genesiscode.quotation.registration;

import com.genesiscode.quotation.domain.user.Responsible;
import com.genesiscode.quotation.security.RoleResponsible;
import com.genesiscode.quotation.service.ResponsibleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RegistrationService {

    private final ResponsibleService responsibleService;
    private final EmailValidator emailValidator;

    public String register(RegistrationRequest request) {
        boolean isValidEmail = emailValidator.test(request.getEmail());
        if(! isValidEmail)
            throw new IllegalStateException("Email not valid");
        return responsibleService.signUp(
                new Responsible(request.getName(), request.getLastName(), request.getEmail(),
                        request.getPassword(), RoleResponsible.HEAD_OF_DIRECTION
                ));
    }
}

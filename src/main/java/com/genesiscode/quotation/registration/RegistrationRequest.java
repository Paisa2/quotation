package com.genesiscode.quotation.registration;

import com.genesiscode.quotation.domain.Role;
import lombok.*;

@Getter @AllArgsConstructor @EqualsAndHashCode @ToString
public class RegistrationRequest {

    private final String name;
    private final String lastName;
    private final String email;
    private final Role role;

}

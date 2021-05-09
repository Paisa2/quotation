package com.genesiscode.quotation.registration;

import lombok.*;

@Getter @AllArgsConstructor @EqualsAndHashCode @ToString
public class RegistrationRequest {

    private final String name;
    private final String lastName;
    private final String password;
    private final String email;

}

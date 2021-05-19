package com.genesiscode.quotation.domain;
import lombok.*;

@Getter @AllArgsConstructor @EqualsAndHashCode @ToString
public class RegistrationRequest {

    private final String name;
    private final String lastName;
    private final String email;
    private final Role role;

}

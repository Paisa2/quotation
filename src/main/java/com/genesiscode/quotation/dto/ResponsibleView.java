package com.genesiscode.quotation.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ResponsibleView {

    private final String name;
    private final String lastName;
    private final String email;
    private final boolean enabled;
    private final String nameRole;


}

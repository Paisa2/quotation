package com.genesiscode.quotation.domain;
import lombok.*;


public class ResponsibleView {

    private String name;
    private String lastName;
    private String email;
    private boolean enabled;
    private String nameRole;

    public ResponsibleView(String name, String lastName, String email, boolean enabled, String nameRole) {
        super();
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.enabled = enabled;
        this.nameRole = nameRole;
    }
}

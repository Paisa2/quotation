package com.genesiscode.quotation.security;

public enum ResponsiblePermission {

    // HEAD OF ADMINISTRATIVE UNIT
    WRITE_DIRECTORE_OF_EXPENSE_UNIT("write:directionUnit"),
    WRITE_HEAD_OF_DIRECTORE_OF_EXPENSE_UNIT("write:headOfDirectionUnit");

    private final String permission;

    ResponsiblePermission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}

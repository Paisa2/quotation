package com.genesiscode.quotation.security;

public enum ResponsiblePermission {

    //HEAD OF ADMINISTRATIVE UNIT
    WRITE_DIRECTION_UNIT("write:directionUnit"),
    WRITE_HEAD_OF_DIRECTION_UNIT("write:headOfDirectionUnit"),
    READ_DIRECTION_UNITS("read:directionUnits"),
    READ_DIRECTION_UNIT_BY_ID("read:directionUnitById"),

    //HEAD OF DIRECTION UNIT
    WRITE_EXPENSE_UNIT("write:expenseUnit"),
    WRITE_HEAD_OF_EXPENSE_UNIT("write:headOfExpenseUnit"),
    READ_EXPENSE_UNITS("read:expenseUnits"),
    READ_EXPENSE_UNIT_BY_ID("read:expenseUnitById");

    private final String permission;

    ResponsiblePermission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}

package com.genesiscode.quotation.domain;

public enum Functionality {

    MANAGE_ROLE("manage:role"),
    MANAGE_USER("manage:user"),
    MAKE_REQUEST("make:request"),
    REVIEW_REQUEST("review:request"),
    ANSWER_REQUEST("answer:request"),
    MAKE_QUOTE("make:quote"),
    MAKE_QUOTATION_REPORT("make:quotation:report"),
    RESPONDING_QUOTATION_REPORT("responding:quotation:report");

    private final String functionality;

    Functionality(String functionality) {
        this.functionality = functionality;
    }

    public String getPermission() {
        return functionality;
    }

}

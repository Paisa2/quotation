package com.genesiscode.quotation.utils;

import org.springframework.stereotype.Service;

import java.util.function.Predicate;
import java.util.regex.*;

@Service
public class EmailValidator implements Predicate<String> {

    private static final String PATTERN_VALIDATOR
            = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    @Override
    public boolean test(String email) {
        Pattern pattern = Pattern.compile(PATTERN_VALIDATOR);
        Matcher matcher = pattern.matcher(email);
        return matcher.find();
    }
}

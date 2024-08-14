package com.readyup.api.validator;

import com.readyup.api.request.CreatePersonRequest;
import com.readyup.manager.util.StringUtil;
import org.springframework.http.ResponseEntity;

import java.util.regex.Pattern;

public class Validator {
    private static final String emailRegexPattern = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
            + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";

    public static Boolean validate(CreatePersonRequest request) {
        if (!validEmail(request.getEmail())) {
            return false;
        }
        return true;
    }

    public static Boolean validEmail(String email) {
        return Pattern.compile(emailRegexPattern)
                .matcher(email)
                .matches();
    }

    public static Boolean validUsername(String username) {
        String nonAlphaUsername = StringUtil.removeNonAlphaNumeric(username);
        return nonAlphaUsername.equals(username);
    }
}

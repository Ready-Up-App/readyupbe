package com.readyup.manager.util;

public abstract class StringUtil {

    public static String removeNonAlphaNumeric(String value) {
        value = value == null ? "": value;
        return value.replaceAll("\\W", "");
    }
}

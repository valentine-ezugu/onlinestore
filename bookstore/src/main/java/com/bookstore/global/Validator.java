package com.bookstore.global;

import java.util.regex.Pattern;

public class Validator {

    public static final Pattern EMAIL_PATTERN = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"

            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");

    public static final Pattern USERNAME_PATTERN = Pattern.compile("^[a-z0-9_-]{6,15}$");

    public static final Pattern PASSWORD_PATTERN = Pattern.compile("^[a-zA-Z0-9]{8,15}$");

}

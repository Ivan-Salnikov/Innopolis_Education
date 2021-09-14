package edu.innopolis.homework05;

import edu.innopolis.homework05.exceptions.BadEmailException;
import edu.innopolis.homework05.exceptions.BadPasswordException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserValidator {
    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    public static final Pattern VALID_PASSWORD_REGEX =
            Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).*[A-Za-z0-9]$");

    public static final int MIN_PASSWORD_LENGTH = 7;

    public static boolean isValidEmail (String email) {

        if(!email.contains("@")) {
            throw new BadEmailException("Incorrect email address");
        }

        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email);
        if(!matcher.matches()) {
            throw new BadEmailException("Incorrect email address");
        }

        return true;
    }

    public static boolean isValidPassword (String password) {

        if(password.length() < MIN_PASSWORD_LENGTH) {
            throw new BadPasswordException("Not enough password length");
        }

        Matcher matcher = VALID_PASSWORD_REGEX.matcher(password);

        if(!matcher.matches()) {
            throw new BadPasswordException("Incorrect symbols in the password. " +
                    "Password must contains at least one number, letter in small case, letter in upper case. " +
                    "Special symbols and spaces are forbidden");
        } else {
            return true;
        }
    }
}

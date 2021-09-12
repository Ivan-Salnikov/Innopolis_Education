package edu.innopolis.task_4_exceptions;

import edu.innopolis.task_4_exceptions.exceptions.BadEmailException;
import edu.innopolis.task_4_exceptions.exceptions.BadPasswordException;
import edu.innopolis.task_4_exceptions.exceptions.UserNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ZoomUsersService implements UsersService {
    private List<User> userList;
    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    public static final Pattern VALID_PASSWORD_REGEX =
            Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).*[A-Za-z0-9]$");

    //(?=\S+$)

    public static final int MIN_PASSWORD_LENGTH = 7;

    public ZoomUsersService() {
        this.userList = new ArrayList<>();
    }


    @Override
    public void signUp(String email, String password) {

        if(!isValidEmail(email) || !isValidPassword(password)) return;

        if(isUserExists(email)) {
            userList.add(new User(email, password));
            System.out.println("User with email " + email + " was registered. Now you can sign in");
        } else {
            System.out.println("User with email " + email + " already exists. Please sign in");
        }
    }

    @Override
    public void signIn(String email, String password) {

        if(isUserExists(email)) {
            throw new UserNotFoundException("User with email " + email + " not registered. Please sign up");
        }

        if(!isPasswordCorrect(email, password)) {
            throw new BadPasswordException("Incorrect password ");
        }

        System.out.println("User with email \"" + email + "\" sign in");
    }


    public static boolean isValidEmail (String email) {
        boolean result;

        if(!email.contains("@")) {
            throw new BadEmailException("Incorrect email address");
        }


        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email);
        result = matcher.matches();

        return result;
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


    private boolean isUserExists (String email) {
        return userList.stream()
                .noneMatch((p) -> p.getEmail().contains(email));
    }

    private boolean isPasswordCorrect (String email, String password) {
        return userList.stream()
                .filter((p) -> p.getEmail().contains(email))
                .allMatch((p) -> p.getPassword().contains(password));
    }
}

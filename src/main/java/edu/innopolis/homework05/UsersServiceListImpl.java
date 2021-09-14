package edu.innopolis.homework05;

import edu.innopolis.homework05.exceptions.BadEmailException;
import edu.innopolis.homework05.exceptions.BadPasswordException;
import edu.innopolis.homework05.exceptions.UserNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UsersServiceListImpl implements UsersService {
    private List<User> userList;

    public UsersServiceListImpl() {

        this.userList = new ArrayList<>();
    }


    @Override
    public void signUp(String email, String password) {

        if(!UserValidator.isValidEmail(email) || !UserValidator.isValidPassword(password)) return;

        if(!isUserExists(email)) {
            userList.add(new User(email, password));
            System.out.println("User with email " + email + " was registered. Now you can sign in");
        } else {
            System.out.println("User with email " + email + " already exists. Please sign in");
        }
    }

    @Override
    public void signIn(String email, String password) {

        if(!isUserExists(email)) {
            throw new UserNotFoundException("User with email " + email + " not registered. Please sign up");
        }

        if(!isPasswordCorrect(email, password)) {
            throw new BadPasswordException("Incorrect password ");
        }

        setSignInState(email);

        System.out.println("User with email \"" + email + "\" sign in");
    }

    private void setSignInState (String email) {
        userList.stream()
                .filter((p) -> p.getEmail().contains(email)).findFirst().get().setSignIn(true);
    }

    public boolean getSignInState(String email) {

        if(!isUserExists(email)) {
            throw new UserNotFoundException("User with email " + email + " not registered. Please sign up");
        }

        return userList.stream()
                .filter((p) -> p.getEmail().contains(email)).findFirst().get().getSignInState();
    }


    public boolean isUserExists (String email) {
        return userList.stream()
                .anyMatch((p) -> p.getEmail().contains(email));
    }

    private boolean isPasswordCorrect (String email, String password) {
        return userList.stream()
                .filter((p) -> p.getEmail().contains(email))
                .allMatch((p) -> p.getPassword().contains(password));
    }
}

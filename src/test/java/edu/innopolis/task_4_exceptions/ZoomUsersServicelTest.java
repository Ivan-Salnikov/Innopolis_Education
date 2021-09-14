package edu.innopolis.task_4_exceptions;

import edu.innopolis.task_4_exceptions.exceptions.BadEmailException;
import edu.innopolis.task_4_exceptions.exceptions.BadPasswordException;
import edu.innopolis.task_4_exceptions.exceptions.UserNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ZoomUsersServicelTest {


    @Test
    void givenEmailAndPassword_whenCorrect_thenSignUp() {
        ZoomUsersService zoomUsersService = new ZoomUsersService();
        zoomUsersService.signUp("d@d.dd", "88gg77889G");
        Assertions.assertTrue(zoomUsersService.isUserExists("d@d.dd"));
    }

    @Test
    void givenEmailAndPassword_whenEmailIncorrect_thenThrowBadEmailException() {
        String correctPassword = "123Aa67";
        ZoomUsersService zoomUsersService = new ZoomUsersService();

        //test with lambda-s
        assertThrows(BadEmailException.class,
                () -> zoomUsersService.signUp("ddd.dd", correctPassword)); // '@' - not included
        assertThrows(BadEmailException.class,
                () -> zoomUsersService.signUp("dd@@dd.dd", correctPassword)); // '@' - more than one times
        assertThrows(BadEmailException.class,
                () -> zoomUsersService.signUp("", correctPassword)); // empty
        assertThrows(BadEmailException.class,
                () -> zoomUsersService.signUp("12@12.12", correctPassword));  //digits in domain zone
        assertThrows(BadEmailException.class,
                () -> zoomUsersService.signUp("dd@.dd", correctPassword)); //empty domain name
        assertThrows(BadEmailException.class,
                () -> zoomUsersService.signUp("dd@ddd", correctPassword)); // '.' - not included
        assertThrows(BadEmailException.class,
                () -> zoomUsersService.signUp("dd@ddd.s", correctPassword)); // - not enough length domain zone
    }

    @Test
    void givenEmailAndPassword_whenPasswordIncorrect_thenThrowBadPasswordException() {
        String correctEmail = "user@some.com";
        ZoomUsersService zoomUsersService = new ZoomUsersService();

        //test with lambda-s

        assertThrows(BadPasswordException.class,
                () -> zoomUsersService.signUp(correctEmail, "Aa1234"));//not enough length
        assertThrows(BadPasswordException.class,
                () -> zoomUsersService.signUp(correctEmail, "12345678")); //not enough chars
        assertThrows(BadPasswordException.class,
                () -> zoomUsersService.signUp(correctEmail, "AAAAaaaa")); //not enough digits
        assertThrows(BadPasswordException.class,
                () -> zoomUsersService.signUp(correctEmail, "AAAA12345")); //not enough low case
        assertThrows(BadPasswordException.class,
                () -> zoomUsersService.signUp(correctEmail, "aaa12345")); //not enough upper case
        assertThrows(BadPasswordException.class,
                () -> zoomUsersService.signUp(correctEmail, "aaaAA123%")); //special symbols
        assertThrows(BadPasswordException.class,
                () -> zoomUsersService.signUp(correctEmail, "aaa AA123")); //spaces
    }

    @Test
    void givenEmailAndPassword_whenUserNotFound_thenThrowUserNotFoundException() {
        ZoomUsersService zoomUsersService = new ZoomUsersService();
        zoomUsersService.signUp("user@some.com", "Aa12345");

        boolean isUserNotFound = false;

        //tests with try-catch blocks
        try {
            zoomUsersService.signIn("newUser@some.com", "Bb12345");
        } catch (UserNotFoundException e) {
            isUserNotFound = true;
        }

        try {
            zoomUsersService.getSignInState("newUser@some.com");
        } catch (UserNotFoundException e) {
            isUserNotFound = true;
        }

        Assertions.assertTrue(isUserNotFound);
    }
}
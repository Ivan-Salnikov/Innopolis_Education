package edu.innopolis.homework05;

import edu.innopolis.homework05.exceptions.BadPasswordException;

import java.io.IOException;
import java.nio.file.Files;
import java.util.stream.Stream;

public class UsersServiceFileImpl implements UsersService {
    private final UsersRepository usersRepository;

    public UsersServiceFileImpl(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public void signUp(String email, String password) {
        if(!UserValidator.isValidEmail(email) || !UserValidator.isValidPassword(password)) return;

        if (!usersRepository.existsByEmail(email)) {
            User user = new User(email, password);
            usersRepository.save(user);
        } else throw new IllegalArgumentException("Email already exists");
    }

    @Override
    public void signIn(String email, String password) {

        if (usersRepository.isPasswordCorrect(email, password)) {
            User user = new User(email, password);
            user.setSignIn(true);
            usersRepository.update(user);
        } else throw new BadPasswordException("Incorrect password ");

    }
}

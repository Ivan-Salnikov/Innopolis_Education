package edu.innopolis.homework05;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.StringJoiner;

import static org.junit.jupiter.api.Assertions.*;

class UsersServiceFileImplTest {

    @Test
    void givenEmailAndPassword_whenAllCorrect_thenSignUp() {
        String testMethodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        String usersIdFileName = "test_" + testMethodName + "_users_id.txt";
        String usersRepositoryFileName = "test_" + testMethodName + "_users.txt";

        IdGenerator idGenerator = new IdGeneratorFileImpl(usersIdFileName);
        UsersRepository usersRepository = new UsersRepositoryFileImpl(usersRepositoryFileName, idGenerator);
        UsersService usersService = new UsersServiceFileImpl(usersRepository);

        if(usersRepository.count() == 0) {
            usersService.signUp("email.1@email.com", "Asdf456");
        }

        boolean b = usersRepository.existsByEmail("email.1@email.com");

        try {
            Files.deleteIfExists(Paths.get(usersIdFileName));
            Files.deleteIfExists(Paths.get(usersRepositoryFileName));
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
        Assertions.assertTrue(b);
    }

    @Test
    void signIn() {
    }
}
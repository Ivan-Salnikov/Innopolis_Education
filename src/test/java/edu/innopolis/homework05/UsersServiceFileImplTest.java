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
    void givenEmailAndPassword_whenUserExistsAndPasswordCorrect_thenSignIn() {
        String testMethodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        String usersIdFileName = "test_" + testMethodName + "_users_id.txt";
        String usersRepositoryFileName = "test_" + testMethodName + "_users.txt";

        IdGenerator idGenerator = new IdGeneratorFileImpl(usersIdFileName);
        UsersRepository usersRepository = new UsersRepositoryFileImpl(usersRepositoryFileName, idGenerator);
        UsersService usersService = new UsersServiceFileImpl(usersRepository);

        if(usersRepository.count() == 0) {
            usersService.signUp("email_1@email.com", "Asdfgh1");
            usersService.signUp("email_2@email.com", "Asdfgh2");
            usersService.signUp("email_3@email.com", "Asdfgh3");
            usersService.signUp("email_4@email.com", "Asdfgh4");
            usersService.signUp("email_5@email.com", "Asdfgh5");
        }

        usersService.signIn("email_4@email.com", "Asdfgh4");

        if (!usersRepository.findByEmail("email_4@email.com").isPresent()) fail();

        boolean b = usersRepository.findByEmail("email_4@email.com").get().getSignInState();

        try {
            Files.deleteIfExists(Paths.get(usersIdFileName));
            Files.deleteIfExists(Paths.get(usersRepositoryFileName));
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
        Assertions.assertTrue(b);
    }
}
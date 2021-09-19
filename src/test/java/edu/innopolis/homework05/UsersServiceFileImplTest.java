package edu.innopolis.homework05;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.StringJoiner;

import static org.junit.jupiter.api.Assertions.*;

class UsersServiceFileImplTest {
    private String usersIdFileName = "test_users_id.txt";
    private String usersRepositoryFileName = "test_users.txt";
    private IdGenerator idGenerator;
    private UsersRepository usersRepository;
    UsersService usersService;

    @BeforeEach
    void beforeEach (){
        idGenerator = new IdGeneratorFileImpl(usersIdFileName);
        usersRepository = new UsersRepositoryFileImpl(usersRepositoryFileName, idGenerator);
        usersService = new UsersServiceFileImpl(usersRepository);
    }

    @AfterEach
    void afterEach(){
        try {
            Files.deleteIfExists(Paths.get(usersIdFileName));
            Files.deleteIfExists(Paths.get(usersRepositoryFileName));
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Test
    void givenEmailAndPassword_whenAllCorrect_thenSignUp() {

        if(usersRepository.count() == 0) {
            usersService.signUp("email.1@email.com", "Asdf456");
        }
        boolean b = usersRepository.existsByEmail("email.1@email.com");

        Assertions.assertTrue(b);
    }

    @Test
    void givenEmailAndPassword_whenUserExistsAndPasswordCorrect_thenSignIn() {

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


        Assertions.assertTrue(b);
    }
}
package edu.innopolis.homework05;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class UsersRepositoryFileImplTest {

    @Test
    void findByEmail() {
    }

    @Test
    void findAll() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }

    @Test
    void save() {
    }

    @Test
    void count() {
    }

    @Test
    void givenEmail_whenEmailExists_thenTrue() {
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
    void givenEmail_whenEmailNotExists_thenFalse() {
        IdGenerator idGenerator = new IdGeneratorFileImpl("users_id.txt");
        UsersRepository usersRepository = new UsersRepositoryFileImpl("users.txt", idGenerator);
        UsersService usersService = new UsersServiceFileImpl(usersRepository);

        if(usersRepository.count() == 0) {
            usersService.signUp("email.1@email.com", "Asdf456");
        }

        Assertions.assertFalse(usersRepository.existsByEmail("email.not.exists@email.com"));
    }

}
package edu.innopolis.homework05;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

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
        IdGenerator idGenerator = new IdGeneratorFileImpl("users_id.txt");
        UsersRepository usersRepository = new UsersRepositoryFileImpl("users.txt", idGenerator);
        UsersService usersService = new UsersServiceFileImpl(usersRepository);

        if(usersRepository.count() == 0) {
            usersService.signUp("email.1@email.com", "Asdf456");
        }

        Assertions.assertTrue(usersRepository.existsByEmail("email.1@email.com"));
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
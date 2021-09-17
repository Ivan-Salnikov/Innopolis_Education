package edu.innopolis.homework05;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;


class UsersRepositoryFileImplTest {

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
    void findByEmail_givenEmail_whenEmailExists_thenReturnUser() {


        if(usersRepository.count() == 0) {
            usersService.signUp("email.1@email.com", "Asdf456");
            usersService.signUp("email.2@email.com", "Asdf456");
            usersService.signUp("email.3@email.com", "Asdf456");
            usersService.signUp("email.4@email.com", "Asdf456");
            usersService.signUp("email.5@email.com", "Asdf456");
        }

        Optional<User> user = usersRepository.findByEmail("email.3@email.com");

        Assertions.assertTrue(user.isPresent());

        boolean b = (user.get().getEmail().equals("email.3@email.com"));

        Assertions.assertTrue(b);

    }

    @Test
    void findByEmail_givenEmail_whenEmailNotExists_thenReturnOptional() {

        if(usersRepository.count() == 0) {
            usersService.signUp("email.1@email.com", "Asdf456");
            usersService.signUp("email.2@email.com", "Asdf456");
            usersService.signUp("email.3@email.com", "Asdf456");
            usersService.signUp("email.4@email.com", "Asdf456");
            usersService.signUp("email.5@email.com", "Asdf456");
        }

        Optional<User> user = usersRepository.findByEmail("invalid.3@email.com");
        Assertions.assertFalse(user.isPresent());

    }


    @Test
    void findAll_whenUsersExists_thenReturnList() {

        if(usersRepository.count() == 0) {
            usersService.signUp("email.1@email.com", "Asdf456");
            usersService.signUp("email.2@email.com", "Asdf456");
            usersService.signUp("email.3@email.com", "Asdf456");
            usersService.signUp("email.4@email.com", "Asdf456RRRRR");
            usersService.signUp("email.5@email.com", "Asdf456");
        }

        List<User> userList = usersRepository.findAll();

        boolean b = (userList.get(3).getEmail().equals("email.4@email.com"))
                && (userList.get(3).getPassword().equals("Asdf456RRRRR"));

        Assertions.assertTrue(b);


    }

    @Test
    void givenUser_whenUserExists_thenUpdateUser() {

        if(usersRepository.count() == 0) {
            usersService.signUp("email.1@email.com", "Asdf456");
            usersService.signUp("email.2@email.com", "Asdf456");
            usersService.signUp("email.3@email.com", "Asdf456");
            usersService.signUp("email.4@email.com", "Asdf456");
            usersService.signUp("email.5@email.com", "Asdf456");
        }

        User user = usersRepository.findByEmail("email.4@email.com").get();
        String newPassword = "NEWAsdf456";

        user.setPassword(newPassword);
        usersRepository.update(user);

        user = usersRepository.findByEmail("email.4@email.com").get();

        boolean b = user.getPassword().equals(newPassword);

        Assertions.assertTrue(b);


    }

    @Test
    void givenUser_whenUserExists_thenDeleteUser() {

        if(usersRepository.count() == 0) {
            usersService.signUp("email.1@email.com", "Asdf456");
            usersService.signUp("email.2@email.com", "Asdf456");
            usersService.signUp("email.3@email.com", "Asdf456");
            usersService.signUp("email.4@email.com", "Asdf456");
            usersService.signUp("email.5@email.com", "Asdf456");
        }

        User user = usersRepository.findByEmail("email.4@email.com").get();
        usersRepository.delete(user);

        boolean b = usersRepository.existsByEmail("email.4@email.com");

        Assertions.assertFalse(b);

    }

    @Test
    void givenEmail_whenEmailExists_thenTrue() {

        if(usersRepository.count() == 0) {
            usersService.signUp("email.1@email.com", "Asdf456");
        }

        boolean b = usersRepository.existsByEmail("email.1@email.com");

        Assertions.assertTrue(b);
    }

    @Test
    void givenEmail_whenEmailNotExists_thenFalse() {

        if(usersRepository.count() == 0) {
            usersService.signUp("email.1@email.com", "Asdf456");
        }

        boolean b =usersRepository.existsByEmail("email.not.exists@email.com");

        Assertions.assertFalse(b);
    }

}
package edu.innopolis.homework05;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;


class UsersRepositoryFileImplTest {

    @Test
    void findByEmail_givenEmail_whenEmailExists_thenReturnUser() {
        String testMethodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        String usersIdFileName = "test_" + testMethodName + "_users_id.txt";
        String usersRepositoryFileName = "test_" + testMethodName + "_users.txt";

        IdGenerator idGenerator = new IdGeneratorFileImpl(usersIdFileName);
        UsersRepository usersRepository = new UsersRepositoryFileImpl(usersRepositoryFileName, idGenerator);
        UsersService usersService = new UsersServiceFileImpl(usersRepository);

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

        try {
            Files.deleteIfExists(Paths.get(usersIdFileName));
            Files.deleteIfExists(Paths.get(usersRepositoryFileName));
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }


        Assertions.assertTrue(b);

    }

    @Test
    void findByEmail_givenEmail_whenEmailNotExists_thenReturnOptional() {
        String testMethodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        String usersIdFileName = "test_" + testMethodName + "_users_id.txt";
        String usersRepositoryFileName = "test_" + testMethodName + "_users.txt";

        IdGenerator idGenerator = new IdGeneratorFileImpl(usersIdFileName);
        UsersRepository usersRepository = new UsersRepositoryFileImpl(usersRepositoryFileName, idGenerator);
        UsersService usersService = new UsersServiceFileImpl(usersRepository);

        if(usersRepository.count() == 0) {
            usersService.signUp("email.1@email.com", "Asdf456");
            usersService.signUp("email.2@email.com", "Asdf456");
            usersService.signUp("email.3@email.com", "Asdf456");
            usersService.signUp("email.4@email.com", "Asdf456");
            usersService.signUp("email.5@email.com", "Asdf456");
        }

        Optional<User> user = usersRepository.findByEmail("invalid.3@email.com");
        Assertions.assertFalse(user.isPresent());

        try {
            Files.deleteIfExists(Paths.get(usersIdFileName));
            Files.deleteIfExists(Paths.get(usersRepositoryFileName));
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }


    @Test
    void findAll_whenUsersExists_thenReturnList() {
        String testMethodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        String usersIdFileName = "test_" + testMethodName + "_users_id.txt";
        String usersRepositoryFileName = "test_" + testMethodName + "_users.txt";

        IdGenerator idGenerator = new IdGeneratorFileImpl(usersIdFileName);
        UsersRepository usersRepository = new UsersRepositoryFileImpl(usersRepositoryFileName, idGenerator);
        UsersService usersService = new UsersServiceFileImpl(usersRepository);

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

        try {
            Files.deleteIfExists(Paths.get(usersIdFileName));
            Files.deleteIfExists(Paths.get(usersRepositoryFileName));
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }

        Assertions.assertTrue(b);


    }

    @Test
    void givenUser_whenUserExists_thenUpdateUser() {
        String testMethodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        String usersIdFileName = "test_" + testMethodName + "_users_id.txt";
        String usersRepositoryFileName = "test_" + testMethodName + "_users.txt";

        IdGenerator idGenerator = new IdGeneratorFileImpl(usersIdFileName);
        UsersRepository usersRepository = new UsersRepositoryFileImpl(usersRepositoryFileName, idGenerator);
        UsersService usersService = new UsersServiceFileImpl(usersRepository);

        if(usersRepository.count() == 0) {
            usersService.signUp("email.1@email.com", "Asdf456");
            usersService.signUp("email.2@email.com", "Asdf456");
            usersService.signUp("email.3@email.com", "Asdf456");
            usersService.signUp("email.4@email.com", "Asdf456");
            usersService.signUp("email.5@email.com", "Asdf456");
        }

        User user = usersRepository.findByEmail("email.4@email.com").get();
        Integer newID = 10;
        String newPassword = "NEWAsdf456";

        user.setId(newID);
        user.setPassword(newPassword);
        usersRepository.update(user);

        user = usersRepository.findByEmail("email.4@email.com").get();

        boolean b = (user.getId().equals(newID)) && (user.getPassword().equals(newPassword));

        try {
            Files.deleteIfExists(Paths.get(usersIdFileName));
            Files.deleteIfExists(Paths.get(usersRepositoryFileName));
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }

        Assertions.assertTrue(b);


    }

    @Test
    void givenUser_whenUserExists_thenDeleteUser() {
        String testMethodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        String usersIdFileName = "test_" + testMethodName + "_users_id.txt";
        String usersRepositoryFileName = "test_" + testMethodName + "_users.txt";

        IdGenerator idGenerator = new IdGeneratorFileImpl(usersIdFileName);
        UsersRepository usersRepository = new UsersRepositoryFileImpl(usersRepositoryFileName, idGenerator);
        UsersService usersService = new UsersServiceFileImpl(usersRepository);

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

        try {
            Files.deleteIfExists(Paths.get(usersIdFileName));
            Files.deleteIfExists(Paths.get(usersRepositoryFileName));
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }

        Assertions.assertFalse(b);

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
        String testMethodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        String usersIdFileName = "test_" + testMethodName + "_users_id.txt";
        String usersRepositoryFileName = "test_" + testMethodName + "_users.txt";

        IdGenerator idGenerator = new IdGeneratorFileImpl(usersIdFileName);
        UsersRepository usersRepository = new UsersRepositoryFileImpl(usersRepositoryFileName, idGenerator);
        UsersService usersService = new UsersServiceFileImpl(usersRepository);

        if(usersRepository.count() == 0) {
            usersService.signUp("email.1@email.com", "Asdf456");
        }

        boolean b =usersRepository.existsByEmail("email.not.exists@email.com");

        try {
            Files.deleteIfExists(Paths.get(usersIdFileName));
            Files.deleteIfExists(Paths.get(usersRepositoryFileName));
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }

        Assertions.assertFalse(b);
    }

}
package edu.innopolis.homework05;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class UsersRepositoryFileImpl implements UsersRepository {

    private final String fileName;
    private final IdGenerator idGenerator;
    private Path path;

    public UsersRepositoryFileImpl(String fileName, IdGenerator idGenerator) {
        this.fileName = fileName;
        this.idGenerator = idGenerator;
        this.path = Paths.get(fileName);
        if(!Files.exists(path) && !Files.isDirectory(path)) {
            try {
                Files.createFile(Paths.get(fileName));
            } catch (IOException e) {
                throw new IllegalArgumentException(e);
            }

        }

    }


    @Override
    public Optional<User> findByEmail(String email) {

        try (Stream <String> userStream = Files.newBufferedReader(path).lines()){
            return  userStream.map(userMapFunction)
                    .filter(p -> p.getEmail().equals(email))
                    .findFirst();
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    private static final Function<String, User> userMapFunction =
            line -> {
                    String[] fields = line.split("\\|");
                    Integer id = Integer.parseInt(fields[0]);
                    String email = fields[1];
                    String password = fields[2];
                    boolean isSignIn = fields[3].equals("true");

                    return new User(id, email, password, isSignIn);
                    };

    @Override
    public List<User> findAll() {
        try (Stream <String> userStream = Files.newBufferedReader(path).lines()){
            return userStream.map(userMapFunction).collect(Collectors.toList());
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public void update(User user) {
        List<String> stringList;

        try (Stream <String> userStream = Files.newBufferedReader(path).lines()){
            stringList = userStream.collect(Collectors.toList());
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }

        String userId = stringList.stream()
                .map(l -> l.split("\\|"))
                .filter(s -> s[1].equals(user.getEmail()))
                .findFirst().get()[0];

        String userAsLine = userId  + "|" + user.getEmail() + "|" + user.getPassword()+ "|" + user.getSignInState();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for(String stringUser : stringList) {

                if (stringUser.contains(user.getEmail())) {
                    writer.write(userAsLine);
                } else {
                    writer.write(stringUser);
                }

                writer.newLine();
            }

        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }


    }

    @Override
    public void delete(User user) {
        List<String> stringList;

        try (Stream <String> userStream = Files.newBufferedReader(path).lines()){
            String userAsLine = user.getId() + "|" + user.getEmail() + "|" + user.getPassword()+ "|" + user.getSignInState();

            stringList = userStream.filter(p -> !p.equals(userAsLine))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for(String stringUser : stringList) {
                writer.write(stringUser);
                writer.newLine();
            }
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }

    }

    @Override
    public void save(User user) {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            user.setId(idGenerator.next());
            String userAsLine = user.getId() + "|" + user.getEmail() + "|" + user.getPassword() + "|" + user.getSignInState();
            writer.write(userAsLine);
            writer.newLine();
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public int count() {
        try (Stream <String> userStream = Files.newBufferedReader(path).lines()){
            return  (int) userStream.count();
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public boolean existsByEmail(String email) {
        try (Stream <String> userStream = Files.newBufferedReader(path).lines()){
        return  userStream.map(l -> l.split("\\|"))
                .anyMatch(s -> s[1].equals(email));
    } catch (IOException e) {
        throw new IllegalArgumentException(e);
        }
    }

    @Override
    public boolean isPasswordCorrect(String email, String password) {
        try (Stream<String> userStream = Files.newBufferedReader(path).lines()){
            return  userStream.map(l -> l.split("\\|"))
                    .filter(s -> s[1].equals(email))
                    .anyMatch(s -> s[2].equals(password));
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }
}

package edu.innopolis.homework05;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
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
        return Optional.empty();
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public void update(User user) {

    }

    @Override
    public void delete(User user) {
        try (Stream <String> userStream = Files.newBufferedReader(path).lines()){
            List<String> ls;

            ls = userStream.map(l -> l.split("\\|"))
                    .filter(s -> !s[1].equals(user.getEmail()))
                    .flatMap(Arrays::stream)
                    .collect(Collectors.toList());
            System.out.println(ls);

        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }

    }

    @Override
    public void save(User user) {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            user.setId(idGenerator.next());
            String userAsLine = user.getId() + "|" + user.getEmail() + "|" + user.getPassword();
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
}

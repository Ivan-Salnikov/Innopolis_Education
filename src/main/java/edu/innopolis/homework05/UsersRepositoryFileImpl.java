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

        try (Stream <String> userStream = Files.newBufferedReader(path).lines()){

            List<String> list = userStream.map(l -> l.split("\\|"))
                    .filter(s -> s[1].equals(email))
                    .flatMap(Arrays::stream)
                    .collect(Collectors.toList());

            if(list.size() > 3 ) throw new IllegalArgumentException("Duplicate emails in the repository");

            if(list.size() < 3 ) return Optional.empty();


            User user = new User(list.get(1), list.get(2));
            user.setId(Integer.parseInt(list.get(0)));
            return Optional.of(user);

        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public List<User> findAll() {
        try (Stream <String> userStream = Files.newBufferedReader(path).lines()){
            List<String> stringList;
            List<User> userList = new ArrayList<>();

            stringList = userStream.map(l -> l.split("\\|"))
                    .flatMap(Arrays::stream)
                    .collect(Collectors.toList());
            for(int i = 0; i < stringList.size(); i=i+3) {
                userList.add(new User(stringList.get(i+1), stringList.get(i+2)));
                userList.get(i/3).setId(Integer.parseInt(stringList.get(i)));
            }

            return  userList;

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

        String userAsLine = user.getId() + "|" + user.getEmail() + "|" + user.getPassword();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for(String stringUser : stringList) {

                writer.write(stringUser.contains(user.getEmail()) ? userAsLine : stringUser);
                writer.newLine();
            }
            int i = 0;
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }


    }

    @Override
    public void delete(User user) {
        List<String> stringList;

        try (Stream <String> userStream = Files.newBufferedReader(path).lines()){
            String userAsLine = user.getId() + "|" + user.getEmail() + "|" + user.getPassword();

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

package edu.innopolis.homework05;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class UsersRepositoryFileImpl implements UsersRepository {

    private final String fileName;
    private final IdGenerator idGenerator;

    public UsersRepositoryFileImpl(String fileName, IdGenerator idGenerator) {
        this.fileName = fileName;
        this.idGenerator = idGenerator;
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
        return 0;
    }

    @Override
    public boolean existsByEmail(String email) {
        return false;
    }
}

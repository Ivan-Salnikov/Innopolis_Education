package edu.innopolis.homework05;

import java.util.List;
import java.util.Optional;

public interface UsersRepository {
    Optional<User> findByEmail(String email);
    List<User> findAll();

    void update(User user);

    void delete(User user);

    void save(User user);

    int count();

    boolean existsByEmail(String email);

    boolean isPasswordCorrect(String email, String password);

}


package ua.goit.project.service;

import java.util.List;
import java.util.Optional;

public interface MyService<T> {
    T create(T entity);

    List<T> readAll();

    Optional<T> read(String value);

    void delete(String value);
}

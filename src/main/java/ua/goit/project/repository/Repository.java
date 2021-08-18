package ua.goit.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Optional;

@NoRepositoryBean
public interface Repository<T> extends JpaRepository<T, Integer> {
    Optional<T> findByUniqueValue(String value);

    void deleteByUniqueValue(String value);
}

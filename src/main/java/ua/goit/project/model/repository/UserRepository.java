package ua.goit.project.model.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import ua.goit.project.model.entity.User;

import java.util.Optional;

public interface UserRepository extends Repository<User> {

    @Override
    @Query("select u from User u where u.userEmail=?1")
    Optional<User> findByUniqueValue(String value);

    @Override
    @Modifying
    @Query("delete from User u where u.userEmail=?1")
    void deleteByUniqueValue(String value);
}
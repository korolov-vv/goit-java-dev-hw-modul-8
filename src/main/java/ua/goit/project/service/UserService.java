package ua.goit.project.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.goit.project.dto.enums.Role;
import ua.goit.project.dto.enums.UserStatus;
import ua.goit.project.model.entity.User;
import ua.goit.project.model.repository.Repository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements MyService<User> {
    private final Repository<User> repository;
    private final BCryptPasswordEncoder encoder;

    public UserService(Repository<User> repository, BCryptPasswordEncoder encoder) {
        this.repository = repository;
        this.encoder = encoder;
    }

    @Override
    public User save(User user) {
        if (repository.findByUniqueValue(user.getUserEmail()).isEmpty()) {
            user.setRole(Role.ROLE_USER);
            user.setPassword(encoder.encode(user.getPassword()));
            user.setUserStatus(UserStatus.ACTIVE);
            return repository.save(user);
        }
        return repository.save(user);
    }

    @Override
    public List<User> readAll() {
        return repository.findAll();
    }

    @Override
    public Optional<User> read(String value) {
        return repository.findByUniqueValue(value);
    }

    @Override
    @Transactional
    public void delete(String value) {
        repository.deleteByUniqueValue(value);
    }
}

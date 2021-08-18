package ua.goit.project.authorization;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ua.goit.project.model.entity.User;
import ua.goit.project.repository.UserRepository;

@Service(value = "userServiceDetails")
public class MyUserDetailsService implements UserDetailsService {
    private final UserRepository repository;

    @Autowired
    public MyUserDetailsService(UserRepository userRepository) {
        this.repository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final User user = repository.findByUniqueValue(username).orElseThrow(() ->
                new UsernameNotFoundException(String.format("user with username %s not exists", username)));
        return new UserPrincipal(user);
    }
}

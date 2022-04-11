package jafar.task.service;

import jafar.task.entity.AuthUser;
import jafar.task.repository.AuthUserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final AuthUserRepository repository;
    private final PasswordEncoder encoder;

    public UserService(AuthUserRepository repository, PasswordEncoder encoder) {
        this.repository = repository;
        this.encoder = encoder;
    }

    public void saveUser(AuthUser authUser) {

        authUser.setPassword(encoder.encode(authUser.getPassword()));
        authUser.setActive(true);
        authUser.setBlocked(false);
        repository.save(authUser);
    }
}

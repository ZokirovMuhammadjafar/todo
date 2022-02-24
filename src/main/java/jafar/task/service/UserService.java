package jafar.task.service;

import jafar.task.entity.AuthUser;
import jafar.task.repository.AuthUserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final AuthUserRepository repository;

    public UserService(AuthUserRepository repository) {
        this.repository = repository;
    }

    public void saveUser(AuthUser authUser) {
        repository.save(authUser);
    }
}

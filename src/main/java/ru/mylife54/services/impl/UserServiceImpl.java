package ru.mylife54.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.mylife54.models.User;
import ru.mylife54.repo.UserRepo;
import ru.mylife54.services.UserService;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo repo;

    public UserServiceImpl(UserRepo repo) {
        this.repo = repo;
    }


    @Override
    public void saveUser(User user) {
        repo.saveAndFlush(user);
    }

    @Override
    public void deleteUser(User user) {
        repo.delete(user);
    }

    @Override
    public List<User> getAllUser() {
        return repo.findAll();
    }

    @Override
    public void updateUser(User user) {
        repo.saveAndFlush(user);
    }

    @Override
    public User getUser(Long id) {
        return repo.getOne(id);
    }

    @Override
    public User getUser(String username) {
        return repo.findByUsername(username);
    }
}

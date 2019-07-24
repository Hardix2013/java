package ru.mylife54.services;

import ru.mylife54.models.User;

import java.util.List;

public interface UserService {
    void saveUser(User user);
    void deleteUser(User user);
    List<User> getAllUser();
    void updateUser(User user);
    User getUser(Long id);
    User getUser(String username);

}

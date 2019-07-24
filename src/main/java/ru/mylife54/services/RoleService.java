package ru.mylife54.services;

import ru.mylife54.models.Role;

import java.util.List;

public interface RoleService {
    void createRole(Role role);

    void deleteRole(Long id);

    List<Role> getRoles();

    Role getRole(String name);
}

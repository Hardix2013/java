package ru.mylife54.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.mylife54.models.Role;
import ru.mylife54.repo.RoleRepo;
import ru.mylife54.services.RoleService;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepo repo;

    @Override
    public void createRole(Role role) {
        repo.saveAndFlush(role);
    }

    @Override
    public void deleteRole(Long id) {
        repo.delete(repo.getOne(id));
    }

    @Override
    public List<Role> getRoles() {
        return repo.findAll();
    }

    @Override
    public Role getRole(String name) {
        return repo.findByName(name);
    }
}

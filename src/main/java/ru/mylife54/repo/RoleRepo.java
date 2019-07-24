package ru.mylife54.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.mylife54.models.Role;


@Repository
public interface RoleRepo extends JpaRepository<Role,Long> {
    Role findByName(String name);
}

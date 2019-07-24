package ru.mylife54.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.mylife54.models.User;

@Repository
public interface UserRepo  extends JpaRepository<User,Long> {
    User findByUsername(String username);
}

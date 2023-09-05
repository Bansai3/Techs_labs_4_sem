package ru.fedotov.dao.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.fedotov.dao.entity.Owner;
import ru.fedotov.dao.entity.User;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUserName(String userName);
}

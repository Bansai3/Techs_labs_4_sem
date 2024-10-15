package ru.fedotov.jpa.users.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.fedotov.jpa.users.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUserName(String userName);
}

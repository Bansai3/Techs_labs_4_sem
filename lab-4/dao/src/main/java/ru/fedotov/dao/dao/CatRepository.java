package ru.fedotov.dao.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.fedotov.dao.entity.Cat;

public interface CatRepository extends JpaRepository<Cat, Long> {

}

package ru.fedotov.jpa.cats.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import ru.fedotov.jpa.cats.entity.Cat;

public interface CatRepository extends JpaRepository<Cat, Long> {

}

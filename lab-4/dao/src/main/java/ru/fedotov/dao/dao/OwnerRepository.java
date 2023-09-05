package ru.fedotov.dao.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.fedotov.dao.entity.Owner;

import java.util.List;

public interface OwnerRepository extends JpaRepository<Owner, Long> {
}

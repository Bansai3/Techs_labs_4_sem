package ru.fedotov.dao.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.fedotov.dao.entity.Owner;

public interface OwnerRepository extends JpaRepository<Owner, Long> {
}

package ru.fedotov.jpa.owners.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import ru.fedotov.jpa.owners.entity.Owner;

public interface OwnerRepository extends JpaRepository<Owner, Long> {
}

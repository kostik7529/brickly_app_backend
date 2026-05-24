package ru.brickly.core.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.brickly.core.entity.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @EntityGraph(attributePaths = {"authorities", "cartItems"})
    Optional<User> findByUsername(String username);

    @Override
    @EntityGraph(attributePaths = {"authorities", "cartItems"})
    List<User> findAll();

    @Override
    @EntityGraph(attributePaths = {"authorities", "cartItems"})
    Page<User> findAll(Pageable pageable);
}

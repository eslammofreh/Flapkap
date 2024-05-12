package com.flapkap.vendingmachine.repository;

import com.flapkap.vendingmachine.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    void deleteById(Long id);

    User save(User user);

    Optional<User> findById(Long id);
}
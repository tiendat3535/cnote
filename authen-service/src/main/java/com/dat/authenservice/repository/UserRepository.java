package com.dat.authenservice.repository;

import com.dat.authenservice.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);
    long countByUsername(String username);

}

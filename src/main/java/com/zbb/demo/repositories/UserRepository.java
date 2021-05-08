package com.zbb.demo.repositories;

import com.zbb.demo.entities.User;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Integer>
{
    Optional<User> findByEmail(String email);
}
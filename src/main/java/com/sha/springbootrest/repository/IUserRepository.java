package com.sha.springbootrest.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sha.springbootrest.model.User;

public interface IUserRepository extends JpaRepository<User, Long> {
		Optional<User> findByUsername(String username);
}

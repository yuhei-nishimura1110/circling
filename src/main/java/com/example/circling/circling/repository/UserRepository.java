package com.example.circling.circling.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.circling.circling.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	public User findByEmail(String email);
}

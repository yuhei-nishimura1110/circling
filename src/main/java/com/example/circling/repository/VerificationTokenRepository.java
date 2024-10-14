package com.example.circling.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.circling.entity.VerificationToken;

public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Integer> {
	public VerificationToken findByToken(String token);
}

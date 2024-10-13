package com.example.circling.circling.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.circling.circling.entity.VerificationToken;

public interface VerificationTokenRepository extends JpaRepository< VerificationToken, Integer> {
	 public VerificationToken findByToken(String token);
}

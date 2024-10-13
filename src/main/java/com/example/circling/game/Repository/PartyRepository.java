package com.example.circling.game.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.circling.game.entity.Party;

public interface PartyRepository extends JpaRepository<Party, Integer> {
	public Party findByUser_idAndCount(Integer user_id, int count);

}

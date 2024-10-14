package com.example.circling.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.circling.entity.Player;

public interface PlayerRepository extends JpaRepository<Player, Integer> {
	public Page<Player> findByUser_id(Integer id, Pageable pageable);

	public Page<Player> findByUser_idAndJob(int id, int job, Pageable pageable);

	public Page<Player> findByUser_idAndJobLessThan(int id, int job, Pageable pageable);

}

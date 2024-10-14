package com.example.circling.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.circling.entity.Board;

public interface BoardRepository extends JpaRepository<Board, Integer> {

}

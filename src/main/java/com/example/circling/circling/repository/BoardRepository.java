package com.example.circling.circling.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.circling.circling.entity.Board;

public interface BoardRepository extends JpaRepository<Board, Integer> {

}

package com.example.circling.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.circling.entity.Board;
import com.example.circling.entity.Chattable;
import com.example.circling.entity.User;

public interface ChattableRepository extends JpaRepository<Chattable, Integer>{
	public List<Chattable> findByUserOrderByDtimeDesc(User user);
	public Chattable findByBoardAndUserNot(Board board,User user);
	public Chattable findByBoardAndUser(Board board,User user);
	public List<Chattable> findByBoard(Board board);
}

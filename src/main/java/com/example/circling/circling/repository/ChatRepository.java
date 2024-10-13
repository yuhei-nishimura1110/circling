package com.example.circling.circling.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.circling.circling.entity.Board;
import com.example.circling.circling.entity.Chat;

public interface ChatRepository extends JpaRepository<Chat, Integer>{
	@Query("SELECT d FROM Chat d WHERE d.board = :board ORDER BY d.id ASC")
	public List<Chat> findByBoardOrderByIdAsc(@Param("board")Board board);
	
	public default Chat findTopByBoardOrderByIdAsc(Board board) {
		List<Chat> re = findByBoardOrderByIdAsc(board);
		if(re.size() == 0) {
			return null;
		}
		return re.getLast();
	}
}

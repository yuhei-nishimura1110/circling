package com.example.circling.game.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.circling.game.entity.Item;

public interface ItemRepository extends JpaRepository<Item, Integer> {
	public Page<Item> findByUser_idAndPlayer_idAndItemkind(Integer user_id, Integer player_id, Integer item_kind,
			Pageable pageable);

	public Item findByPlayer_idAndItemkind(int player_id, int item_kind);

	public Item findByPlayer_idAndItemkindNot(int player_id, int item_kind);

	public Page<Item> findByUser_idAndPlayer_id(Integer user_id, Integer player_id, Pageable pageable);

	public Item findByUser_idAndPlayer_idAndItemkind(Integer user_id, Integer player_id, Integer item_kind);

}

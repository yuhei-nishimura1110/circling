package com.example.circling.game.entity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

import com.example.circling.circling.entity.User;

import lombok.Data;

@Data
@Entity
@Table(name = "party")
public class Party {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@OneToOne
	@JoinColumn(name = "user_id")
	private User user;

	@Column(name = "count")
	private int count;
	@OneToOne
	@JoinColumn(name = "player_id1")
	private Player player1;
	@OneToOne
	@JoinColumn(name = "player_id2")
	private Player player2;
	@OneToOne
	@JoinColumn(name = "player_id3")
	private Player player3;
	@OneToOne
	@JoinColumn(name = "player_id4")
	private Player player4;
	@OneToOne
	@JoinColumn(name = "player_id5")
	private Player player5;
	@OneToOne
	@JoinColumn(name = "player_id6")
	private Player player6;
	@OneToOne
	@JoinColumn(name = "player_id7")
	private Player player7;
	@OneToOne
	@JoinColumn(name = "player_id8")
	private Player player8;

}

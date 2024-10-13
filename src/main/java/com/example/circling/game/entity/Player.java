package com.example.circling.game.entity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import com.example.circling.circling.entity.User;

import lombok.Data;

@Data
@Entity
@Table(name = "player")
public class Player {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@Column(name = "name")
	private String name;

	@Column(name = "hp")
	private Integer hp;

	@Column(name = "mp")
	private Integer mp;

	@Column(name = "fp")
	private Integer fp;

	@Column(name = "cp")
	private Integer cp;

	@Column(name = "muscle")
	private Integer muscle;

	@Column(name = "quick")
	private Integer quick;

	@Column(name = "technique")
	private Integer technique;

	@Column(name = "magic")
	private Integer magic;

	@Column(name = "spirit")
	private Integer spirit;

	@Column(name = "knowledge")
	private Integer knowledge;

	@Column(name = "stamina")
	private Integer stamina;

	@Column(name = "eating")
	private Integer eating;

	@Column(name = "max_muscle")
	private Integer max_muscle;

	@Column(name = "max_quick")
	private Integer max_quick;

	@Column(name = "max_technique")
	private Integer max_technique;

	@Column(name = "max_magic")
	private Integer max_magic;

	@Column(name = "max_spirit")
	private Integer max_spirit;

	@Column(name = "max_knowledge")
	private Integer max_knowledge;

	@Column(name = "max_stamina")
	private Integer max_stamina;

	@Column(name = "max_eating")
	private Integer max_eating;

	@Column(name = "job")
	private Integer job;

	@Column(name = "job_sworder")
	private Integer job_sworder;

	@Column(name = "job_spearer")
	private Integer job_spearer;

	@Column(name = "job_archer")
	private Integer job_archer;

	@Column(name = "job_katanaer")
	private Integer job_katanaer;

	@Column(name = "job_axer")
	private Integer job_axer;

	@Column(name = "job_melleter")
	private Integer job_melleter;

	@Column(name = "job_cluber")
	private Integer job_cluber;

	@Column(name = "job_black_magician")
	private Integer job_black_magician;

	@Column(name = "job_shielder")
	private Integer job_shielder;

	@Column(name = "job_magician")
	private Integer job_magician;

	@Column(name = "job_white_magician")
	private Integer job_white_magician;

	@Column(name = "job_commander")
	private Integer job_commander;

	@Column(name = "job_blacksmith")
	private Integer job_blacksmith;

	@Column(name = "job_arrow_master")
	private Integer job_arrow_master;

	@Column(name = "job_katana_blacksmith")
	private Integer job_katana_blacksmith;

	@Column(name = "job_armor_blacksmith")
	private Integer job_armor_blacksmith;

	@Column(name = "job_cane_maker")
	private Integer job_cane_maker;

	@Column(name = "job_teacher")
	private Integer job_teacher;
}

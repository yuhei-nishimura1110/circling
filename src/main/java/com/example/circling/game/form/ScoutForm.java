package com.example.circling.game.form;

import com.example.circling.game.entity.Family_name;
import com.example.circling.game.repository.Family_nameRepository;

import lombok.Data;

@Data
public class ScoutForm {
	private Integer user_id;
	private String name;
	private Family_nameRepository family_nameRepository;

	public ScoutForm(Family_nameRepository family_nameRepository, Integer id, String name) {
		this.family_nameRepository = family_nameRepository;
		this.user_id = id;
		this.name = name;
	}

	public String getName() {
		if ("".equals(name)) {
			int a = new java.util.Random().nextInt(1000) + 1;
			Family_name family_name = family_nameRepository.getReferenceById(a);
			return name = family_name.getName();
		} else {
			return name;
		}
	}

}

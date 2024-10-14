package com.example.circling.form;

import lombok.Data;

@Data
public class PlayerNameEditForm {
	private String name;

	public PlayerNameEditForm(String name) {
		this.name = name;
	}
}

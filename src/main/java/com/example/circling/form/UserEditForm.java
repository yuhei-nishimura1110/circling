package com.example.circling.form;

import lombok.Data;

@Data
public class UserEditForm {
	private Integer id;
	private String evaluation;

	public UserEditForm(Integer intenger, String string) {
		this.id = intenger;
		this.evaluation = string;
	}

}
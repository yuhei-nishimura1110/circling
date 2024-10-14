package com.example.circling.form;

import lombok.Data;

@Data
public class Playertrainingform {
	private int training;

	public Playertrainingform(int training) {
		this.training = training;
	}
}

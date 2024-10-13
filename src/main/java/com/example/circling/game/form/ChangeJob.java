package com.example.circling.game.form;

import lombok.Data;

@Data
public class ChangeJob {
	private int job;

	public ChangeJob(int job) {
		this.job = job;
	}
}

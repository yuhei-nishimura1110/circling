package com.example.circling.form;

import lombok.Data;

@Data
public class ChangeJob {
	private int job;

	public ChangeJob(int job) {
		this.job = job;
	}
}

package com.example.circling.circling.form;

import jakarta.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class ChatForm {
	@NotBlank
	private String chat;
}

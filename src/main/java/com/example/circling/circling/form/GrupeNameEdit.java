package com.example.circling.circling.form;

import jakarta.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class GrupeNameEdit {
	@NotBlank(message = "名前を入力してください。")
	private String name;
}

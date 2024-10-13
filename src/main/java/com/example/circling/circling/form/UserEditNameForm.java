package com.example.circling.circling.form;

import jakarta.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserEditNameForm {
	@NotBlank(message = "氏名を入力してください。")
	private String name;
}

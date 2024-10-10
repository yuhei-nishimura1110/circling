package com.example.circling.form;

import jakarta.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserEditPhoneForm {
	@NotBlank(message = "電話番号を入力してください。")
	private String phoneNumber;
}

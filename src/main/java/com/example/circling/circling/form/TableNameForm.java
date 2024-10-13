package com.example.circling.circling.form;

import java.util.List;

import jakarta.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class TableNameForm {
	@NotBlank(message = "名前を入力してください。")
	private String name;
	private List<Integer> number;
}

package com.example.circling.circling.form;

import jakarta.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserEditPasswordForm {
	@NotBlank(message = "現在のパスワードを入力してください。")
	private String nowpassword;
    @NotBlank(message = "新しいパスワードを入力してください。")
    @Length(min = 8, message = "新しいパスワードは8文字以上で入力してください。")
    private String password;    
    
    @NotBlank(message = "新しいパスワード（確認用）を入力してください。")
    private String passwordConfirmation; 
}

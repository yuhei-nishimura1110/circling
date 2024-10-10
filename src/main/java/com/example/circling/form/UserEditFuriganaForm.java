package com.example.circling.form;

import jakarta.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserEditFuriganaForm {
    @NotBlank(message = "フリガナを入力してください。")
    private String furigana;
}

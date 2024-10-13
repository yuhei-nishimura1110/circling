package com.example.circling.game.form;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ItemForm {
	private Integer user_id;
	private Integer player_id;
	private Integer item_kind;
	private Integer item_strong;

}

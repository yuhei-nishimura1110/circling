package com.example.circling.game.enumset;

public enum Item_Strong {
	type_1918(0),
	type_1936(1),
	type_1938(2),
	type_1940(3),
	type_1942(4),
	type_1945(5);
	
	
	private final int id;
	
	private Item_Strong(int id) {
		this.id = id;
	}
	public int getId() {
		return id;
	}
	
	
}

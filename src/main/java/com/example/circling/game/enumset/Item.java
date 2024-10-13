package com.example.circling.game.enumset;

public enum Item {
	SWORD(1, "剣"), SPEAR(2, "槍"), ARCH(3, "弓"), KATANA(4, "刀"), AX(5, "斧"), MELLET(6, "槌"), CLUB(7, "棍棒"), CANE(8,
			"杖"), SHIELD(9, "盾"), ARMOR(10, "防具");

	private final Integer id;
	private final String name;

	private Item(Integer id, String name) {
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public static String getItemname(final Integer id) {
		Item[] items = Item.values();
		for (Item item : items) {
			if (item.getId() == id) {
				return item.getName();
			}
		}
		return null;
	}
}

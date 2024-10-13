package com.example.circling.game.enumset;

public enum Fluctuation_Status {
	HP(1, "HP"), MP(2, "MP"), FP(3, "FP"), CP(4, "CP");

	private final int id;
	private final String name;

	private Fluctuation_Status(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public static String getType(final int id) {
		Fluctuation_Status[] fstatus = Fluctuation_Status.values();
		for (Fluctuation_Status fstatu : fstatus) {
			if (fstatu.getId() == id) {
				return fstatu.getName();
			}
		}

		return null;
	}
}

package com.example.circling.enumset;

public enum Job {

	SWORDER(1, "剣使い"), SPEARER(2, "槍使い"), ARCHER(3, "弓使い"), KATANAER(4, "刀使い"), AXER(5, "斧使い"), MELLETER(6,
			"槌使い"), CLUBER(7, "棍棒使い"), BLACK_MAGICIAN(8, "黒魔術師"), SHIELDER(9, "盾使い"), MAGICIAN(10,
					"魔法盾使い"), WHITE_MAGICIAN(11, "白魔術師"), COMMANDER(12, "参謀"), BLACKSMITH(13, "鍛冶師"), ARROW_MASTER(14,
							"矢師"), KATANA_BLACKSMITH(15,
									"刀鍛冶師"), ARMOR_BLACKSMITH(16, "防具鍛冶師"), CANE_MAKER(17, "杖職人"), TEACHER(18, "教授");

	private final int id;
	private final String name;

	private Job(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public static String getJobname(final int id) {
		for (Job job : Job.values()) {
			if (job.getId() == (id)) {
				return job.getName();
			}
		}
		return null;
	}
}

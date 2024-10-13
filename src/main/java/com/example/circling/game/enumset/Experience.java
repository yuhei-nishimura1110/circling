package com.example.circling.game.enumset;

public enum Experience {
	FRESH(1,"新人"),
	TRAINED(2,"未熟"),
	REGULAR(3,"一般"),
	SEASONED(4,"練達"),
	VETERANS(5,"歴戦");
	
	private final int id;
	private final String name;
	
	private Experience(int id,String name) {
		this.id = id;
		this.name=name;
	}
	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	
	public static String getExperience(final int id) {
		Experience[] Experiences = Experience.values();
		for(Experience experience :Experiences) {
			if(experience.getId()==id) {
				return experience.getName();
			}
		}
		return null;
	}
}

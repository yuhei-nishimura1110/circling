package com.example.circling.game.enumset;


public enum Invaroamt_Status {
	//定数
	MUSCLE(1,"筋力"),
	QUICK(2,"俊敏"),
	TECHNIQUE(3,"技術"),
	MAGIC(4,"魔術"),
	SPIRIT(5,"精神"),
	KNOWLEDGE(6,"学力"),
	STAMINA(7,"体力"),
	EATING(8,"食力");
	
	private final int id;
	private final String name;
	private Invaroamt_Status(int id,String name){
		this.id=id;
		this.name=name;
	}
	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	
	public static String getType(final int id) {
		Invaroamt_Status[] istatus=Invaroamt_Status.values();
		for (Invaroamt_Status istatu : istatus) {
			if(istatu.getId() == id) {
				return istatu.getName();
			}
		}
		
		return null;
	}
	
}

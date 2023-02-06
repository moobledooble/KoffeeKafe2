package edu.tridenttech.cpt237.cafe.model;

public class MenuItem {
	private String name;
	private double baseCost;
	private String type;

	public MenuItem(String type, String name, double cost) {
		this.name = name;
		this.baseCost = cost;
		this.type = type;
	}
	
	public String getName() {
		return name;
	}

	public double getBaseCost() {
		return baseCost;
	}

	public String getType() {
		return type;
	};
}
package edu.mst.tours.model;

public class Department {
	
	private String name;
	private Building building;
	
	public Department(String name, Building building) {
		this.name = name;
		this.building = building;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setBuilding(Building building) {
		this.building = building;
	}
	
	public Building getBuilding() {
		return building;
	}
	
}

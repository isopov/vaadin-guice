package com.sopovs.moradanen.vaadin.domain;

import java.util.List;

public class Company {
	private String id;
	private String name;

	// TODO make this ManyToMany JPA relationship
	private List<Sector> focusedSectors;
	// TODO make this ManyToMany JPA relationship
	private List<Person> workers;

	public Company() {

	}

	public Company(String id, String name) {
		this.id = id;
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Sector> getFocusedSectors() {
		return focusedSectors;
	}

	public void setFocusedSectors(List<Sector> focusedSectors) {
		this.focusedSectors = focusedSectors;
	}

	public List<Person> getWorkers() {
		return workers;
	}

	public void setWorkers(List<Person> workers) {
		this.workers = workers;
	}

	public String getVersion() {
		// TODO
		return "0";
	}

}

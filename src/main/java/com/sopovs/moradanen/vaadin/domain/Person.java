package com.sopovs.moradanen.vaadin.domain;

import java.util.List;

public class Person {

	private String id;
	private String firstName;
	private String secondName;
	private String description;
	// TODO make this ManyToMany JPA relationship
	private List<Company> companies;

	public Person() {

	}

	public Person(String id, String firstName, String secondName,
			String description) {
		this.id = id;
		this.firstName = firstName;
		this.secondName = secondName;
		this.description = description;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getSecondName() {
		return secondName;
	}

	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Company> getCompanies() {
		return companies;
	}

	public void setCompanies(List<Company> companies) {
		this.companies = companies;
	}

	public String getVersion() {
		// TODO
		return "0";
	}

}

package com.sopovs.moradanen.vaadin.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import javax.inject.Singleton;

import com.sopovs.moradanen.vaadin.domain.Company;
import com.sopovs.moradanen.vaadin.domain.Person;
import com.sopovs.moradanen.vaadin.domain.Sector;

@Singleton
public class DummyDao {
	private final Random r = new Random(228L);
	private final List<Person> persons = createPersons(r);
	private final List<Company> companies = createCompanies(r);
	private final List<Sector> sectors = createSectors(r);

	// Company Dao methods
	public long countCompanies() {
		return companies.size();
	}

	public List<Company> findAllCompanies() {
		return new ArrayList<Company>(companies);
	}

	public Company findCompany(String id) {
		if (id == null) {
			return null;
		}
		return companies.get(Integer.valueOf(id));
	}

	public List<Company> findCompaniesBySector(String sectorId) {
		return new ArrayList<Company>(sectors.get(Integer.valueOf(sectorId))
				.getFocusedCompanies());
	}

	// Person dao methods
	public long countPersons() {
		return persons.size();
	}

	public List<Person> findAllPersons() {
		return new ArrayList<Person>(persons);
	}

	public Person findPerson(String id) {
		if (id == null) {
			return null;
		}
		return persons.get(Integer.valueOf(id));
	}

	public List<Person> findPersonsByCompany(String companyId) {
		return new ArrayList<Person>(companies.get(Integer.valueOf(companyId))
				.getWorkers());
	}

	// Sector dao methods
	public long countSectors() {
		return sectors.size();
	}

	public List<Sector> findAllSectors() {
		return new ArrayList<Sector>(sectors);
	}

	public Sector findSector(String id) {
		if (id == null) {
			return null;
		}
		return sectors.get(Integer.valueOf(id));
	}

	public List<Sector> findSectorsByParent(String parentId) {
		if (parentId == null) {
			return new ArrayList<Sector>(Collections.singletonList(sectors
					.get(0)));
		} else {
			return new ArrayList<Sector>(sectors.get(Integer.valueOf(parentId))
					.getSubSectors());
		}
		// if (parentId == null) {
		// return Collections.singletonList(new Sector("0", null, "Sector 0"));
		// } else if (Integer.valueOf(parentId) < 30) {
		// return Collections.singletonList(new
		// Sector(String.valueOf((Integer.valueOf(parentId) + 1)), parentId,
		// "SubSector"));
		// } else {
		// return Collections.emptyList();
		// }
	}

	private static List<Person> createPersons(Random r) {
		List<Person> result = new ArrayList<Person>();
		String[] firstNames = new String[] { "Ivan", "Peter", "Sidor", "Vasil",
				"Urij", "Alex" };
		String[] secondNames = new String[] { "Ivanov", "Petrov", "Sidorov",
				"Vasiljev", "Urijev", "Alexandrov", "Shulc" };
		String[] descriptions = new String[] { "Student", "Professor",
				"Worker", "Driver", "Gardener" };
		for (int i = 0; i < 100; i++) {
			Person person = new Person(String.valueOf(i),
					firstNames[r.nextInt(firstNames.length)],
					secondNames[r.nextInt(secondNames.length)],
					descriptions[r.nextInt(descriptions.length)]);
			person.setCompanies(new ArrayList<Company>());
			result.add(person);
		}
		return result;
	}

	private List<Company> createCompanies(Random r) {
		List<Company> result = new ArrayList<Company>();
		for (int i = 0; i < 50; i++) {
			Company com = new Company(String.valueOf(i), "Company " + (i + 1));
			com.setFocusedSectors(new ArrayList<Sector>());
			Set<Person> uniqueWorkers = new HashSet<Person>();
			for (int j = 0; j < r.nextInt(20); j++) {
				Person worker = persons.get(r.nextInt(persons.size()));
				worker.getCompanies().add(com);
				uniqueWorkers.add(worker);
			}
			com.setWorkers(new ArrayList<Person>(uniqueWorkers));
			result.add(com);
		}
		return result;
	}

	private List<Sector> createSectors(Random r) {
		List<Sector> result = new ArrayList<Sector>();
		Sector root = new Sector(String.valueOf(0), null, "Root");
		result.add(root);
		for (int i = 1; i < 60; i++) {
			Sector sec = new Sector(String.valueOf(i), result.get(
					r.nextInt(result.size())).getId(), "Sector " + (i + 1));
			result.get(Integer.valueOf(sec.getParentId())).getSubSectors()
					.add(sec);
			Set<Company> uniqueCompaniess = new HashSet<Company>();
			for (int j = 0; j < r.nextInt(20); j++) {
				Company com = companies.get(r.nextInt(companies.size()));
				com.getFocusedSectors().add(sec);
				uniqueCompaniess.add(com);
			}
			sec.setFocusedCompanies(new ArrayList<Company>(uniqueCompaniess));
			result.add(sec);
		}
		return result;
	}

}

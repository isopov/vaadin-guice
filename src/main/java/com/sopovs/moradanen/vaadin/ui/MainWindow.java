/*
 * Copyright 2011 Navin Peiris <navin.peiris@gmail.com>, Melbourne, Australia.
 * All Rights Reserved.
 */

package com.sopovs.moradanen.vaadin.ui;

import java.util.List;

import javax.inject.Inject;

import com.google.inject.servlet.SessionScoped;
import com.sopovs.moradanen.vaadin.dao.DummyDao;
import com.sopovs.moradanen.vaadin.domain.Company;
import com.sopovs.moradanen.vaadin.domain.Person;
import com.vaadin.data.Item;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.ItemClickEvent.ItemClickListener;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalSplitPanel;
import com.vaadin.ui.Window;

@SessionScoped
public class MainWindow extends Window {
	private static final long serialVersionUID = 1L;

	@Inject
	private DummyDao dao;

	@Inject
	private SectorTree sectorTree;

	private Table companyTable;

	private Table personTable;

	public MainWindow() {
		super("Vaadin Guice application");
		setSizeFull();
	}

	public void init() {
		assert dao != null;
		HorizontalSplitPanel mainSplit = new HorizontalSplitPanel();
		mainSplit.setSizeFull();

		sectorTree.init();
		mainSplit.setFirstComponent(sectorTree);
		sectorTree.addListener(new ItemClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void itemClick(ItemClickEvent event) {
				companyTable.removeAllItems();
				List<Company> companies = dao.findCompaniesBySector((String) event.getItemId()); //WTF is this cast?
				for (Company company : companies) {
					Item companyItem = companyTable.addItem(company.getId());
					companyItem.getItemProperty("id").setValue(company.getId());
					companyItem.getItemProperty("name").setValue(company.getName());

				}
			}
		});
		VerticalSplitPanel rightSplit = new VerticalSplitPanel();
		mainSplit.setSecondComponent(rightSplit);
		rightSplit.setSizeFull();
		companyTable = createCompanyTable();
		rightSplit.setFirstComponent(companyTable);
		personTable = createPersonTable();
		rightSplit.setSecondComponent(personTable);
		setContent(mainSplit);
	}

	private static Table createPersonTable() {
		Table table = new Table();
		table.setSizeFull();
		table.addContainerProperty("id", String.class, null);
		table.addContainerProperty("firstName", String.class, null);
		table.addContainerProperty("secondName", String.class, null);
		table.addContainerProperty("description", String.class, null);
		return table;
	}

	private Table createCompanyTable() {
		final Table table = new Table();
		table.setSizeFull();
		table.addContainerProperty("id", String.class, null);
		table.addContainerProperty("name", String.class, null);

		table.setSelectable(true);
		table.setImmediate(true);
		table.addListener(new ValueChangeListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void valueChange(ValueChangeEvent event) {
				personTable.removeAllItems();
				String companyId = (String) companyTable.getValue();
				if (companyId == null) {
					return;
				}
				List<Person> persons = dao.findPersonsByCompany(companyId);
				for (Person person : persons) {
					Item addItem = personTable.addItem(person.getId());
					addItem.getItemProperty("id").setValue(person.getId());
					addItem.getItemProperty("firstName").setValue(person.getFirstName());
					addItem.getItemProperty("secondName").setValue(person.getSecondName());
					addItem.getItemProperty("description").setValue(person.getDescription());
				}
			}
		});
		return table;
	}

}

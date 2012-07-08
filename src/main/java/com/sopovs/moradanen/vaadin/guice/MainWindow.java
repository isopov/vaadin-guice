/*
 * Copyright 2011 Navin Peiris <navin.peiris@gmail.com>, Melbourne, Australia.
 * All Rights Reserved.
 */

package com.sopovs.moradanen.vaadin.guice;

import javax.inject.Inject;

import com.google.inject.servlet.SessionScoped;
import com.sopovs.moradanen.vaadin.dao.DummyDao;
import com.vaadin.data.Item;
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

	public MainWindow() {
		super("Vaadin Guice application");
	}

	public void init() {
		assert dao != null;
		HorizontalSplitPanel mainSplit = new HorizontalSplitPanel();
		// TODO
		// mainSplit.setFirstComponent(sectorTree);
		mainSplit.setFirstComponent(createDummyTable());
		VerticalSplitPanel rightSplit = new VerticalSplitPanel();
		rightSplit.setFirstComponent(createDummyTable());
		rightSplit.setSecondComponent(createDummyTable());
		mainSplit.setSecondComponent(rightSplit);
		addComponent(mainSplit);
	}

	private static Table createDummyTable() {
		Table table = new Table();
		table.addContainerProperty("Thing", String.class, null);
		for (int i = 0; i < 50; i++) {
			Item item = table.addItem("Number " + i);
			item.getItemProperty("Thing").setValue("Number " + i);
		}

		return table;
	}
}

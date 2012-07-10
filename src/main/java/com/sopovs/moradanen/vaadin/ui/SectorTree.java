package com.sopovs.moradanen.vaadin.ui;

import java.util.List;

import javax.inject.Inject;

import com.google.inject.servlet.SessionScoped;
import com.sopovs.moradanen.vaadin.dao.DummyDao;
import com.sopovs.moradanen.vaadin.domain.Sector;
import com.vaadin.data.Item;
import com.vaadin.data.util.HierarchicalContainer;
import com.vaadin.ui.Tree;

@SessionScoped
public class SectorTree extends Tree {
	private static final long serialVersionUID = 1L;

	@Inject
	private DummyDao dao;

	public void init() {
		List<Sector> sectors = dao.findAllSectors();
		HierarchicalContainer container = new HierarchicalContainer();
		container.addContainerProperty("name", String.class, null);
		for (Sector sector : sectors) {
			Item sectorItem = container.addItem(sector.getId());
			sectorItem.getItemProperty("name").setValue(sector.getName());
			if (sector.getParentId() != null) {
				container.setParent(sector.getId(), sector.getParentId());
			}
		}

		setContainerDataSource(container);
		setItemCaptionPropertyId("name");
	}

}

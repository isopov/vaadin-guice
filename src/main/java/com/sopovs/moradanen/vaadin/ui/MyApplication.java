package com.sopovs.moradanen.vaadin.ui;

import com.google.inject.Inject;
import com.google.inject.servlet.SessionScoped;
import com.vaadin.Application;

@SessionScoped
public class MyApplication extends Application {
	private static final long serialVersionUID = 1L;
	@Inject
	private MainWindow mainWindow;

	@Override
	public void init() {
		mainWindow.init();
		setMainWindow(mainWindow);
	}

}
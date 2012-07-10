package com.sopovs.moradanen.vaadin.guice;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;
import com.google.inject.servlet.ServletModule;
import com.sopovs.moradanen.vaadin.ui.MyApplication;
import com.vaadin.Application;

public class MyServletConfig extends GuiceServletContextListener {

	@Override
	protected Injector getInjector() {

		ServletModule module = new ServletModule() {
			@Override
			protected void configureServlets() {
				serve("/*").with(GuiceApplicationServlet.class);

				bind(Application.class).to(MyApplication.class);

			}
		};

		Injector injector = Guice.createInjector(module);

		return injector;
	}
}
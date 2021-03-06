package com.intexsoft.library;

import org.apache.wicket.Page;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class LibraryApplication extends WebApplication {
    @Override
    public Class<? extends Page> getHomePage() {
        return MainPage.class;
    }

    @Override
    protected void init() {
        super.init();
        getComponentInstantiationListeners().add(
                new SpringComponentInjector(this, new ClassPathXmlApplicationContext("applicationContext.xml"), false));
    }
}

package com.intexsoft;

import org.apache.wicket.protocol.http.WebApplication;


public class TaskLibraryApplication extends WebApplication {
    @Override
    public Class<HomePage> getHomePage() {
        return HomePage.class;
    }
}

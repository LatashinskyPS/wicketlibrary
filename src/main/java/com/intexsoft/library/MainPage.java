package com.intexsoft.library;

import com.intexsoft.library.database.repositories.BookRepository;
import com.intexsoft.library.wicket.components.panels.NavbarPanel;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.spring.injection.annot.SpringBean;

public class MainPage extends WebPage {

    public MainPage() {
        add(new NavbarPanel("navbar"));
    }
}

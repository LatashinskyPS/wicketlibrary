package com.intexsoft.library;

import com.intexsoft.library.database.repositories.BookRepository;
import com.intexsoft.library.wicket.components.panels.general.FooterPanel;
import com.intexsoft.library.wicket.components.panels.general.NavbarPanel;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.spring.injection.annot.SpringBean;

public class MainPage extends WebPage {

    @SpringBean
    private BookRepository bookRepository;

    public MainPage() {
        bookRepository.getAll();
        add(new NavbarPanel("navbar"));
        add(new FooterPanel("footer"));
    }


}

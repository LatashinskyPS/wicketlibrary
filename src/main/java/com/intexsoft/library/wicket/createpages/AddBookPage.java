package com.intexsoft.library.wicket.createpages;

import com.intexsoft.library.database.entities.Book;
import com.intexsoft.library.database.services.BookService;
import com.intexsoft.library.wicket.BooksPage;
import com.intexsoft.library.wicket.components.BookForm;
import com.intexsoft.library.wicket.components.panels.general.FooterPanel;
import com.intexsoft.library.wicket.components.panels.general.NavbarPanel;
import com.intexsoft.library.wicket.components.panels.model.BookPanel;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.spring.injection.annot.SpringBean;

public class AddBookPage extends WebPage {
    @SpringBean
    private BookService bookService;

    public AddBookPage() {
        add(new NavbarPanel("navbar"));
        add(new FooterPanel("footer"));
        BookForm bookForm = new BookForm("form", new Book(), "Отмена") {
            @Override
            protected void actionOnLinkReturn() {
                redirectToInterceptPage(new BooksPage());
            }

            @Override
            protected void onSubmit() {
                bookService.save(getModelObject());
                redirectToInterceptPage(new BooksPage());
            }
        };
        add(new BookPanel("panel", bookForm));
    }

}

package com.intexsoft.library.wicket.infopages;

import com.intexsoft.library.wicket.BooksPage;
import com.intexsoft.library.database.entities.Book;
import com.intexsoft.library.wicket.components.BookForm;
import com.intexsoft.library.database.repositories.BookRepository;
import com.intexsoft.library.wicket.components.panels.general.FooterPanel;
import com.intexsoft.library.wicket.components.panels.general.NavbarPanel;
import com.intexsoft.library.wicket.components.panels.model.BookPanel;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.PropertyModel;

public class BookInfoPage extends WebPage {

    public BookInfoPage(Book book) {
        add(new NavbarPanel("navbar"));
        add(new FooterPanel("footer"));
        book = BookRepository.getInstance().findById(book.getId());
        if (book == null) {
            redirectToInterceptPage(new BooksPage());
        }
        add(new Link<>("return") {
            @Override
            public void onClick() {
                redirectToInterceptPage(new BooksPage());
            }
        });
        BookForm bookForm = new BookForm("form", book, "Отменить изменения") {
            @Override
            protected void actionOnLinkReturn() {
                redirectToInterceptPage(new BookInfoPage(getModelObject()));
            }

            @Override
            protected void onSubmit() {
                BookRepository.getInstance().save(getModelObject());
            }
        };
        add(new Label("name", new PropertyModel<>(book, "name")));
        add(new Label("description", new PropertyModel<>(book, "description")));
        add(new Label("authors", new PropertyModel<>(book, "authors")));
        add(new Label("publisher", new PropertyModel<>(book, "publisher")));
        add(new BookPanel("panel", bookForm));
    }
}

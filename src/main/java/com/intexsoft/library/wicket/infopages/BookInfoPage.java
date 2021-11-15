package com.intexsoft.library.wicket.infopages;

import com.intexsoft.library.wicket.BooksPage;
import com.intexsoft.library.database.entities.Book;
import com.intexsoft.library.wicket.components.BookForm;
import com.intexsoft.library.database.repositories.BookRepository;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.PropertyModel;

public class BookInfoPage extends WebPage {

    public BookInfoPage(Book book) {
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
        Form<Book> bookForm = new BookForm("form", book) {
            @Override
            protected void onSubmit() {
                BookRepository.getInstance().save(getModelObject());
            }
        };
        add(new Label("name", new PropertyModel<>(book, "name")));
        add(new Label("description", new PropertyModel<>(book, "description")));
        add(new Label("authors", new PropertyModel<>(book,"authors")));
        add(new Label("publisher", new PropertyModel<>(book,"publisher")));
        add(bookForm);
    }
}

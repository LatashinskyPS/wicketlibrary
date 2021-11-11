package com.intexsoft.wicketlibrary.bookinfo;

import com.intexsoft.wicketlibrary.HomePage;
import com.intexsoft.wicketlibrary.entities.Book;
import com.intexsoft.wicketlibrary.models.BookForm;
import com.intexsoft.wicketlibrary.repositories.BookRepository;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.PropertyModel;

public class BookInfoPage extends WebPage {

    public BookInfoPage(Book book) {
        book = BookRepository.getInstance().findById(book.getId());
        if (book == null) {
            redirectToInterceptPage(new HomePage());
        }
        add(new Link<>("return") {
            @Override
            public void onClick() {
                redirectToInterceptPage(new HomePage());
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

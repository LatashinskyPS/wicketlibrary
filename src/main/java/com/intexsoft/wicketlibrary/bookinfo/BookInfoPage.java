package com.intexsoft.wicketlibrary.bookinfo;

import com.intexsoft.wicketlibrary.HomePage;
import com.intexsoft.wicketlibrary.entities.Book;
import com.intexsoft.wicketlibrary.models.BookForm;
import com.intexsoft.wicketlibrary.repositories.BookRepository;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.PropertyModel;

public class BookInfoPage extends WebPage {

    public BookInfoPage(Book book) {
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
        add(new Label("name", new PropertyModel<>(book,"name")));
        add(new Label("description", new PropertyModel<>(book,"description")));
        add(bookForm);
    }
}

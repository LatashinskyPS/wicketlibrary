package com.intexsoft.library.wicket.createpages;

import com.intexsoft.library.wicket.BooksPage;
import com.intexsoft.library.database.entities.Book;
import com.intexsoft.library.wicket.components.BookForm;
import com.intexsoft.library.database.repositories.BookRepository;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.*;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.FeedbackPanel;

public class AddBookPage extends WebPage {

    public AddBookPage() {
        Form<Book> bookForm = new BookForm("form", new Book()){
            @Override
            protected void onSubmit() {
                BookRepository.getInstance().save(getModelObject());
                redirectToInterceptPage(new BooksPage());
            }
        };
        bookForm.add(new Link<>("return") {
            @Override
            public void onClick() {
                redirectToInterceptPage(new BooksPage());
            }
        });
        add(bookForm);
        FeedbackPanel feedbackPanel = new FeedbackPanel("feedback");
        add(feedbackPanel);
    }
}

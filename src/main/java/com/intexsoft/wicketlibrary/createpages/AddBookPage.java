package com.intexsoft.wicketlibrary.createpages;

import com.intexsoft.wicketlibrary.HomePage;
import com.intexsoft.wicketlibrary.entities.Book;
import com.intexsoft.wicketlibrary.models.BookForm;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.*;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.FeedbackPanel;

public class AddBookPage extends WebPage {

    public AddBookPage() {
        Form<Book> bookForm = new BookForm("form", new Book());
        bookForm.add(new Link<>("return") {
            @Override
            public void onClick() {
                redirectToInterceptPage(new HomePage());
            }
        });
        add(bookForm);
        FeedbackPanel feedbackPanel = new FeedbackPanel("feedback");
        add(feedbackPanel);
    }
}

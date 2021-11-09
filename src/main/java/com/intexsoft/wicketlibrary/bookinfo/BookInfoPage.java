package com.intexsoft.wicketlibrary.bookinfo;

import com.intexsoft.wicketlibrary.HomePage;
import com.intexsoft.wicketlibrary.entities.Book;
import com.intexsoft.wicketlibrary.models.BookForm;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.link.Link;

public class BookInfoPage extends WebPage {

    public BookInfoPage(Book book) {
        add(new Link<>("return") {
            @Override
            public void onClick() {
                redirectToInterceptPage(new HomePage());
            }
        });
        Form<Book> bookForm = new BookForm("form", book);
        add(bookForm);
    }
}

package com.intexsoft.library.wicket.infopages;

import com.intexsoft.library.database.entities.Author;
import com.intexsoft.library.database.entities.Book;
import com.intexsoft.library.database.repositories.AuthorRepository;
import com.intexsoft.library.database.repositories.BookRepository;
import com.intexsoft.library.wicket.AuthorsPage;
import com.intexsoft.library.wicket.BooksPage;
import com.intexsoft.library.wicket.components.AuthorForm;
import com.intexsoft.library.wicket.components.BookForm;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;

public class AuthorInfoPage extends WebPage {

    public AuthorInfoPage(Author author) {
        author = AuthorRepository.getInstance().findById(author.getId());
        if (author == null) {
            redirectToInterceptPage(new BooksPage());
        }
        add(new Link<>("return") {
            @Override
            public void onClick() {
                redirectToInterceptPage(new AuthorsPage());
            }
        });
        Form<Author> bookForm = new AuthorForm("form", author) {
            @Override
            protected void onSubmit() {
                AuthorRepository.getInstance().save(getModelObject());
            }
        };
        add(new Label("info", new Model<>(author)));
        add(bookForm);
    }
}

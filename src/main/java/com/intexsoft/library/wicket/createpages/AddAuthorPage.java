package com.intexsoft.library.wicket.createpages;

import com.intexsoft.library.database.repositories.AuthorRepository;
import com.intexsoft.library.wicket.AuthorsPage;
import com.intexsoft.library.wicket.BooksPage;
import com.intexsoft.library.wicket.components.AuthorForm;
import com.intexsoft.library.database.entities.Author;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.FeedbackPanel;

public class AddAuthorPage extends WebPage {
    private static final long serialVersionUID = 1L;

    public AddAuthorPage() {
        FeedbackPanel feedbackPanel = new FeedbackPanel("feedback");
        add(feedbackPanel);
        Form<Author> form = new AuthorForm("form", new Author()) {
            @Override
            protected void onSubmit() {
                AuthorRepository.getInstance().save((Author) getDefaultModelObject());
                redirectToInterceptPage(new BooksPage());
            }
        };
        form.add(new Link<>("return") {
            @Override
            public void onClick() {
                redirectToInterceptPage(new AuthorsPage());
            }
        });
        add(form);
    }
}

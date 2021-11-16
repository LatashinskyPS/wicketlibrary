package com.intexsoft.library.wicket.infopages;

import com.intexsoft.library.database.entities.Author;
import com.intexsoft.library.database.repositories.AuthorRepository;
import com.intexsoft.library.wicket.AuthorsPage;
import com.intexsoft.library.wicket.BooksPage;
import com.intexsoft.library.wicket.components.AuthorForm;
import com.intexsoft.library.wicket.components.panels.general.FooterPanel;
import com.intexsoft.library.wicket.components.panels.general.NavbarPanel;
import com.intexsoft.library.wicket.components.panels.model.AuthorPanel;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.Model;

public class AuthorInfoPage extends WebPage {

    public AuthorInfoPage(Author author) {
        add(new NavbarPanel("navbar"));
        add(new FooterPanel("footer"));
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
        AuthorForm authorForm = new AuthorForm("form", author, "Отменить изменения") {
            @Override
            protected void actionOnLinkReturn() {
                redirectToInterceptPage(new AuthorInfoPage(getModelObject()));
            }

            @Override
            protected void onSubmit() {
                AuthorRepository.getInstance().save(getModelObject());
            }
        };
        add(new Label("info", new Model<>(author)));
        add(new AuthorPanel("panel", authorForm));
    }
}

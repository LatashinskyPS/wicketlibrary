package com.intexsoft.library.wicket.createpages;

import com.intexsoft.library.database.entities.Author;
import com.intexsoft.library.database.repositories.AuthorRepository;
import com.intexsoft.library.wicket.AuthorsPage;
import com.intexsoft.library.wicket.components.AuthorForm;
import com.intexsoft.library.wicket.components.panels.general.FooterPanel;
import com.intexsoft.library.wicket.components.panels.general.NavbarPanel;
import com.intexsoft.library.wicket.components.panels.model.AuthorPanel;
import org.apache.wicket.markup.html.WebPage;

public class AddAuthorPage extends WebPage {
    private static final long serialVersionUID = 1L;

    public AddAuthorPage() {
        add(new NavbarPanel("navbar"));
        add(new FooterPanel("footer"));
        AuthorForm form = new AuthorForm("form", new Author(), "Отмена") {
            @Override
            protected void actionOnLinkReturn() {
                redirectToInterceptPage(new AuthorsPage());
            }

            @Override
            protected void onSubmit() {
                AuthorRepository.getInstance().save((Author) getDefaultModelObject());
                redirectToInterceptPage(new AuthorsPage());
            }
        };
        add(new AuthorPanel("panel", form));
    }

    protected void actionOnLinkReturn() {
        redirectToInterceptPage(new AuthorsPage());
    }
}

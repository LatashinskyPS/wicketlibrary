package com.intexsoft.library.wicket.createpages;

import com.intexsoft.library.wicket.BooksPage;
import com.intexsoft.library.database.entities.Publisher;
import com.intexsoft.library.database.repositories.PublisherRepository;
import com.intexsoft.library.wicket.PublishersPage;
import com.intexsoft.library.wicket.components.PublisherForm;
import com.intexsoft.library.wicket.components.panels.general.FooterPanel;
import com.intexsoft.library.wicket.components.panels.general.NavbarPanel;
import com.intexsoft.library.wicket.components.panels.model.PublisherPanel;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.FeedbackPanel;

public class AddPublisherPage extends WebPage {
    private static final long serialVersionUID = 1L;

    public AddPublisherPage() {
        add(new NavbarPanel("navbar"));
        add(new FooterPanel("footer"));
        PublisherForm publisherForm = new PublisherForm("form", new Publisher(), "Отмена") {
            @Override
            protected void actionOnLinkReturn() {
                redirectToInterceptPage(new PublishersPage());
            }

            @Override
            protected void onSubmit() {
                PublisherRepository.getInstance().save((Publisher) getDefaultModelObject());
                redirectToInterceptPage(new PublishersPage());
            }
        };

        add(new PublisherPanel("panel", publisherForm));
    }
}

package com.intexsoft.library.wicket.createpages;

import com.intexsoft.library.database.entities.Publisher;
import com.intexsoft.library.database.repositories.PublisherRepository;
import com.intexsoft.library.database.services.PublisherService;
import com.intexsoft.library.wicket.PublishersPage;
import com.intexsoft.library.wicket.components.PublisherForm;
import com.intexsoft.library.wicket.components.panels.general.FooterPanel;
import com.intexsoft.library.wicket.components.panels.general.NavbarPanel;
import com.intexsoft.library.wicket.components.panels.model.PublisherPanel;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.spring.injection.annot.SpringBean;

public class AddPublisherPage extends WebPage {
    private static final long serialVersionUID = 1L;

    @SpringBean
    private PublisherService publisherService;

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
                publisherService.save((Publisher) getDefaultModelObject());
                redirectToInterceptPage(new PublishersPage());
            }
        };

        add(new PublisherPanel("panel", publisherForm));
    }
}

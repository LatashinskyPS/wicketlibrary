package com.intexsoft.library.wicket.infopages;

import com.intexsoft.library.database.entities.Publisher;
import com.intexsoft.library.database.repositories.PublisherRepository;
import com.intexsoft.library.database.services.AuthorService;
import com.intexsoft.library.database.services.PublisherService;
import com.intexsoft.library.wicket.BooksPage;
import com.intexsoft.library.wicket.PublishersPage;
import com.intexsoft.library.wicket.components.PublisherForm;
import com.intexsoft.library.wicket.components.panels.general.FooterPanel;
import com.intexsoft.library.wicket.components.panels.general.NavbarPanel;
import com.intexsoft.library.wicket.components.panels.model.PublisherPanel;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;

public class PublisherInfoPage extends WebPage {
    @SpringBean
    private PublisherService publisherService;

    public PublisherInfoPage(Publisher publisher) {
        add(new NavbarPanel("navbar"));
        add(new FooterPanel("footer"));
        publisher = publisherService.findById(publisher.getId());
        if (publisher == null) {
            redirectToInterceptPage(new BooksPage());
        }
        add(new Link<>("return") {
            @Override
            public void onClick() {
                redirectToInterceptPage(new PublishersPage());
            }
        });
        PublisherForm publisherForm = new PublisherForm("form", publisher, "Отменить изменения") {
            @Override
            protected void actionOnLinkReturn() {
                redirectToInterceptPage(new PublisherInfoPage(getModelObject()));
            }

            @Override
            protected void onSubmit() {
                publisherService.save(getModelObject());
            }
        };
        add(new Label("info", new Model<>(publisher)));
        add(new PublisherPanel("panel", publisherForm));
    }
}

package com.intexsoft.library.wicket.createpages;

import com.intexsoft.library.wicket.BooksPage;
import com.intexsoft.library.database.entities.Publisher;
import com.intexsoft.library.database.repositories.PublisherRepository;
import com.intexsoft.library.wicket.PublishersPage;
import com.intexsoft.library.wicket.components.PublisherForm;
import com.intexsoft.library.wicket.validators.CustomNumberValidator;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;

public class AddPublisherPage extends WebPage {
    private static final long serialVersionUID = 1L;

    public AddPublisherPage() {
        Form<Publisher> publisherForm = new PublisherForm("form", new Publisher()) {
            @Override
            protected void onSubmit() {
                PublisherRepository.getInstance().save((Publisher) getDefaultModelObject());
                redirectToInterceptPage(new BooksPage());
            }
        };

        FeedbackPanel feedbackPanel = new FeedbackPanel("feedback");
        publisherForm.add(new Link<>("return") {
            @Override
            public void onClick() {
                redirectToInterceptPage(new PublishersPage());
            }
        });
        add(feedbackPanel);
        add(publisherForm);
    }
}

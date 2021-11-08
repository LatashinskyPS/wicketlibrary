package com.intexsoft.createpages;

import com.intexsoft.HomePage;
import com.intexsoft.entities.Publisher;
import com.intexsoft.repositories.PublisherRepository;
import com.intexsoft.validators.CustomNumberValidator;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;

public class AddPublisherPage extends WebPage {
    private static final long serialVersionUID = 1L;

    public AddPublisherPage() {
        CompoundPropertyModel<Publisher> compoundPropertyModel = new CompoundPropertyModel<>(new Publisher());
        Form<Publisher> publisherForm = new Form<>("form", compoundPropertyModel) {
            @Override
            protected void onSubmit() {
                PublisherRepository.getInstance().save((Publisher) getDefaultModelObject());
                redirectToInterceptPage(new HomePage());
            }
        };

        RequiredTextField<String> nameRequiredTextField = new RequiredTextField<>("name");
        RequiredTextField<String> addressRequiredTextField = new RequiredTextField<>("address");
        RequiredTextField<String> numberRequiredTextField = new RequiredTextField<>("number");
        FeedbackPanel feedbackPanel = new FeedbackPanel("feedback");

        numberRequiredTextField.add(new CustomNumberValidator());

        publisherForm.add(nameRequiredTextField);
        publisherForm.add(addressRequiredTextField);
        publisherForm.add(numberRequiredTextField);
        publisherForm.add(new Link<>("return") {
            @Override
            public void onClick() {
                redirectToInterceptPage(new HomePage());
            }
        });

        add(feedbackPanel);
        add(publisherForm);
    }
}

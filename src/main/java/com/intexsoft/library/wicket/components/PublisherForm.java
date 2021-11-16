package com.intexsoft.library.wicket.components;

import com.intexsoft.library.database.entities.Publisher;
import com.intexsoft.library.wicket.validators.CustomNumberValidator;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.Model;

public abstract class PublisherForm extends Form<Publisher> {
    public PublisherForm(String id, Publisher publisher, String nameOfButton) {
        super(id, new CompoundPropertyModel<>(publisher));

        RequiredTextField<String> nameRequiredTextField = new RequiredTextField<>("name");
        RequiredTextField<String> addressRequiredTextField = new RequiredTextField<>("address");
        RequiredTextField<String> numberRequiredTextField = new RequiredTextField<>("number");

        numberRequiredTextField.add(new CustomNumberValidator());

        add(nameRequiredTextField);
        add(addressRequiredTextField);
        add(numberRequiredTextField);
        add(new FeedbackPanel("feedback"));
        add(new Link<>("return") {
            @Override
            public void onClick() {
                actionOnLinkReturn();
            }
        }.add(new Label("nameOfButton", new Model<>(nameOfButton))));
    }

    protected abstract void actionOnLinkReturn();
}

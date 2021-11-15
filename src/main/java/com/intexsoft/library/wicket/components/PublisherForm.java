package com.intexsoft.library.wicket.components;

import com.intexsoft.library.database.entities.Publisher;
import com.intexsoft.library.database.repositories.PublisherRepository;
import com.intexsoft.library.wicket.BooksPage;
import com.intexsoft.library.wicket.validators.CustomNumberValidator;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;

public class PublisherForm extends Form<Publisher> {
    public PublisherForm(String id,Publisher publisher) {
        super(id, new CompoundPropertyModel<>(publisher));

        RequiredTextField<String> nameRequiredTextField = new RequiredTextField<>("name");
        RequiredTextField<String> addressRequiredTextField = new RequiredTextField<>("address");
        RequiredTextField<String> numberRequiredTextField = new RequiredTextField<>("number");

        numberRequiredTextField.add(new CustomNumberValidator());

        add(nameRequiredTextField);
        add(addressRequiredTextField);
        add(numberRequiredTextField);

    }
}

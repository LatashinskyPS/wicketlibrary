package com.intexsoft.createpages;

import com.intexsoft.HomePage;
import com.intexsoft.entities.Author;
import com.intexsoft.repositories.AuthorRepository;
import org.apache.wicket.event.IEvent;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.validation.validator.DateValidator;

import java.util.Date;

public class AddAuthorPage extends WebPage {
    private static final long serialVersionUID = 1L;

    public AddAuthorPage() {
        CompoundPropertyModel<Author> compoundPropertyModel = new CompoundPropertyModel<>(new Author());
        Form<Author> authorForm = new Form<>("form", compoundPropertyModel) {
            @Override
            protected void onSubmit() {
                AuthorRepository.getInstance().save((Author) getDefaultModelObject());
                redirectToInterceptPage(new HomePage());
            }
        };

        FeedbackPanel feedbackPanel = new FeedbackPanel("feedback");
        RequiredTextField<String> nameRequiredTextField = new RequiredTextField<>("name");
        RequiredTextField<String> surnameRequiredTextField = new RequiredTextField<>("surname");
        RequiredTextField<Date> dateBornRequiredTextField = new RequiredTextField<>("dateBorn");
        TextField<Date> dateDeathTextField = new TextField<>("dateDeath");

        dateBornRequiredTextField.add(DateValidator.maximum(new Date()));
        dateDeathTextField.add(DateValidator.maximum(new Date()));

        authorForm.add(nameRequiredTextField);
        authorForm.add(surnameRequiredTextField);
        authorForm.add(dateBornRequiredTextField);
        authorForm.add(dateDeathTextField);
        authorForm.add(new Link<>("return") {
            @Override
            public void onClick() {
                redirectToInterceptPage(new HomePage());
            }
        });

        add(feedbackPanel);
        add(authorForm);
    }
}

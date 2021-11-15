package com.intexsoft.library.wicket.components;

import com.intexsoft.library.database.entities.Author;
import com.intexsoft.library.database.repositories.AuthorRepository;
import com.intexsoft.library.wicket.BooksPage;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.validation.validator.DateValidator;

import java.util.Date;

public class AuthorForm extends Form<Author> {
    public AuthorForm(String id, Author author) {
        super(id, new CompoundPropertyModel<>(author));
        RequiredTextField<String> nameRequiredTextField = new RequiredTextField<>("name");
        RequiredTextField<String> surnameRequiredTextField = new RequiredTextField<>("surname");
        RequiredTextField<Date> dateBornRequiredTextField = new RequiredTextField<>("dateBorn");
        TextField<Date> dateDeathTextField = new TextField<>("dateDeath");

        dateBornRequiredTextField.add(DateValidator.maximum(new Date()));
        dateDeathTextField.add(DateValidator.maximum(new Date()));

        add(nameRequiredTextField);
        add(surnameRequiredTextField);
        add(dateBornRequiredTextField);
        add(dateDeathTextField);
    }
}

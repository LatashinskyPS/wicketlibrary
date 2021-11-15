package com.intexsoft.library.wicket.infopages;

import com.intexsoft.library.database.entities.Author;
import com.intexsoft.library.database.entities.Publisher;
import com.intexsoft.library.database.repositories.AuthorRepository;
import com.intexsoft.library.database.repositories.PublisherRepository;
import com.intexsoft.library.wicket.AuthorsPage;
import com.intexsoft.library.wicket.BooksPage;
import com.intexsoft.library.wicket.PublishersPage;
import com.intexsoft.library.wicket.components.AuthorForm;
import com.intexsoft.library.wicket.components.PublisherForm;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.Model;

public class PublisherInfoPage extends WebPage {
    public PublisherInfoPage(Publisher publisher) {
        publisher = PublisherRepository.getInstance().findById(publisher.getId());
        if (publisher == null) {
            redirectToInterceptPage(new BooksPage());
        }
        add(new Link<>("return") {
            @Override
            public void onClick() {
                redirectToInterceptPage(new PublishersPage());
            }
        });
        Form<Publisher> bookForm = new PublisherForm("form", publisher) {
            @Override
            protected void onSubmit() {
                PublisherRepository.getInstance().save(getModelObject());
            }
        };
        add(new Label("info", new Model<>(publisher)));
        add(bookForm);
    }
}

package com.intexsoft.createpages;

import com.intexsoft.HomePage;
import com.intexsoft.entities.Author;
import com.intexsoft.entities.Book;
import com.intexsoft.entities.Publisher;
import com.intexsoft.repositories.AuthorRepository;
import com.intexsoft.repositories.BookRepository;
import com.intexsoft.repositories.PublisherRepository;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.*;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.Model;

import java.util.ArrayList;

public class AddBookPage extends WebPage {

    public AddBookPage() {
        CompoundPropertyModel<Book> compoundPropertyModel = new CompoundPropertyModel<>(new Book());
        Form<Book> bookForm = new Form<>("form", compoundPropertyModel) {
            @Override
            protected void onSubmit() {
                BookRepository.getInstance().save((Book) getDefaultModelObject());
                redirectToInterceptPage(new HomePage());
            }
        };

        RequiredTextField<String> nameRequiredTextField = new RequiredTextField<>("name");
        bookForm.add(nameRequiredTextField);

        TextField<String> descriptionTextField = new TextField<>("description");
        bookForm.add(descriptionTextField);

        compoundPropertyModel.getObject().setAuthorList(new ArrayList<>());
        CheckGroup<Author> authorCheckGroup = new CheckGroup<>("authors");
        ListView<Author> checksList = new ListView<>("authorsForCheck", AuthorRepository.getInstance().getAll()) {
            @Override
            protected void populateItem(ListItem<Author> item) {
                Check<Author> check = new Check<>("check", item.getModel());
                check.setLabel(new Model<>(item.getModel().getObject().getName()));
                item.add(check);
                item.add(new SimpleFormComponentLabel("author12", check));
            }
        }.setReuseItems(true);
        authorCheckGroup.add(checksList);
        bookForm.add(authorCheckGroup);

        DropDownChoice<Publisher> publisherDropDownChoice = new DropDownChoice<>("publisher");
        publisherDropDownChoice.setChoices(PublisherRepository.getInstance().getAll());
        publisherDropDownChoice.setChoiceRenderer(new ChoiceRenderer<>() {
            @Override
            public Object getDisplayValue(Publisher object) {
                return object.getName();
            }
        });
        publisherDropDownChoice.setRequired(true);
        bookForm.add(publisherDropDownChoice);

        bookForm.add(new Link<>("return") {
            @Override
            public void onClick() {
                redirectToInterceptPage(new HomePage());
            }
        });
        add(bookForm);

        FeedbackPanel feedbackPanel = new FeedbackPanel("feedback");
        add(feedbackPanel);
    }
}

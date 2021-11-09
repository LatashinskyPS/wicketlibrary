package com.intexsoft.wicketlibrary.createpages;

import com.intexsoft.wicketlibrary.HomePage;
import com.intexsoft.wicketlibrary.entities.Author;
import com.intexsoft.wicketlibrary.entities.Book;
import com.intexsoft.wicketlibrary.entities.Publisher;
import com.intexsoft.wicketlibrary.repositories.AuthorRepository;
import com.intexsoft.wicketlibrary.repositories.BookRepository;
import com.intexsoft.wicketlibrary.repositories.PublisherRepository;
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
                Author author =item.getModelObject();
                check.setLabel(new Model<>(String.format("%s %s",author.getName(), author.getSurname())));
                item.add(check);
                item.add(new SimpleFormComponentLabel("authorName", check));
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

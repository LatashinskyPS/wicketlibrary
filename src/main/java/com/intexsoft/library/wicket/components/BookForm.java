package com.intexsoft.library.wicket.components;

import com.intexsoft.library.database.entities.Author;
import com.intexsoft.library.database.entities.Book;
import com.intexsoft.library.database.entities.Publisher;
import com.intexsoft.library.database.repositories.AuthorRepository;
import com.intexsoft.library.database.repositories.PublisherRepository;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.*;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.Model;

import java.util.ArrayList;

public abstract class BookForm extends Form<Book> {

    public BookForm(String id, Book book, String nameOfButton) {
        super(id, new CompoundPropertyModel<>(book));
        book.setAuthorList(AuthorRepository.getInstance().getByBook(book));
        if (book.getAuthorList() == null) {
            book.setAuthorList(new ArrayList<>());
        }

        RequiredTextField<String> nameRequiredTextField = new RequiredTextField<>("name");
        add(nameRequiredTextField);

        TextArea<String> stringTextArea = new TextArea<>("description");
        add(stringTextArea);

        CheckGroup<Author> authorCheckGroup = new CheckGroup<>("authors");
        ListView<Author> checksList = new ListView<>("authors", AuthorRepository.getInstance().getAll()) {
            @Override
            protected void populateItem(ListItem<Author> item) {
                Check<Author> check = new Check<>("check", item.getModel());
                Author author = item.getModelObject();
                check.setLabel(new Model<>(String.format("%s %s", author.getName(), author.getSurname())));
                item.add(check);
                item.add(new SimpleFormComponentLabel("authorName", check));
            }
        }.setReuseItems(true);
        authorCheckGroup.setRequired(true);
        authorCheckGroup.add(checksList);
        add(authorCheckGroup);
        add(new Link<>("return") {
            @Override
            public void onClick() {
                actionOnLinkReturn();
            }
        }.add(new Label("nameOfButton", new Model<>(nameOfButton))));
        DropDownChoice<Publisher> publisherDropDownChoice = new DropDownChoice<>("publisher");
        publisherDropDownChoice.setChoices(PublisherRepository.getInstance().getAll());
        publisherDropDownChoice.setChoiceRenderer(new ChoiceRenderer<>() {
            @Override
            public Object getDisplayValue(Publisher object) {
                return object.getName();
            }
        });
        publisherDropDownChoice.setRequired(true);
        add(publisherDropDownChoice);
        add(new FeedbackPanel("feedback"));
    }

    protected abstract void actionOnLinkReturn();
}

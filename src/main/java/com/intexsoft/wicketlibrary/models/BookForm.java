package com.intexsoft.wicketlibrary.models;

import com.intexsoft.wicketlibrary.entities.Author;
import com.intexsoft.wicketlibrary.entities.Book;
import com.intexsoft.wicketlibrary.entities.Publisher;
import com.intexsoft.wicketlibrary.repositories.AuthorRepository;
import com.intexsoft.wicketlibrary.repositories.PublisherRepository;
import org.apache.wicket.markup.html.form.*;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.Model;

import java.util.ArrayList;

public class BookForm extends Form<Book> {

    public BookForm(String id, Book book) {
        super(id, new CompoundPropertyModel<>(book));
        if (book.getAuthorList() == null) {
            book.setAuthorList(new ArrayList<>());
        }

        RequiredTextField<String> nameRequiredTextField = new RequiredTextField<>("name");
        add(nameRequiredTextField);

        TextField<String> descriptionTextField = new TextField<>("description");
        add(descriptionTextField);

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
        authorCheckGroup.add(checksList);
        add(authorCheckGroup);

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
    }
}
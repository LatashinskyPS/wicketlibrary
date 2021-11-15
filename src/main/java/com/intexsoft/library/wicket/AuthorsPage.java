package com.intexsoft.library.wicket;

import com.intexsoft.library.database.entities.Author;
import com.intexsoft.library.database.entities.Book;
import com.intexsoft.library.database.repositories.AuthorRepository;
import com.intexsoft.library.database.repositories.BookRepository;
import com.intexsoft.library.wicket.components.AbstractLibraryDataView;
import com.intexsoft.library.wicket.components.panels.NavbarPanel;
import com.intexsoft.library.wicket.components.panels.PaginationPanel;
import com.intexsoft.library.wicket.confirms.ConfirmDeleteBookPage;
import com.intexsoft.library.wicket.infopages.AuthorInfoPage;
import com.intexsoft.library.wicket.infopages.BookInfoPage;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.link.PopupSettings;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.markup.repeater.data.ListDataProvider;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import java.util.List;

public class AuthorsPage extends WebPage {
    public AuthorsPage() {
        add(new NavbarPanel("navbar"));
        List<Author> books = AuthorRepository.getInstance().getAll();
        ListDataProvider<Author> listDataProvider = new ListDataProvider<>(books);
        if (get("rows") != null){
            remove("rows");
        }
        DataView<Author> bookDataView = new AbstractLibraryDataView<>("rows", listDataProvider, AuthorInfoPage.class) {
            @Override
            protected Link<?> linkForEachItem(Item<Author> item) {
                Author book = item.getModelObject();
                return new BookmarkablePageLink<>("deleteButton", ConfirmDeleteBookPage.class, new PageParameters().set("Check", book.getId()))
                        .setPopupSettings(
                                new PopupSettings().setHeight(150).setWidth(500).setLeft(500).setTop(100)
                        );
            }
        };
        bookDataView.setItemsPerPage(1);
        add(bookDataView);
        add(new PaginationPanel("paginationPanel", bookDataView));
    }
}

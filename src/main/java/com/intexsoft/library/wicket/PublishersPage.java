package com.intexsoft.library.wicket;

import com.intexsoft.library.database.entities.Book;
import com.intexsoft.library.database.entities.Publisher;
import com.intexsoft.library.database.repositories.BookRepository;
import com.intexsoft.library.database.repositories.PublisherRepository;
import com.intexsoft.library.wicket.components.AbstractLibraryDataView;
import com.intexsoft.library.wicket.components.panels.NavbarPanel;
import com.intexsoft.library.wicket.components.panels.PaginationPanel;
import com.intexsoft.library.wicket.confirms.ConfirmDeleteBookPage;
import com.intexsoft.library.wicket.infopages.BookInfoPage;
import com.intexsoft.library.wicket.infopages.PublisherInfoPage;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.link.PopupSettings;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.markup.repeater.data.ListDataProvider;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import java.util.List;

public class PublishersPage extends WebPage {
    public PublishersPage() {
        add(new NavbarPanel("navbar"));
        List<Publisher> publishers = PublisherRepository.getInstance().getAll();
        ListDataProvider<Publisher> listDataProvider = new ListDataProvider<>(publishers);
        if (get("rows") != null){
            remove("rows");
        }
        DataView<Publisher> bookDataView = new AbstractLibraryDataView<>("rows", listDataProvider, PublisherInfoPage.class) {
            @Override
            protected Link<?> linkForEachItem(Item<Publisher> item) {
                Publisher publisher = item.getModelObject();
                return new BookmarkablePageLink<>("deleteButton", ConfirmDeleteBookPage.class, new PageParameters().set("Check", publisher.getId()))
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

package com.intexsoft.library.wicket;

import com.intexsoft.library.wicket.components.AbstractLibraryDataView;
import com.intexsoft.library.wicket.components.panels.PaginationPanel;
import com.intexsoft.library.wicket.infopages.BookInfoPage;
import com.intexsoft.library.wicket.confirms.ConfirmDeleteBookPage;
import com.intexsoft.library.wicket.createpages.AddAuthorPage;
import com.intexsoft.library.wicket.createpages.AddBookPage;
import com.intexsoft.library.wicket.createpages.AddPublisherPage;
import com.intexsoft.library.database.HibernateSessionFactory;
import com.intexsoft.library.database.entities.Book;
import com.intexsoft.library.wicket.components.panels.NavbarPanel;
import com.intexsoft.library.database.repositories.BookRepository;
import org.apache.wicket.Page;
import org.apache.wicket.ajax.AjaxNewWindowNotifyingBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.link.PopupSettings;
import org.apache.wicket.markup.html.navigation.paging.PagingNavigation;
import org.apache.wicket.markup.html.navigation.paging.PagingNavigationIncrementLink;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.RepeatingView;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.markup.repeater.data.ListDataProvider;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;

import java.util.List;

public class BooksPage extends WebPage {
    private static final long serialVersionUID = 1L;

    public BooksPage() {
        add(new NavbarPanel("navbar"));
        List<Book> books = BookRepository.getInstance().getAll();
        ListDataProvider<Book> listDataProvider = new ListDataProvider<>(books);
        if (get("rows") != null){
            remove("rows");
        }
        DataView<Book> bookDataView = new AbstractLibraryDataView<>("rows", listDataProvider, BookInfoPage.class) {
            @Override
            protected Link<?> linkForEachItem(Item<Book> item) {
                Book book = item.getModelObject();
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

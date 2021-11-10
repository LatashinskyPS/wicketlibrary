package com.intexsoft.wicketlibrary;

import com.intexsoft.wicketlibrary.bookinfo.BookInfoPage;
import com.intexsoft.wicketlibrary.confirms.ConfirmDeleteBookPage;
import com.intexsoft.wicketlibrary.createpages.AddAuthorPage;
import com.intexsoft.wicketlibrary.createpages.AddBookPage;
import com.intexsoft.wicketlibrary.createpages.AddPublisherPage;
import com.intexsoft.wicketlibrary.entities.Book;
import com.intexsoft.wicketlibrary.repositories.BookRepository;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.link.PopupSettings;
import org.apache.wicket.markup.html.navigation.paging.PagingNavigation;
import org.apache.wicket.markup.html.navigation.paging.PagingNavigationIncrementLink;
import org.apache.wicket.markup.html.navigation.paging.PagingNavigationLink;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.RepeatingView;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.markup.repeater.data.ListDataProvider;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import java.util.List;

public class HomePage extends WebPage {
    private static final long serialVersionUID = 1L;

    public HomePage() {
        HibernateSessionFactory.getSessionFactory();
        {
            add(new Link<>("addAuthorButton") {
                @Override
                public void onClick() {
                    redirectToInterceptPage(new AddAuthorPage());
                }
            });
            add(new Link<>("addPublisherButton") {
                @Override
                public void onClick() {
                    redirectToInterceptPage(new AddPublisherPage());
                }
            });
            add(new Link<>("addBookButton") {
                @Override
                public void onClick() {
                    redirectToInterceptPage(new AddBookPage());
                }
            });
        }

        List<Book> books = BookRepository.getInstance().getAll();
        ListDataProvider<Book> listDataProvider = new ListDataProvider<>(books);
        DataView<Book> dataView = new DataView<>("rows", listDataProvider) {
            @Override
            protected void populateItem(Item<Book> item) {
                Book book = item.getModelObject();
                RepeatingView repeatingView = new RepeatingView("dataRow");
                repeatingView.add(new Label(repeatingView.newChildId(), new PropertyModel<>(book, "name")));
                item.add(repeatingView);
                item.add(new Link<>("showButton") {
                    @Override
                    public void onClick() {
                        redirectToInterceptPage(new BookInfoPage(book));
                    }
                });
                item.add(new BookmarkablePageLink<>("deleteButton", ConfirmDeleteBookPage.class, new PageParameters().set("Check", book.getId()))
                        .setPopupSettings(
                                new PopupSettings().setHeight(150).setWidth(500).setLeft(500).setTop(100)
                        ));
            }
        };
        dataView.setItemsPerPage(5);

        add(dataView);
        PagingNavigation pagingNavigation = new PagingNavigation("pagingNavigation", dataView);
        add(new PagingNavigationLink<>("first", dataView, 0));
        add(new PagingNavigationIncrementLink<>("prev", dataView, -1));
        add(new PagingNavigationIncrementLink<>("next", dataView, 1));
        add(new PagingNavigationLink<>("last", dataView, -1));
        add(pagingNavigation);

        add(new Link<>("update") {
            @Override
            public void onClick() {
                redirectToInterceptPage(new HomePage());
            }
        });
    }
}

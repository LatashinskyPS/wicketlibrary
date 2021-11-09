package com.intexsoft.wicketlibrary;

import com.intexsoft.wicketlibrary.createpages.AddAuthorPage;
import com.intexsoft.wicketlibrary.createpages.AddBookPage;
import com.intexsoft.wicketlibrary.createpages.AddPublisherPage;
import com.intexsoft.wicketlibrary.entities.Book;
import com.intexsoft.wicketlibrary.repositories.BookRepository;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.navigation.paging.PagingNavigation;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.RepeatingView;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.markup.repeater.data.ListDataProvider;
import org.apache.wicket.model.Model;

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
                repeatingView.add(new Label(repeatingView.newChildId(), new Model<>(book)));
                item.add(repeatingView);
            }
        };
        dataView.setItemsPerPage(1);

        add(dataView);
        add(new PagingNavigation("pagingNavigator",dataView));
    }
}

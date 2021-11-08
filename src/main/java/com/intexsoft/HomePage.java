package com.intexsoft;

import com.intexsoft.createpages.AddAuthorPage;
import com.intexsoft.createpages.AddBookPage;
import com.intexsoft.createpages.AddPublisherPage;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.WebPage;

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
    }
}

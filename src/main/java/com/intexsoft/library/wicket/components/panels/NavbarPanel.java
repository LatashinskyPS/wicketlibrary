package com.intexsoft.library.wicket.components.panels;

import com.intexsoft.library.wicket.AuthorsPage;
import com.intexsoft.library.wicket.BooksPage;
import com.intexsoft.library.wicket.PublishersPage;
import com.intexsoft.library.wicket.createpages.AddAuthorPage;
import com.intexsoft.library.wicket.createpages.AddBookPage;
import com.intexsoft.library.wicket.createpages.AddPublisherPage;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;

public class NavbarPanel extends Panel {
    public NavbarPanel(String id) {
        super(id);
        add(new Link<>("authors-page") {
            @Override
            public void onClick() {
                redirectToInterceptPage(new AuthorsPage());
            }
        });
        add(new Link<>("publishers-page") {
            @Override
            public void onClick() {
                redirectToInterceptPage(new PublishersPage());
            }
        });
        add(new Link<>("books-page") {
            @Override
            public void onClick() {
                redirectToInterceptPage(new BooksPage());
            }
        });
    }

}

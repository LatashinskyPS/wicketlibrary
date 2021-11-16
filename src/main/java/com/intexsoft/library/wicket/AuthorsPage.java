package com.intexsoft.library.wicket;

import com.intexsoft.library.database.entities.Author;
import com.intexsoft.library.database.repositories.AuthorRepository;
import com.intexsoft.library.wicket.components.AbstractLibraryDataView;
import com.intexsoft.library.wicket.components.panels.general.FooterPanel;
import com.intexsoft.library.wicket.components.panels.general.NavbarPanel;
import com.intexsoft.library.wicket.components.panels.general.PaginationPanel;
import com.intexsoft.library.wicket.confirms.ConfirmDeleteAuthorPage;
import com.intexsoft.library.wicket.createpages.AddAuthorPage;
import com.intexsoft.library.wicket.infopages.AuthorInfoPage;
import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.markup.repeater.data.ListDataProvider;

import java.util.List;

public class AuthorsPage extends WebPage {
    private List<Author> authors;

    public AuthorsPage() {
        add(new Link<>("createAuthorButton") {
            @Override
            public void onClick() {
                redirectToInterceptPage(new AddAuthorPage());
            }
        });
        add(new NavbarPanel("navbar"));
        add(new FooterPanel("footer"));
        authors = AuthorRepository.getInstance().getAll();
        DataView<?> bookDataView;
        if (authors.isEmpty()) {
            bookDataView = new AbstractLibraryDataView<>("rows",new ListDataProvider<>(List.of("Empty"))) {
                @Override
                protected ModalWindow linkForEachItem(Item<String> item) {
                    return null;
                }
            };
        } else {
            ListDataProvider<Author> listDataProvider = new ListDataProvider<>(authors);
            bookDataView = new AbstractLibraryDataView<>("rows", listDataProvider, AuthorInfoPage.class) {
                @Override
                protected ModalWindow linkForEachItem(Item<Author> item) {
                    ModalWindow modalWindow = new ModalWindow("confirm");
                    modalWindow.setCookieName("delete-button");
                    modalWindow.setAutoSize(true);
                    modalWindow.setPageCreator((ModalWindow.PageCreator) () ->
                            new ConfirmDeleteAuthorPage(AuthorsPage.this.getPageReference(), modalWindow, item.getModelObject()));
                    modalWindow.setWindowClosedCallback((ModalWindow.WindowClosedCallback) ajaxRequestTarget ->
                            AuthorsPage.this.redirectToInterceptPage(new AuthorsPage()));
                    return modalWindow;
                }
            };
        }

        bookDataView.setItemsPerPage(1);
        add(bookDataView);
        add(new PaginationPanel("paginationPanel", bookDataView));
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }
}

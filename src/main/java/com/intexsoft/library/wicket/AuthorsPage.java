package com.intexsoft.library.wicket;

import com.intexsoft.library.database.entities.Author;
import com.intexsoft.library.database.entities.Book;
import com.intexsoft.library.database.repositories.AuthorRepository;
import com.intexsoft.library.database.repositories.BookRepository;
import com.intexsoft.library.wicket.components.AbstractLibraryDataView;
import com.intexsoft.library.wicket.components.panels.NavbarPanel;
import com.intexsoft.library.wicket.components.panels.PaginationPanel;
import com.intexsoft.library.wicket.confirms.ConfirmDeleteAuthorPage;
import com.intexsoft.library.wicket.confirms.ConfirmDeleteBookPage;
import com.intexsoft.library.wicket.createpages.AddAuthorPage;
import com.intexsoft.library.wicket.createpages.AddBookPage;
import com.intexsoft.library.wicket.infopages.AuthorInfoPage;
import com.intexsoft.library.wicket.infopages.BookInfoPage;
import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow;
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
    private List<Author> authors;

    public AuthorsPage() {
        add(new Link<>("createAuthorButton") {
            @Override
            public void onClick() {
                redirectToInterceptPage(new AddAuthorPage());
            }
        });
        add(new NavbarPanel("navbar"));
        authors = AuthorRepository.getInstance().getAll();
        ListDataProvider<Author> listDataProvider = new ListDataProvider<>(authors);
        if (get("rows") != null) {
            remove("rows");
        }
        DataView<Author> bookDataView = new AbstractLibraryDataView<>("rows", listDataProvider, AuthorInfoPage.class) {
            @Override
            protected Component linkForEachItem(Item<Author> item) {
                ModalWindow modalWindow = new ModalWindow("confirm");
                modalWindow.setCookieName("delete-button");
                modalWindow.setPageCreator((ModalWindow.PageCreator) () ->
                        new ConfirmDeleteAuthorPage(AuthorsPage.this.getPageReference(), modalWindow, item.getModelObject()));
                modalWindow.setWindowClosedCallback((ModalWindow.WindowClosedCallback) ajaxRequestTarget ->
                        AuthorsPage.this.redirectToInterceptPage(new AuthorsPage()));
                item.add(new AjaxLink<Void>("deleteButton") {
                    @Override
                    public void onClick(AjaxRequestTarget target) {
                        modalWindow.show(target);
                    }
                });
                return modalWindow;
            }
        };
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

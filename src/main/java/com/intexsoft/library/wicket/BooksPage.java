package com.intexsoft.library.wicket;

import com.intexsoft.library.wicket.components.AbstractLibraryDataView;
import com.intexsoft.library.wicket.components.panels.general.FooterPanel;
import com.intexsoft.library.wicket.components.panels.general.PaginationPanel;
import com.intexsoft.library.wicket.infopages.BookInfoPage;
import com.intexsoft.library.wicket.confirms.ConfirmDeleteBookPage;
import com.intexsoft.library.wicket.createpages.AddBookPage;
import com.intexsoft.library.database.entities.Book;
import com.intexsoft.library.wicket.components.panels.general.NavbarPanel;
import com.intexsoft.library.database.repositories.BookRepository;
import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.RepeatingView;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.markup.repeater.data.ListDataProvider;
import org.apache.wicket.model.Model;

import java.util.List;

public class BooksPage extends WebPage {
    private static final long serialVersionUID = 1L;

    private List<Book> books;

    public BooksPage() {
        add(new NavbarPanel("navbar"));
        add(new FooterPanel("footer"));
        add(new Link<>("createBookButton") {
            @Override
            public void onClick() {
                redirectToInterceptPage(new AddBookPage());
            }
        });
        books = BookRepository.getInstance().getAll();
        if (get("rows") != null) {
            remove("rows");
        }
        DataView<?> bookDataView;
        if (books.isEmpty()) {
            bookDataView = new AbstractLibraryDataView<>("rows", new ListDataProvider<>(List.of("Empty"))) {
                @Override
                protected ModalWindow linkForEachItem(Item<String> item) {
                    return null;
                }
            };
        } else {
            ListDataProvider<Book> listDataProvider = new ListDataProvider<>(books);
            bookDataView = new AbstractLibraryDataView<>("rows", listDataProvider, BookInfoPage.class) {
                @Override
                protected ModalWindow linkForEachItem(Item<Book> item) {
                    ModalWindow modalWindow = new ModalWindow("confirm");
                    modalWindow.setCookieName("delete-button");
                    modalWindow.setPageCreator((ModalWindow.PageCreator) () ->
                            new ConfirmDeleteBookPage(BooksPage.this.getPageReference(), modalWindow, item.getModelObject()));
                    modalWindow.setWindowClosedCallback((ModalWindow.WindowClosedCallback) ajaxRequestTarget ->
                            BooksPage.this.redirectToInterceptPage(new BooksPage()));
                    return modalWindow;
                }
            };
        }
        bookDataView.setItemsPerPage(1);
        add(bookDataView);
        add(new PaginationPanel("paginationPanel", bookDataView));
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}

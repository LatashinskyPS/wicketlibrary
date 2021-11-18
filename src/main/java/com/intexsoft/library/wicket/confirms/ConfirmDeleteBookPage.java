package com.intexsoft.library.wicket.confirms;

import com.intexsoft.library.database.entities.Book;
import com.intexsoft.library.database.repositories.BookRepository;
import com.intexsoft.library.database.services.AuthorService;
import com.intexsoft.library.database.services.BookService;
import org.apache.wicket.PageReference;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.spring.injection.annot.SpringBean;

public class ConfirmDeleteBookPage extends WebPage {
    @SpringBean
    private BookService bookService;

    public ConfirmDeleteBookPage(final PageReference pageReference, ModalWindow modalWindow, Book book) {
        add(new AjaxLink<>("close") {
            @Override
            public void onClick(AjaxRequestTarget target) {
                modalWindow.close(target);
            }
        });
        add(new AjaxLink<>("confirm") {
            @Override
            public void onClick(AjaxRequestTarget target) {
                if (pageReference != null) {
                    bookService.delete(book);
                }
                modalWindow.close(target);
            }
        });
    }
}

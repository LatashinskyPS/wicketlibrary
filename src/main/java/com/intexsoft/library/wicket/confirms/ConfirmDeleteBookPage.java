package com.intexsoft.library.wicket.confirms;

import com.intexsoft.library.database.entities.Book;
import com.intexsoft.library.database.repositories.BookRepository;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.link.PopupCloseLink;

import java.util.UUID;

public class ConfirmDeleteBookPage extends WebPage {

    public ConfirmDeleteBookPage() {
        add(new PopupCloseLink<>("close"));
        add(new PopupCloseLink<>("confirm") {
            @Override
            public void onClick() {
                Book book = BookRepository.getInstance().findById(UUID.fromString(getPageParameters().get("Check").toString()));
                BookRepository.getInstance().deleteBook(book);
                ConfirmDeleteBookPage.this.getDefaultModelObject();
                super.onClick();
            }
        });
    }
}

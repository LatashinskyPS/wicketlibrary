package com.intexsoft.library.wicket.confirms;

import com.intexsoft.library.database.entities.Author;
import com.intexsoft.library.database.repositories.AuthorRepository;
import com.intexsoft.library.database.services.AuthorService;
import org.apache.wicket.PageReference;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.spring.injection.annot.SpringBean;

public class ConfirmDeleteAuthorPage extends WebPage {
    @SpringBean
    private AuthorService authorService;

    public ConfirmDeleteAuthorPage(final PageReference pageReference, ModalWindow modalWindow, Author author) {
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
                    authorService.delete(author);
                }
                modalWindow.close(target);
            }
        });
    }
}

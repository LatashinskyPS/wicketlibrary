package com.intexsoft.library.wicket.confirms;

import com.intexsoft.library.database.entities.Publisher;
import com.intexsoft.library.database.repositories.PublisherRepository;
import com.intexsoft.library.database.services.AuthorService;
import com.intexsoft.library.database.services.PublisherService;
import org.apache.wicket.PageReference;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.spring.injection.annot.SpringBean;

public class ConfirmDeletePublisherPage extends WebPage {

    @SpringBean
    private PublisherService publisherService;

    public ConfirmDeletePublisherPage(final PageReference pageReference, ModalWindow modalWindow, Publisher publisher) {
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
                    publisherService.delete(publisher);
                }
                modalWindow.close(target);
            }
        });
    }
}

package com.intexsoft.library.wicket;

import com.intexsoft.library.database.entities.Publisher;
import com.intexsoft.library.database.repositories.PublisherRepository;
import com.intexsoft.library.wicket.components.AbstractLibraryDataView;
import com.intexsoft.library.wicket.components.panels.general.FooterPanel;
import com.intexsoft.library.wicket.components.panels.general.NavbarPanel;
import com.intexsoft.library.wicket.components.panels.general.PaginationPanel;
import com.intexsoft.library.wicket.confirms.ConfirmDeletePublisherPage;
import com.intexsoft.library.wicket.createpages.AddPublisherPage;
import com.intexsoft.library.wicket.infopages.PublisherInfoPage;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.markup.repeater.data.ListDataProvider;

import java.util.List;

public class PublishersPage extends WebPage {

    public PublishersPage() {
        add(new NavbarPanel("navbar"));
        add(new FooterPanel("footer"));
        add(new Link<>("createPublisherButton") {
            @Override
            public void onClick() {
                redirectToInterceptPage(new AddPublisherPage());
            }
        });
        List<Publisher> publishers = PublisherRepository.getInstance().getAll();
        DataView<?> bookDataView;
        if (publishers.isEmpty()) {
            bookDataView = new AbstractLibraryDataView<>("rows", new ListDataProvider<>(List.of("Empty"))) {
                @Override
                protected ModalWindow linkForEachItem(Item<String> item) {
                    return null;
                }
            };
        } else {
            ListDataProvider<Publisher> listDataProvider = new ListDataProvider<>(publishers);
            bookDataView = new AbstractLibraryDataView<>("rows", listDataProvider, PublisherInfoPage.class) {
                @Override
                protected ModalWindow linkForEachItem(Item<Publisher> item) {
                    ModalWindow modalWindow = new ModalWindow("confirm");
                    modalWindow.setCookieName("delete-button");
                    modalWindow.setPageCreator((ModalWindow.PageCreator) () ->
                            new ConfirmDeletePublisherPage(PublishersPage.this.getPageReference(), modalWindow, item.getModelObject()));
                    modalWindow.setWindowClosedCallback((ModalWindow.WindowClosedCallback) ajaxRequestTarget ->
                            PublishersPage.this.redirectToInterceptPage(new PublishersPage()));
                    return modalWindow;
                }
            };
        }
        bookDataView.setItemsPerPage(5);
        add(bookDataView);
        add(new PaginationPanel("paginationPanel", bookDataView));
    }
}

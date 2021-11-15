package com.intexsoft.library.wicket;

import com.intexsoft.library.database.entities.Book;
import com.intexsoft.library.database.entities.Publisher;
import com.intexsoft.library.database.repositories.BookRepository;
import com.intexsoft.library.database.repositories.PublisherRepository;
import com.intexsoft.library.wicket.components.AbstractLibraryDataView;
import com.intexsoft.library.wicket.components.panels.NavbarPanel;
import com.intexsoft.library.wicket.components.panels.PaginationPanel;
import com.intexsoft.library.wicket.confirms.ConfirmDeleteAuthorPage;
import com.intexsoft.library.wicket.confirms.ConfirmDeleteBookPage;
import com.intexsoft.library.wicket.confirms.ConfirmDeletePublisherPage;
import com.intexsoft.library.wicket.infopages.BookInfoPage;
import com.intexsoft.library.wicket.infopages.PublisherInfoPage;
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

public class PublishersPage extends WebPage {
    private List<Publisher> publishers;

    public PublishersPage() {
        add(new NavbarPanel("navbar"));
        publishers = PublisherRepository.getInstance().getAll();
        ListDataProvider<Publisher> listDataProvider = new ListDataProvider<>(publishers);
        if (get("rows") != null) {
            remove("rows");
        }
        DataView<Publisher> bookDataView = new AbstractLibraryDataView<>("rows", listDataProvider, PublisherInfoPage.class) {
            @Override
            protected Component linkForEachItem(Item<Publisher> item) {
                ModalWindow modalWindow = new ModalWindow("confirm");
                modalWindow.setCookieName("delete-button");
                modalWindow.setPageCreator((ModalWindow.PageCreator) () ->
                        new ConfirmDeletePublisherPage(PublishersPage.this.getPageReference(), modalWindow, item.getModelObject()));
                modalWindow.setWindowClosedCallback((ModalWindow.WindowClosedCallback) ajaxRequestTarget ->
                        PublishersPage.this.redirectToInterceptPage(new PublishersPage()));
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

    public List<Publisher> getPublishers() {
        return publishers;
    }

    public void setPublishers(List<Publisher> publishers) {
        this.publishers = publishers;
    }
}

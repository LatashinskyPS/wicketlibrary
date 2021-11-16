package com.intexsoft.library.wicket.components.panels.buttons;

import org.apache.wicket.Page;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;

public class NotEmptyButtonsPanel extends Panel {
    public NotEmptyButtonsPanel(String id, ModalWindow modalWindow, Page page) {
        super(id);
        add(modalWindow);
        add(new Link<>("showButton") {
            @Override
            public void onClick() {
                redirectToInterceptPage(page);
            }
        });
        add(new AjaxLink<Void>("deleteButton") {
            @Override
            public void onClick(AjaxRequestTarget target) {
                modalWindow.show(target);
            }
        });
    }
}

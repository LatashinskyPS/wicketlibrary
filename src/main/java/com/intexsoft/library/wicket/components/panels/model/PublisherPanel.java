package com.intexsoft.library.wicket.components.panels.model;

import com.intexsoft.library.wicket.components.PublisherForm;
import org.apache.wicket.markup.html.panel.Panel;

public class PublisherPanel extends Panel {
    public PublisherPanel(String id, PublisherForm publisherForm) {
        super(id);
        add(publisherForm);
    }
}

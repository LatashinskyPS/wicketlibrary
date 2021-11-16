package com.intexsoft.library.wicket.components.panels.model;

import com.intexsoft.library.wicket.components.AuthorForm;
import org.apache.wicket.markup.html.panel.Panel;

public class AuthorPanel extends Panel {
    public AuthorPanel(String id, AuthorForm authorForm) {
        super(id);
        add(authorForm);
    }
}

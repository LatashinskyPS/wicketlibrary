package com.intexsoft.library.wicket.components.panels.model;

import com.intexsoft.library.wicket.components.BookForm;
import org.apache.wicket.markup.html.panel.Panel;

public class BookPanel extends Panel {
    public BookPanel(String id, BookForm bookForm) {
        super(id);
        add(bookForm);
    }
}

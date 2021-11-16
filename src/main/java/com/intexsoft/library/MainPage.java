package com.intexsoft.library;

import com.intexsoft.library.wicket.components.panels.general.FooterPanel;
import com.intexsoft.library.wicket.components.panels.general.NavbarPanel;
import org.apache.wicket.markup.html.WebPage;

public class MainPage extends WebPage {

    public MainPage() {
        add(new NavbarPanel("navbar"));
        add(new FooterPanel("footer"));
    }


}

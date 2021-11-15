package com.intexsoft.library.wicket.components.panels;


import org.apache.wicket.markup.html.navigation.paging.PagingNavigation;
import org.apache.wicket.markup.html.navigation.paging.PagingNavigationIncrementLink;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.data.DataView;

public class PaginationPanel extends Panel {
    public PaginationPanel(String id, DataView<?> dataView) {
        super(id);
        PagingNavigation pagingNavigation = new PagingNavigation("pagingNavigation", dataView);
        add(new PagingNavigationIncrementLink<>("prev", dataView, -1));
        add(new PagingNavigationIncrementLink<>("next", dataView, 1));
        add(pagingNavigation);
    }
}

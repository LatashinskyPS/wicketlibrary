package com.intexsoft.library.wicket.components;

import org.apache.wicket.Page;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.RepeatingView;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.markup.repeater.data.IDataProvider;
import org.apache.wicket.model.PropertyModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;

public abstract class AbstractLibraryDataView<T> extends DataView<T> {
    Logger logger = LoggerFactory.getLogger(AbstractLibraryDataView.class);
    private final Class<? extends Page> showPageClass;

    public AbstractLibraryDataView(String id, IDataProvider<T> dataProvider, Class<? extends Page> showPageClass) {
        super(id, dataProvider);
        this.showPageClass = showPageClass;
    }

    @Override
    protected void populateItem(Item<T> item) {
        Object object = item.getModelObject();
        Page page;
        try {
            page = showPageClass.getConstructor(item.getModelObject().getClass()).newInstance(item.getModelObject());
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            logger.error(e.getMessage());
            return;
        }
        RepeatingView repeatingView = new RepeatingView("dataRow");
        repeatingView.add(new Label(repeatingView.newChildId(), new PropertyModel<>(object, "name")));
        item.add(repeatingView);
        item.add(new Link<>("showButton") {
            @Override
            public void onClick() {
                redirectToInterceptPage(page);
            }
        });
        item.add(linkForEachItem(item));
    }

    protected abstract Link<?> linkForEachItem(Item<T> item);
}

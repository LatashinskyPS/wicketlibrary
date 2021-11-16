package com.intexsoft.library.wicket.components;

import com.intexsoft.library.wicket.components.panels.buttons.EmptyPanel;
import com.intexsoft.library.wicket.components.panels.buttons.NotEmptyButtonsPanel;
import org.apache.wicket.Page;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.RepeatingView;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.markup.repeater.data.IDataProvider;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.util.Objects;

public abstract class AbstractLibraryDataView<T> extends DataView<T> {
    Logger logger = LoggerFactory.getLogger(AbstractLibraryDataView.class);
    private final Class<? extends Page> showPageClass;
    private final boolean isEmpty;

    public AbstractLibraryDataView(String id, IDataProvider<T> dataProvider) {
        super(id, dataProvider);
        showPageClass = null;
        isEmpty = true;
    }

    public AbstractLibraryDataView(String id, IDataProvider<T> dataProvider, Class<? extends Page> showPageClass) {
        super(id, dataProvider);
        this.showPageClass = showPageClass;
        this.isEmpty = false;
    }

    @Override
    protected void populateItem(Item<T> item) {
        if (isEmpty) {
            createIfEmpty(item);
        } else {
            createIfNotEmpty(item);
        }
    }

    private void createIfEmpty(Item<T> item) {
        item.add(new RepeatingView("dataRow").add(new Label(newChildId(), new Model<>("Empty"))));
        item.add(new EmptyPanel("panel"));
    }

    private void createIfNotEmpty(Item<T> item) {
        Object object = item.getModelObject();
        RepeatingView repeatingView = new RepeatingView("dataRow");
        repeatingView.add(new Label(repeatingView.newChildId(), new PropertyModel<>(object, "name")));
        item.add(repeatingView);
        Page page;
        try {
            page = Objects.requireNonNull(showPageClass).getConstructor(object.getClass()).newInstance(object);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            logger.error(e.getMessage());
            return;
        }
        item.add(new NotEmptyButtonsPanel("panel", linkForEachItem(item), page));
    }

    protected abstract ModalWindow linkForEachItem(Item<T> item);
}

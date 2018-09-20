package cying.gui.model;

import cying.entity.CategoryEntity;
import cying.service.CategoryService;

import javax.swing.*;
import javax.swing.event.ListDataListener;
import java.util.List;

/**
 * Created by samson on 17-4-17.
 */
public class CategoryComboBoxModel implements ComboBoxModel<CategoryEntity> {

    public List<CategoryEntity> categories = CategoryService.list();

    CategoryEntity category;
    public CategoryComboBoxModel() {
        if (! categories.isEmpty())
            category = categories.get(0);
    }

    @Override
    public void setSelectedItem(Object anItem) {
        category = (CategoryEntity) anItem;
    }

    @Override
    public Object getSelectedItem() {
        if (! categories.isEmpty())
            return category;
        else
            return null;
    }

    @Override
    public int getSize() {
        return categories.size();
    }

    @Override
    public CategoryEntity getElementAt(int index) {
        return categories.get(index);
    }

    @Override
    public void addListDataListener(ListDataListener l) {

    }

    @Override
    public void removeListDataListener(ListDataListener l) {

    }
}

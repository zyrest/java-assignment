package cying.gui.model;

import cying.entity.CategoryEntity;
import cying.service.CategoryService;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import java.util.List;

/**
 * Created by samson on 17-4-17.
 */
public class CategoryTableModel implements TableModel {


    String[] columnNames = {"分类名称","消费次数"};
    public List<CategoryEntity> categories = CategoryService.list();

    public CategoryTableModel() {

    }
    @Override
    public int getRowCount() {
        return categories.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return columnNames[columnIndex];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return String.class;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if(columnIndex == 0) {
            return categories.get(rowIndex).getName();
        } else if(columnIndex == 1) {
            return categories.get(rowIndex).getRecordNum();
        }

        return null;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {

    }

    @Override
    public void addTableModelListener(TableModelListener l) {

    }

    @Override
    public void removeTableModelListener(TableModelListener l) {

    }
}

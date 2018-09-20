package cying.gui.panel;

import cying.entity.CategoryEntity;
import cying.gui.listener.CategoryListener;
import cying.gui.model.CategoryTableModel;
import cying.service.CategoryService;
import cying.util.ColorUtil;
import cying.util.GUIutil;

import javax.swing.*;
import java.awt.*;

/**
 * Created by samson on 17-4-17.
 */
public class CategoryPanel extends WorkingPanel {
    static {
        GUIutil.useLiquidLook();
        GUIutil.useFont();
    }

    public JButton addButton = new JButton("新增");
    public JButton deleteButton = new JButton("删除");
    public JButton editButton = new JButton("编辑");
    String columNames[] = new String[] { "分类名称", "消费次数" };

    public CategoryTableModel categoryModel = new CategoryTableModel();
    public JTable categories = new JTable(categoryModel);

    private static CategoryPanel instance;
    public static CategoryPanel getInstance() {
        if(instance == null) instance = new CategoryPanel();
        return instance;
    }
    private CategoryPanel() {
        GUIutil.setColor(ColorUtil.BLUE,
                addButton, editButton, deleteButton);

        this.setLayout(new BorderLayout());
        this.add(centre(), BorderLayout.CENTER);
        this.add(south(), BorderLayout.SOUTH);

    }
    private JComponent centre() {
        JScrollPane centre = new JScrollPane(categories);

        return centre;
    }
    private JComponent south() {
        JPanel south = new JPanel();

        south.add(addButton);
        south.add(editButton);
        south.add(deleteButton);
        addListener();

        return south;
    }

    public void addListener() {
        CategoryListener listener = new CategoryListener();
        addButton.addActionListener(listener);
        deleteButton.addActionListener(listener);
        editButton.addActionListener(listener);
    }

    //当前选择行的category的实体
    public CategoryEntity getSelectedCategory() {
        int index = categories.getSelectedRow();
        return categoryModel.categories.get(index);
    }

    //更新数据
    public void updateData() {
        categoryModel.categories = CategoryService.list();
        categories.updateUI();
        categories.getSelectionModel().setSelectionInterval(0, 0);

        if (categoryModel.categories.size() == 0) {
            deleteButton.setEnabled(false);
            editButton.setEnabled(false);
        } else {
            deleteButton.setEnabled(true);
            editButton.setEnabled(true);
        }
    }

    public static void main(String[] args) {
        GUIutil.showPanel(getInstance());
    }
}

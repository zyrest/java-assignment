package cying.gui.listener;

import cying.entity.CategoryEntity;
import cying.gui.panel.CategoryPanel;
import cying.service.CategoryService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by samson on 17-4-21.
 */
public class CategoryListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        CategoryPanel panel = CategoryPanel.getInstance();
        JButton button = (JButton) e.getSource();

        if (button == panel.addButton) {

            String name = JOptionPane.showInputDialog(null);
            if (name.length() == 0) {
                JOptionPane.showMessageDialog(panel, "分类名不能为空");
                return;
            }
            CategoryService.add(name);

        } else if (button == panel.editButton) {

            CategoryEntity category = panel.getSelectedCategory();
            String name = JOptionPane.showInputDialog(null);

            if (name.length() == 0) {
                JOptionPane.showMessageDialog(panel, "分类名不能为空");
                return;
            }

            CategoryService.update(category.getId(), name);
        } else if (button == panel.deleteButton){

            CategoryEntity category = panel.getSelectedCategory();

            if (category.getRecordNum() != 0) {
                JOptionPane.showMessageDialog(panel, "本分类下有消费记录存在，不能删除");
                return;
            } else {
                int confirm = JOptionPane.showConfirmDialog(panel, "确定要删除吗？");
                if (confirm != JOptionPane.OK_OPTION)
                    return;
            }

            int id = category.getId();
            CategoryService.delete(id);

        }

        panel.updateData();
    }
}

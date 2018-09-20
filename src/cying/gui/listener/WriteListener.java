package cying.gui.listener;

import cying.entity.CategoryEntity;
import cying.gui.panel.CategoryPanel;
import cying.gui.panel.HomePanel;
import cying.gui.panel.MainPanel;
import cying.gui.panel.WritePanel;
import cying.service.RecordService;
import cying.util.DateUtil;
import cying.util.GUIutil;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;

/**
 * Created by samson on 17-4-21.
 */
public class WriteListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        WritePanel panel = WritePanel.getInstance();

        if (panel.categoryModel.categories.isEmpty()) {
            JOptionPane.showMessageDialog(panel, "暂时没有消费分类哦，请去添加一个");
            MainPanel.getInstance().workingPanel.show(CategoryPanel.getInstance());
            return;
        }
        if (! GUIutil.isNumber(panel.writeText, "消费金额"))
            return;


        int spend = Integer.parseInt(panel.writeText.getText());
        CategoryEntity category = (CategoryEntity) panel.categoryBox.getSelectedItem();
        String comment = panel.commentText.getText().trim();
        Timestamp now = DateUtil.dateToTimestamp( panel.datePicker.getDate() );

        RecordService.add(spend, category, comment, now);
        JOptionPane.showMessageDialog(panel, "添加成功");

        MainPanel.getInstance().workingPanel.show(HomePanel.getInstance());
    }
}

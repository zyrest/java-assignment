package cying.gui.listener;

import cying.gui.panel.ConfigPanel;
import cying.service.ConfigService;
import cying.util.GUIutil;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * Created by samson on 17-4-20.
 */
public class ConfigListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        ConfigPanel panel = ConfigPanel.getInstance();
        String path = panel.databaseValue.getText().trim();

        if (! GUIutil.isNumber(panel.spendValue, "消费金额"))
            return;

        if (path.length() != 0) {

            File mysql = new File(path, "bin/mysql.exe");
            if (! mysql.exists()) {
                JOptionPane.showMessageDialog(panel, "MySql路径不正确");
                panel.databaseValue.grabFocus();
                return;
            }

        }

        ConfigService.update(ConfigService.BUDGET, panel.spendValue.getText().trim());
        ConfigService.update(ConfigService.MYSQL_PATH, path);

        JOptionPane.showMessageDialog(panel, "修改成功");
    }
}

package cying.gui.listener;

import cying.gui.panel.ConfigPanel;
import cying.gui.panel.MainPanel;
import cying.gui.panel.RecoverPanel;
import cying.service.ConfigService;
import cying.util.MysqlUtil;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * Created by 96428 on 2017/4/23.
 */
public class RecoverListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        RecoverPanel panel = RecoverPanel.getInstance();
        String mysqlPath = ConfigService.getValue(ConfigService.MYSQL_PATH);

        if (mysqlPath.length() == 0) {
            JOptionPane.showMessageDialog(panel, "请去设置Mysql安装目录");
            MainPanel.getInstance().workingPanel.show(ConfigPanel.getInstance());
            ConfigPanel.getInstance().databaseValue.grabFocus();
            return;
        }

        JFileChooser chooser = new JFileChooser();
        chooser.setFileFilter(new FileFilter() {
            @Override
            public boolean accept(File f) {
                return f.getName().trim().toLowerCase().endsWith(".sql");
            }

            @Override
            public String getDescription() {
                return ".sql";
            }
        });

        int returnVal = chooser.showOpenDialog(panel);
        File file = chooser.getSelectedFile();

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            try {
                MysqlUtil.recover(mysqlPath, file.getAbsolutePath());
                JOptionPane.showMessageDialog(panel, "恢复成功~");
            } catch (Exception ee) {
                ee.printStackTrace();
                JOptionPane.showMessageDialog(panel, "对不起，恢复出错");
            }
        }

    }
}

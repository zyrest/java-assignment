package cying.gui.listener;

import cying.gui.panel.BackupPanel;
import cying.gui.panel.ConfigPanel;
import cying.gui.panel.MainPanel;
import cying.service.ConfigService;
import cying.util.MysqlUtil;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

/**
 * Created by samson on 17-4-23.
 */
public class BackupListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        BackupPanel panel = BackupPanel.getInstance();
        String mysqlPath = ConfigService.getValue(ConfigService.MYSQL_PATH);

        if (mysqlPath.length() == 0) {
            JOptionPane.showMessageDialog(panel, "请先设置Mysql目录");
            MainPanel.getInstance().workingPanel.show(ConfigPanel.getInstance());
            ConfigPanel.getInstance().databaseValue.grabFocus();
            return;
        }

        JFileChooser chooser = new JFileChooser();
        chooser.setSelectedFile(new File("accounting.sql"));
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

        int returnVal = chooser.showSaveDialog(panel);
        File file = chooser.getSelectedFile();
        System.out.println(file);
        if (returnVal == JFileChooser.APPROVE_OPTION) {

            if (! file.getName().toLowerCase().endsWith(".sql")) {
                file = new File(file.getParent(), file.getName() + ".sql");
            }

            try {
                MysqlUtil.backup(mysqlPath, file.getAbsolutePath());
                JOptionPane.showMessageDialog(panel, "备份成功,备份文件位于:\r\n" + file.getAbsolutePath());
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }
}

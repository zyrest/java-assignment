package cying.gui.listener;

import cying.gui.panel.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by samson on 17-4-20.
 */
public class ToolBarListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        MainPanel main = MainPanel.getInstance();
        JButton button = (JButton) e.getSource();

        if(button == main.homeButton)
            main.workingPanel.show(HomePanel.getInstance());
        else if (button == main.writeButton)
            main.workingPanel.show(WritePanel.getInstance());
        else if (button == main.categoryButton)
            main.workingPanel.show(CategoryPanel.getInstance());
        else if (button == main.reportButton)
            main.workingPanel.show(ReportPanel.getInstance());
        else if (button == main.configButton)
            main.workingPanel.show(ConfigPanel.getInstance());
        else if (button == main.recoverButton)
            main.workingPanel.show(RecoverPanel.getInstance());
        else if (button == main.backupButton)
            main.workingPanel.show(BackupPanel.getInstance());

    }
}

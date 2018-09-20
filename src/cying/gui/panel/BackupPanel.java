package cying.gui.panel;

import cying.gui.listener.BackupListener;
import cying.util.ColorUtil;
import cying.util.GUIutil;

import javax.swing.*;

/**
 * Created by samson on 17-4-17.
 */
public class BackupPanel extends WorkingPanel {
    static {
        GUIutil.useLiquidLook();
        GUIutil.useFont();
    }

    public JButton backupButton = new JButton("备份一下");

    private static BackupPanel instance;
    public static BackupPanel getInstance() {
        if(instance == null) instance = new BackupPanel();
        return instance;
    }
    private BackupPanel() {
        GUIutil.setColor(ColorUtil.BLUE, backupButton);
        this.add(backupButton);
        addListener();
    }

    @Override
    public void addListener() {
        BackupListener listener = new BackupListener();
        backupButton.addActionListener(listener);
    }

    @Override
    public void updateData() {

    }

    public static void main(String[] args) {
        GUIutil.showPanel(getInstance());
    }
}

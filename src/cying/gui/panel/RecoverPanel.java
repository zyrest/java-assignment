package cying.gui.panel;

import cying.gui.listener.RecoverListener;
import cying.util.ColorUtil;
import cying.util.GUIutil;

import javax.swing.*;

/**
 * Created by samson on 17-4-17.
 */
public class RecoverPanel extends WorkingPanel {
    static {
        GUIutil.useLiquidLook();
        GUIutil.useFont();
    }

    public JButton recoverButton = new JButton("恢复一下");

    private static RecoverPanel instance;
    public static RecoverPanel getInstance() {
        if(instance == null) instance = new RecoverPanel();
        return instance;
    }
    private RecoverPanel() {
        GUIutil.setColor(ColorUtil.BLUE, recoverButton);
        this.add(recoverButton);
        addListener();
    }

    @Override
    public void addListener() {
        RecoverListener listener = new RecoverListener();
        recoverButton.addActionListener(listener);
    }

    @Override
    public void updateData() {

    }

    public static void main(String[] args) {
        GUIutil.showPanel(getInstance());
    }
}

package cying.startup;

import cying.gui.frame.MainFrame;
import cying.gui.panel.HomePanel;
import cying.gui.panel.MainPanel;
import cying.util.GUIutil;

import javax.swing.*;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by samson on 17-4-18.
 */
public class Bootstrap {
    public static void main(String[] args) throws InvocationTargetException, InterruptedException {
        GUIutil.useLiquidLook();
        GUIutil.useFont();

        SwingUtilities.invokeAndWait(new Runnable() {
            @Override
            public void run() {
                MainFrame.getInstance().setVisible(true);
                MainPanel.getInstance().workingPanel.show(HomePanel.getInstance());
            }
        });
    }
}

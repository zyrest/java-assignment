package cying.gui.frame;

import cying.gui.panel.MainPanel;

import javax.swing.*;

/**
 * Created by 96428 on 2017/4/6.
 */
public class MainFrame extends JFrame {

    private static MainFrame instance = new MainFrame();
    public static MainFrame getInstance() {
        return instance;
    }
    private MainFrame() {
        this.setSize(550,500);
        this.setTitle("易记");
        this.setContentPane(MainPanel.getInstance());
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        getInstance().setVisible(true);
    }
}

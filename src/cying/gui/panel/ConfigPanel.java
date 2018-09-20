package cying.gui.panel;

import cying.gui.listener.ConfigListener;
import cying.service.ConfigService;
import cying.util.ColorUtil;
import cying.util.GUIutil;

import javax.swing.*;
import java.awt.*;

/**
 * Created by samson on 17-4-17.
 */
public class ConfigPanel extends WorkingPanel {
    static {
        GUIutil.useLiquidLook();
        GUIutil.useFont();
    }

    public JLabel spendLabel = new JLabel("每月预算￥");
    public JTextField spendValue = new JTextField("0");

    public JLabel databaseLabel = new JLabel("MySql安装路径");
    public JTextField databaseValue = new JTextField();

    public JButton submit = new JButton("设置");

    private static ConfigPanel instance;
    public static ConfigPanel getInstance() {
        if(instance == null) instance = new ConfigPanel();
        return instance;
    }
    private ConfigPanel() {
        GUIutil.setColor(ColorUtil.GREY,
                spendLabel, databaseLabel);
        GUIutil.setColor(ColorUtil.BLUE,
                submit);

        this.setLayout(new BorderLayout());
        this.add(north(), BorderLayout.NORTH);
        this.add(centre(), BorderLayout.CENTER);

    }
    private JComponent north() {
        int gap = 40;

        JPanel north = new JPanel();
        north.setLayout(new GridLayout(4, 1, gap, gap));

        north.add(spendLabel);
        north.add(spendValue);

        north.add(databaseLabel);
        north.add(databaseValue);

        return north;
    }
    private JComponent centre() {
        JPanel centre = new JPanel();
        centre.add(submit);
        addListener();

        return centre;
    }

    public void addListener() {
        submit.addActionListener(new ConfigListener());
    }

    @Override
    public void updateData() {
        String budgetValue = ConfigService.getValue(ConfigService.BUDGET);
        spendValue.setText(budgetValue);

        String databasePath = ConfigService.getValue(ConfigService.MYSQL_PATH);
        databaseValue.setText(databasePath);

        spendValue.grabFocus();
    }

    public static void main(String[] args) {
        GUIutil.showPanel(getInstance());
    }
}

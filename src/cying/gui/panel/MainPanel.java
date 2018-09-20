package cying.gui.panel;

import cying.gui.listener.ToolBarListener;
import cying.util.CenterPanel;
import cying.util.GUIutil;

import javax.swing.*;
import java.awt.*;

/**
 * Created by 96428 on 2017/4/7.
 * 界面主要Panel
 * 采用BorderLayout布局管理
 * 北方是工具栏，中间为主要显示部分
 */
public class MainPanel extends WorkingPanel {
    //这段话必须被加载，应放在最前面
    static {
        GUIutil.useLiquidLook();
        GUIutil.useFont();
    }

    public JToolBar toolBar = new JToolBar();
    public JButton homeButton = new JButton();
    public JButton writeButton = new JButton();
    public JButton categoryButton = new JButton();
    public JButton reportButton = new JButton();
    public JButton configButton = new JButton();//
    public JButton backupButton = new JButton();
    public JButton recoverButton = new JButton();

    public CenterPanel workingPanel;


    private static MainPanel onlyOne;
    /**
     * 使用单例模式 没必要new太多主界面，只需要一个就够了
     * @return 一个唯一的主Panel
     */
    public static MainPanel getInstance() {

        if(onlyOne == null) onlyOne = new MainPanel();

        return onlyOne;
    }
    private MainPanel() {

        GUIutil.setImageIcon(homeButton, "home.png", "消费一览");
        GUIutil.setImageIcon(writeButton, "write.png", "记一笔");
        GUIutil.setImageIcon(categoryButton, "category.png", "消费分类");
        GUIutil.setImageIcon(reportButton, "report.png", "本月报表");
        GUIutil.setImageIcon(configButton, "config.png", "设置");
        GUIutil.setImageIcon(backupButton, "backup.png", "备份");
        GUIutil.setImageIcon(recoverButton, "recover.png", "恢复");

        toolBar.add(homeButton);
        toolBar.add(writeButton);
        toolBar.add(categoryButton);
        toolBar.add(reportButton);
        toolBar.add(configButton);
        toolBar.add(backupButton);
        toolBar.add(recoverButton);
        toolBar.setFloatable(false);

        //添加监听器
        addListener();

        workingPanel = new CenterPanel(0.8);

        setLayout(new BorderLayout());
        add(toolBar, BorderLayout.NORTH);
        add(workingPanel, BorderLayout.CENTER);

    }

    public void addListener() {
        ToolBarListener listener = new ToolBarListener();

        homeButton.addActionListener(listener);
        writeButton.addActionListener(listener);
        categoryButton.addActionListener(listener);
        reportButton.addActionListener(listener);
        configButton.addActionListener(listener);
        backupButton.addActionListener(listener);
        recoverButton.addActionListener(listener);
    }

    @Override
    public void updateData() {

    }

    public static void main(String[] args) {
        MainPanel panel = MainPanel.getInstance();
        GUIutil.showPanel(panel,1.0);
    }
}

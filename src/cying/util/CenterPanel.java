package cying.util;

import cying.gui.panel.WorkingPanel;

import javax.swing.*;
import java.awt.*;

/**
 * Created by 96428 on 2017/4/6.
 */
public class CenterPanel extends JPanel{

    private double rate; // 拉伸比例
    private JComponent component; // 显示的组件
    private boolean strech; // 是否拉伸

    public CenterPanel(double rate, boolean strech) {
        this.setLayout(null);
        this.rate = rate;
        this.strech = strech;
    }

    //默认拉伸
    public CenterPanel(double rate) {
        this(rate, true);
    }


    /**
     * updateUI会导致swing去调用repaint()方法。
     */
    public void repaint() {
        if(component != null) {
            Dimension containerSize = this.getSize();
            Dimension componentSize = component.getPreferredSize();

            if(strech) {
                component.setSize((int) (containerSize.width * rate), (int) (containerSize.height * rate));
            } else {
                component.setSize(componentSize);
            }

            component.setLocation(containerSize.width/2 - component.getWidth()/2,
                    containerSize.height/2 - component.getHeight()/2);
        }

        super.repaint();

    }

    /**
     *
     * @param toBeShowed
     * 使用show方法显示组件,show方法中的思路是：<br>
     * 先把这个容器中的组件都移出，然后把新的组件加进来，并且调用updateUI进行界面渲染。
     */
    public void show(JComponent toBeShowed) {
        this.component = toBeShowed;

        Component[] components = getComponents();
        for(Component comp : components) {
            remove(comp);
        }

        add(toBeShowed);
        if (toBeShowed instanceof WorkingPanel)
            ( (WorkingPanel) toBeShowed ).updateData();

        this.updateUI();
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setSize(200,200);
        frame.setLocationRelativeTo(null);
        CenterPanel centerPanel = new CenterPanel(0.5, false);
        frame.setContentPane(centerPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        JButton button = new JButton("aaa");
        centerPanel.show(button);
    }
}

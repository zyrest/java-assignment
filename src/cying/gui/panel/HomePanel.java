package cying.gui.panel;

import cying.page.HomePage;
import cying.service.HomeService;
import cying.util.CircleProgressBar;
import cying.util.ColorUtil;
import cying.util.GUIutil;

import javax.swing.*;
import java.awt.*;

/**
 * Created by samson on 17-4-13.
 */
public class HomePanel extends WorkingPanel {
    static {
        GUIutil.useLiquidLook();
        GUIutil.useFont();
    }

    private static HomePanel instance = new HomePanel();

    public JLabel monthSpendLabel = new JLabel("本月消费");
    public JLabel todaySpendLabel = new JLabel("今日消费");
    public JLabel avgSpendPerDayLabel = new JLabel("日均消费");
    public JLabel monthLeftLabel = new JLabel("本月剩余");
    public JLabel dayAvgAvailableLabel = new JLabel("日均可用");
    public JLabel monthLeftDayLabel = new JLabel("距离月末");

    public JLabel monthSpendValue = new JLabel();
    public JLabel todaySpendValue = new JLabel();
    public JLabel avgSpendPerDayValue = new JLabel();
    public JLabel monthAvailableValue = new JLabel();
    public JLabel dayAvgAvailableValue = new JLabel();
    public JLabel monthLeftDayValue = new JLabel();

    CircleProgressBar bar;

    /**
     *
     * @return
     *      返回实例化的Panel
     */
    public static HomePanel getInstance() {

        return instance;
    }

    private HomePanel(){
        this.setLayout(new BorderLayout());
        bar = new CircleProgressBar();
        bar.setBackgroundColor(ColorUtil.BLUE);

        GUIutil.setColor(ColorUtil.GREY,
                monthSpendLabel, todaySpendLabel,
                avgSpendPerDayLabel, monthLeftLabel,
                dayAvgAvailableLabel, monthLeftDayLabel,
                avgSpendPerDayValue, monthAvailableValue,
                dayAvgAvailableValue, monthLeftDayValue
                );
        GUIutil.setColor(ColorUtil.BLUE,
                monthSpendValue, todaySpendValue);

        monthSpendValue.setFont(new Font("微软雅黑", Font.BOLD, 23));
        todaySpendValue.setFont(new Font("微软雅黑", Font.BOLD, 23));

        this.add(centre(), BorderLayout.CENTER);
        this.add(south(), BorderLayout.SOUTH);
    }
    private JPanel centre() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        panel.add(westInCentre(), BorderLayout.WEST);
        panel.add(bar);

        return panel;
    }
    private JComponent westInCentre() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4,1));

        panel.add(monthSpendLabel);
        panel.add(monthSpendValue);
        panel.add(todaySpendLabel);
        panel.add(todaySpendValue);

        return panel;
    }
    private JPanel south() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 4));

        panel.add(avgSpendPerDayLabel);
        panel.add(monthLeftLabel);
        panel.add(dayAvgAvailableLabel);
        panel.add(monthLeftDayLabel);

        panel.add(avgSpendPerDayValue);
        panel.add(monthAvailableValue);
        panel.add(dayAvgAvailableValue);
        panel.add(monthLeftDayValue);

        return panel;
    }
    private void initData() {

    }

    @Override
    public void addListener() {

    }

    @Override
    public void updateData() {
        HomePage page = HomeService.getHomePage();
//        System.out.println(page.spendPercentage);

        monthSpendValue.setText(page.monthSpend);
        todaySpendValue.setText(page.todaySpend);
        avgSpendPerDayValue.setText(page.avgSpendPerDay);
        monthAvailableValue.setText(page.monthAvailable);
        dayAvgAvailableValue.setText(page.dayAvgAvailable);
        monthLeftDayValue.setText(page.monthLeftDay);

        bar.setProgress(page.spendPercentage);

        if (page.isOverSpend) {
            monthSpendValue.setForeground(ColorUtil.WARNING);
            todaySpendValue.setForeground(ColorUtil.WARNING);
            monthAvailableValue.setForeground(ColorUtil.WARNING);
        } else {
            monthSpendValue.setForeground(ColorUtil.BLUE);
            todaySpendValue.setForeground(ColorUtil.BLUE);
            monthAvailableValue.setForeground(ColorUtil.GREY);
        }

        bar.setForegroundColor( ColorUtil.getColorByPercentage(page.spendPercentage) );

    }

    public static void main(String[] args) {

        GUIutil.showPanel(HomePanel.instance);
    }
    
}

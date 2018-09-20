package cying.gui.panel;

import cying.entity.RecordEntity;
import cying.service.ReportService;
import cying.util.ChartUtil;
import cying.util.GUIutil;

import javax.swing.*;
import java.awt.*;

/**
 * Created by samson on 17-4-17.
 */
public class ReportPanel extends WorkingPanel {
    static {
        GUIutil.useLiquidLook();
        GUIutil.useFont();
    }


    public JLabel label = new JLabel();

    private static ReportPanel instance;
    public static ReportPanel getInstance() {
        if(instance == null) instance = new ReportPanel();
        return instance;
    }
    private ReportPanel() {
        this.setLayout(new BorderLayout());

        java.util.List<RecordEntity> records = ReportService.listMonthSpend();
        Image chart = ChartUtil.getChartImage(records, 350,250);
        ImageIcon icon = new ImageIcon(chart);
        label.setIcon(icon);

        this.add(label, BorderLayout.CENTER);
    }

    @Override
    public void addListener() {

    }

    @Override
    public void updateData() {
        java.util.List<RecordEntity> records = ReportService.listMonthSpend();
        Image chart = ChartUtil.getChartImage(records, 350, 250);
        ImageIcon icon = new ImageIcon(chart);
        label.setIcon(icon);
    }

    public static void main(String[] args) {
        GUIutil.showPanel(getInstance());
    }
}

package cying.util;

import com.objectplanet.chart.BarChart;
import com.objectplanet.chart.Chart;
import cying.entity.RecordEntity;

import java.awt.*;
import java.util.List;

/**
 * Created by 96428 on 2017/4/7.
 */
public class ChartUtil {

    private static String[] sampleLabels(List<RecordEntity> records) {
        String[] sampleLabels = new String[records.size()];

        for(int i = 0;i < sampleLabels.length;i++) {
            if(i % 5 == 0)
                 sampleLabels[i] = (i + 1) + "日";
        }

        return sampleLabels;
    }

    private static double[] sampleValues(List<RecordEntity> records) {
        double[] sampleValues = new double[records.size()];

        for(int i = 0;i < sampleValues.length;i++) {
            sampleValues[i] = records.get(i).getSpend();
        }

        return sampleValues;
    }
    /**
     *
     * @param values 多个数据
     * @return 数据中的最大值
     */
    public static int max(double ... values) {
        int max = 0;

        for(double value : values) {
            if(value > max)
                max = (int) value;
        }

        return max;
    }

    /**
     *
     * @param width 生成表的宽度
     * @param height 表的高度
     * @return      返回一个数据表
     *
     */
    public static Image getChartImage(List<RecordEntity> records, int width, int height) {
        //模拟样本数据
        double[] sampleValues = sampleValues(records);
        //样本下方文字
        String[] sampleLabels = sampleLabels(records);
        int max = max(sampleValues);

        //数据的颜色
        Color[] sampleColors = new Color[] { ColorUtil.BLUE };

        //数据表
        BarChart chart = new BarChart();

        //设置数据个数
        chart.setSampleCount(sampleValues.length);
        //图表中间背景颜色
        chart.setChartBackground(Color.white);
        //图表整体背景颜色
        chart.setBackground(ColorUtil.BACKGROUND);

        //设置各行数据
        chart.setSampleValues(0, sampleValues);
        //设置数据颜色
        chart.setSampleColors(sampleColors);
        //设置取值范围
        chart.setRange(0, max * 1.2);
        //是否显示背景线
        chart.setValueLinesOn(true);
        //设置数据字体
        chart.setFont("rangeLabelFont", new Font("Arial", Font.BOLD, 12));

        //设置底部文字
        chart.setSampleLabels(sampleLabels);
        //是否显示底部文字
        chart.setSampleLabelsOn(true);
        //底部文字放在下方
        chart.setSampleLabelStyle(Chart.BELOW);
        //下方文字的字体
        chart.setFont("sampleLabelFont", new Font("Dialog", Font.BOLD, 13));

        //是否使用图例说明
        chart.setLegendOn(true);
        //图例说明的位置
        chart.setLegendPosition(Chart.LEFT);
        //图例说明文字
        chart.setLegendLabels(new String[]{ "月消费~~" });
        //图例说明的字体
        chart.setFont("legendFont", new Font("Dialog", Font.BOLD, 13));

        //得到图像
        Image image = chart.getImage(width, height);

        return image;
    }


}

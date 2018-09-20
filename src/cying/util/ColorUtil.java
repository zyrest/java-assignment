package cying.util;

import java.awt.*;

/**
 * Created by 96428 on 2017/4/7.
 * 与颜色相关的常用工具方法
 */
public class ColorUtil {
    //定义常用颜色
    public static final Color BLUE = Color.decode("#3399FF");
    public static final Color GREY = Color.decode("#999999");
    public static final Color BACKGROUND = Color.decode("#eeeeee");
    public static final Color WARNING = Color.decode("#FF3333");

    /**
     *
     * @param percentage 已使用的百分比
     * @return 返回相应百分比对应的颜色
     *      根据已使用的金额所占百分比计算颜色，当百分比接近100时，为红色
     */
    public static Color getColorByPercentage(int percentage) {
        if(percentage > 100)
            percentage = 100;
        int r = 51;
        int g = 255;
        int b = 51;

        float rate = percentage / 100f;
        r = (int) ((255 - 51) * rate + 51);
        g = 255 - r + 51;

        Color color = new Color(r, g, b);
        return color;
    }
}

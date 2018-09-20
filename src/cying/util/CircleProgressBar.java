package cying.util;

import javax.swing.*;
import java.awt.*;

/**
 * Created by 96428 on 2017/4/7.
 */
public class CircleProgressBar extends JPanel {

    private int minProgress;
    private int maxProgress;
    private int progress;
    private String progressText;

    private Color foregroundColor;
    private Color backgroundColor;

    public CircleProgressBar() {
        minProgress = 0;
        maxProgress = 100;
    }

    /**
     *
     * @param graphics
     *      重写方法，画出一个圆形展示框
     */
    public void paint(Graphics graphics) {
        super.paint(graphics);

        Graphics2D graphics2D = (Graphics2D) graphics;
        // 开启抗锯齿
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        int x = 0;
        int y = 0;
        int width = 0;
        int height = 0;
        int fontSize = 0;

        if(getWidth() >= getHeight()) {
            x = (getWidth() - getHeight()) / 2 + 25;
            y = 25;
            width = getHeight() - 50;
            height = getHeight() - 50;
            fontSize = getWidth() / 8;
        } else {
            x = 25;
            y = (getHeight() - getWidth()) / 2 + 25;
            width = getWidth() - 50;
            height = getWidth() - 50;
            fontSize = getHeight() / 8;
        }

        //BasicStroke是JDK中提供的一个基本的画笔类,我们对他设置画笔的粗细
        graphics2D.setStroke(new BasicStroke(20.0f));
        //画出底下的整个弧线
        graphics2D.setColor(backgroundColor);
        graphics2D.drawArc(x, y, width, height, 0, 360);

        graphics2D.setColor(foregroundColor);
        graphics2D.drawArc(x, y, width, height, 90,
                -(int) (360 * ((progress * 1.0) / (maxProgress - minProgress))));

        graphics2D.setFont(new Font("黑体", Font.BOLD, fontSize));
        FontMetrics fontMetrics = graphics2D.getFontMetrics();
        int digitalWidth = fontMetrics.stringWidth(progressText);
        int digitalAscent = fontMetrics.getAscent();

        graphics2D.setColor(foregroundColor);
        graphics2D.drawString(progressText,
                getWidth() / 2 - digitalWidth / 2,
                getHeight() / 2 + digitalAscent / 2);
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        if(progress >= minProgress && progress <= maxProgress)
            this.progress = progress;
        else if(progress > maxProgress)
            this.progress = maxProgress;

        this.progressText = getProgress() + "%";

        this.repaint();
    }

    public Color getForegroundColor() {
        return foregroundColor;
    }

    public void setForegroundColor(Color foregroundColor) {
        this.foregroundColor = foregroundColor;
        this.repaint();
    }

    public Color getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
        this.repaint();
    }

}

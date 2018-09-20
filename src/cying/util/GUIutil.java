package cying.util;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import java.awt.*;
import java.io.File;
import java.util.Enumeration;

/**
 * Created by 96428 on 2017/4/6.
 */
public class GUIutil {
    private static final String imageFolder = "ref/img";

    /**
     *
     * @param field
     * @param input
     * @return
     *      验证输入框里的内容是否为空
     */
    public static boolean isEmpty(JTextField field,String input) {
        boolean flag = false;
        String text = field.getText().trim();

        if(text.length() == 0) {
            flag = true;
            JOptionPane.showMessageDialog(null, input + " 不能为空");
            field.grabFocus();
        }

        return flag;
    }

    /**
     *
     * @param field
     * @param input
     * @return
     *      验证输入框里的内容是否全为数字
     */
    public static boolean isNumber(JTextField field, String input) {
        if(isEmpty(field, input)) {
            return false;
        }

        boolean flag = false;
        String text = field.getText().trim();

        if(text.matches("^-?[1-9]\\d*$")) {
            flag = true;
        } else {
            JOptionPane.showMessageDialog(null, input +" 需要是整数");
            field.grabFocus();
        }
        return flag;
    }

    /**
     *
     * @param field
     * @param input
     * @return
     *      验证输入框里的内容是否为零
     */
    public static boolean isZero(JTextField field, String input) {
        if(!isNumber(field, input)) {
            return false;
        }

        boolean flag = false;
        String text = field.getText().trim();
        int number = Integer.parseInt(text);

        if(number == 0) {
            flag = true;
            JOptionPane.showMessageDialog(null, input + " 不能为零");
            field.grabFocus();
        }

        return flag;
    }

    /**
     *
     * @param color
     * @param components
     *      为多个组件设置前景色
     */
    public static void setColor(Color color, JComponent ... components) {
        for(JComponent component : components) {
            component.setForeground(color);
        }
    }

    /**
     *
     * @param button
     * @param fileName
     * @param tip
     *      为一个按钮添加图标和提示信息
     */
    public static void setImageIcon(JButton button, String fileName, String tip) {
        ImageIcon icon = new ImageIcon( new File(imageFolder, fileName).getAbsolutePath() );

        button.setIcon(icon);
        button.setPreferredSize(new Dimension(80, 81));
        button.setToolTipText(tip); //当鼠标滑过时 显示信息

        button.setVerticalTextPosition(JButton.BOTTOM);
        button.setHorizontalTextPosition(JButton.CENTER);

        button.setText(tip); //设置信息
    }

    /**
     * 水晶皮肤。。JAVA中比较好看的一种皮肤
     */
    public static void useLiquidLook() {
        try {
            javax.swing.UIManager.setLookAndFeel("com.birosoft.liquid.LiquidLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @param panel  需要展示的panel
     * @param strech   中间位置的所占比例
     *      将一个Panel居中展示出来
     */
    public static void showPanel(JPanel panel, double strech) {
        GUIutil.useLiquidLook();
        JFrame frame = new JFrame();
        frame.setSize(500, 500);
        frame.setLocationRelativeTo(null);

        CenterPanel centerPanel = new CenterPanel(strech);
        frame.setContentPane(centerPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        centerPanel.show(panel);
    }

    /**
     *
     * @param panel 需要展示的panel
     *             居中展示 占比为85%
     */
    public static void showPanel(JPanel panel) {
        showPanel(panel, 0.85);
    }

    public static void main(String[] args) {
        JPanel panel = new JPanel();
        panel.setSize(500, 500);
        JLabel label = new JLabel("there");
        panel.add(label);
        GUIutil.showPanel(panel);
    }

    public static void useFont() {
        Font vFont = new Font("alias", Font.PLAIN, 15);
        FontUIResource fontRes = new FontUIResource(vFont);

        for (Enumeration<Object> keys = UIManager.getDefaults().keys();
             keys.hasMoreElements(); ) {

            Object key = keys.nextElement();
            Object value = UIManager.get(key);

            if (value instanceof FontUIResource) {
                UIManager.put(key, fontRes);
            }

        }
//
//        UIManager.put("ToolTip.font", vFont);
//        UIManager.put("Table.font", vFont);
//        UIManager.put("TableHeader.font", vFont);
//        UIManager.put("TextField.font", vFont);
//        UIManager.put("ComboBox.font", vFont);
//        UIManager.put("TextField.font", vFont);
//        UIManager.put("PasswordField.font", vFont);
//        UIManager.put("TextArea.font", vFont);
//        UIManager.put("TextPane.font", vFont);
//        UIManager.put("EditorPane.font", vFont);
//        UIManager.put("FormattedTextField.font", vFont);
//        UIManager.put("Button.font", vFont);
//        UIManager.put("CheckBox.font", vFont);
//        UIManager.put("RadioButton.font", vFont);
//        UIManager.put("ToggleButton.font", vFont);
//        UIManager.put("ProgressBar.font", vFont);
//        UIManager.put("DesktopIcon.font", vFont);
//        UIManager.put("TitledBorder.font", vFont);
//        UIManager.put("Label.font", vFont);
//        UIManager.put("List.font", vFont);
//        UIManager.put("TabbedPane.font", vFont);
//        UIManager.put("MenuBar.font", vFont);
//        UIManager.put("Menu.font", vFont);
//        UIManager.put("MenuItem.font", vFont);
//        UIManager.put("PopupMenu.font", vFont);
//        UIManager.put("CheckBoxMenuItem.font", vFont);
//        UIManager.put("RadioButtonMenuItem.font", vFont);
//        UIManager.put("Spinner.font", vFont);
//        UIManager.put("Tree.font", vFont);
//        UIManager.put("ToolBar.font", vFont);
//        UIManager.put("OptionPane.messageFont", vFont);
//        UIManager.put("OptionPane.buttonFont", vFont);
    }
}

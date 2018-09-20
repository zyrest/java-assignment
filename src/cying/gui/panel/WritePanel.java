package cying.gui.panel;

import cying.entity.CategoryEntity;
import cying.gui.listener.WriteListener;
import cying.gui.model.CategoryComboBoxModel;
import cying.service.CategoryService;
import cying.util.ColorUtil;
import cying.util.GUIutil;
import org.jdesktop.swingx.JXDatePicker;

import javax.swing.*;
import java.awt.*;
import java.util.Date;

/**
 * Created by samson on 17-4-17.
 */
public class WritePanel extends WorkingPanel {
    static {
        GUIutil.useLiquidLook();
        GUIutil.useFont();
    }

    JLabel writeLabel = new JLabel("花费￥");
    JLabel categoryLabel = new JLabel("分类");
    JLabel commentLabel = new JLabel("备注");
    JLabel dateLabel = new JLabel("日期");

    public JTextField writeText = new JTextField("0");

    public CategoryComboBoxModel categoryModel = new CategoryComboBoxModel();
    public JComboBox<CategoryEntity> categoryBox = new JComboBox<>(categoryModel);

    public JTextField commentText = new JTextField();
    public JXDatePicker datePicker = new JXDatePicker(new Date());

    public JButton submit = new JButton("记一笔");


    private static WritePanel instance;
    public static WritePanel getInstance() {
        if(instance == null) instance = new WritePanel();
        return instance;
    }
    private WritePanel(){
        GUIutil.setColor(ColorUtil.GREY,
                writeLabel, categoryLabel, commentLabel, dateLabel);
        GUIutil.setColor(ColorUtil.BLUE, submit);

        this.setLayout(new BorderLayout());
        this.add(north(), BorderLayout.NORTH);
        this.add(centre(), BorderLayout.CENTER);

        addListener();

    }
    private JComponent north() {
        int gap = 40;

        JPanel north = new JPanel();
        north.setLayout(new GridLayout(4, 2, gap, gap));

        north.add(writeLabel);
        north.add(writeText);

        north.add(categoryLabel);
        north.add(categoryBox);

        north.add(dateLabel);
        north.add(datePicker);

        north.add(commentLabel);
        north.add(commentText);

        return north;
    }
    private JComponent centre() {
        JPanel centre = new JPanel();
        centre.add(submit);
        return centre;
    }

    @Override
    public void addListener() {
        WriteListener listener = new WriteListener();

        submit.addActionListener(listener);
    }

    @Override
    public void updateData() {
        categoryModel.categories = CategoryService.list();
        categoryBox.updateUI();

        resetInput();

        writeText.grabFocus();
    }

    private void resetInput() {
        writeText.setText("0");
        commentText.setText("");

        if (! categoryModel.categories.isEmpty())
            categoryBox.setSelectedIndex(0);

        datePicker.setDate(new Date());

    }

    public static void main(String[] args) {
        GUIutil.showPanel(getInstance(), 1.0);
    }
}

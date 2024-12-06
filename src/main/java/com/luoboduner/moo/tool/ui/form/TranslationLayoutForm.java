package com.luoboduner.moo.tool.ui.form;

import com.formdev.flatlaf.extras.FlatSVGIcon;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import com.luoboduner.moo.tool.util.UndoUtil;
import com.luoboduner.moo.tool.util.translator.Translator;
import com.luoboduner.moo.tool.util.translator.TranslatorFactory;
import lombok.Getter;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.util.concurrent.atomic.AtomicInteger;

@Getter
public class TranslationLayoutForm {
    private JComboBox comboBox1;
    private JButton exchangeButton;
    private JComboBox comboBox2;
    private JTextArea textArea1;
    private JTextArea textArea2;
    private JPanel mainLayoutPanel;
    private JSplitPane splitPane;

    private static AtomicInteger changeCount = new AtomicInteger(0);

    public TranslationLayoutForm() {
        exchangeButton.setIcon(new FlatSVGIcon("icon/exchange.svg"));

        UndoUtil.register(this);

        addListeners();
    }

    public void addListeners() {
        exchangeButton.addActionListener(e -> {
            String from = comboBox1.getSelectedItem().toString();
            String to = comboBox2.getSelectedItem().toString();
            if ("自动检测".equals(from)) {
                if ("中文（简体）".equals(to)) {
                    comboBox1.setSelectedItem("中文（简体）");
                    comboBox2.setSelectedItem("英语");
                } else {
                    comboBox1.setSelectedItem("中文（简体）");
                    comboBox2.setSelectedItem("英语");
                }
            } else {
                comboBox1.setSelectedItem(to);
                comboBox2.setSelectedItem(from);
            }

            translateControl();
        });

        textArea1.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                translateControl();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                translateControl();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
            }
        });
    }

    public void translateControl() {
        Thread thread = new Thread(() -> {
            changeCount.incrementAndGet();
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            changeCount.decrementAndGet();
            if (changeCount.get() == 0) {
                translate();
            }
        });
        thread.start();
    }

    private void translate() {
        String sourceLanguage = comboBox1.getSelectedItem().toString();
        String targetLanguage = comboBox2.getSelectedItem().toString();
        String text = textArea1.getText();

        TranslatorFactory translatorFactory = new TranslatorFactory();
        String result = translatorFactory.getTranslator(TranslatorFactory.TranslatorType.GOOGLE).translate(text, Translator.languageNameToCodeMap.get(sourceLanguage), Translator.languageNameToCodeMap.get(targetLanguage));

        textArea2.setText(result);
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        mainLayoutPanel = new JPanel();
        mainLayoutPanel.setLayout(new GridLayoutManager(2, 1, new Insets(0, 0, 0, 0), -1, -1));
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayoutManager(1, 4, new Insets(0, 0, 0, 0), -1, -1));
        mainLayoutPanel.add(panel1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        comboBox1 = new JComboBox();
        final DefaultComboBoxModel defaultComboBoxModel1 = new DefaultComboBoxModel();
        defaultComboBoxModel1.addElement("自动检测");
        defaultComboBoxModel1.addElement("中文（简体）");
        defaultComboBoxModel1.addElement("英语");
        comboBox1.setModel(defaultComboBoxModel1);
        panel1.add(comboBox1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer1 = new Spacer();
        panel1.add(spacer1, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        exchangeButton = new JButton();
        exchangeButton.setText("");
        panel1.add(exchangeButton, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        comboBox2 = new JComboBox();
        final DefaultComboBoxModel defaultComboBoxModel2 = new DefaultComboBoxModel();
        defaultComboBoxModel2.addElement("中文（简体）");
        defaultComboBoxModel2.addElement("英语");
        comboBox2.setModel(defaultComboBoxModel2);
        panel1.add(comboBox2, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        splitPane = new JSplitPane();
        splitPane.setContinuousLayout(true);
        splitPane.setDividerLocation(224);
        mainLayoutPanel.add(splitPane, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(200, 200), null, 0, false));
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        splitPane.setLeftComponent(panel2);
        final JScrollPane scrollPane1 = new JScrollPane();
        panel2.add(scrollPane1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        textArea1 = new JTextArea();
        scrollPane1.setViewportView(textArea1);
        final JPanel panel3 = new JPanel();
        panel3.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        splitPane.setRightComponent(panel3);
        final JScrollPane scrollPane2 = new JScrollPane();
        panel3.add(scrollPane2, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        textArea2 = new JTextArea();
        scrollPane2.setViewportView(textArea2);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return mainLayoutPanel;
    }

}

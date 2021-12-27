package com.test.burp.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;

public class C2ui extends JFrame{
    private JTextArea resArea;
    private JButton button;

    public C2ui() {
        super("C2 profile generator");
        JPanel codePanel = new JPanel(new GridLayout());

        // 新建的GUI框存放C2 profile文件内容
        resArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(resArea);
        resArea.setEditable(false);

        this.add(codePanel, BorderLayout.CENTER);
        codePanel.add(scrollPane);

        // 新建Buttom用于Copy C2 profile
        JPanel buttonPanel = new JPanel(new FlowLayout());
        button = new JButton("Copy Text");
        buttonPanel.add(button);
        this.add(buttonPanel, BorderLayout.PAGE_END);
        button.addActionListener(e1 -> {
            Toolkit toolkit = Toolkit.getDefaultToolkit();
            Clipboard clipboard = toolkit.getSystemClipboard();
            StringSelection CodeToCopy = new StringSelection(this.getFile());
            clipboard.setContents(CodeToCopy, CodeToCopy);
        });

        // 设置GUI框样式
        this.setSize(630,530);
        this.setVisible(false);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    public void setFile(String str){
        this.resArea.setText(str);
    }
    public String getFile(){
        return resArea.getText();
    }
}

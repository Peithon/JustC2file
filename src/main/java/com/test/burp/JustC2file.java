package com.test.burp;

import burp.*;
import com.test.burp.ui.C2ui;

import javax.swing.*;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
/**
 * @program: JustC2file
 * @author: Peithon
 * @create: 2022-01-08 09:09
 **/
public class JustC2file implements IBurpExtender, IContextMenuFactory
{
    private static final String name = "JustC2file";
    private IBurpExtenderCallbacks callbacks;

    @Override
    public void registerExtenderCallbacks(IBurpExtenderCallbacks callbacks)
    {
        PrintWriter stdout = new PrintWriter(callbacks.getStdout(), true);
        PrintWriter stderr = new PrintWriter(callbacks.getStderr(), true);
        this.callbacks = callbacks;
        callbacks.setExtensionName(name);
        stdout.println("[INFO] ------------------------------------------------------------------------\n" +
                "[INFO] C2 profile generator\n" +
                "[INFO] ------------------------------------------------------------------------\n" +
                "[INFO] 用法: 同时选中至少三个请求(GET/POST)，且必须GET和POST同时存在，然后右键点击该插件。\n" +
                "[INFO] 提示: 想要隐蔽性好的话，Get请求可以选择JQuery;\n" +
                "[INFO] ------------------------------------------------------------------------\n");
        stderr.println("no errors");
        callbacks.registerContextMenuFactory(this);

    }
    //自定义上下文菜单
    @Override
    public List<JMenuItem> createMenuItems(final IContextMenuInvocation invocation) {
        List<JMenuItem> listMenuItems = new ArrayList<JMenuItem>();
        //子菜单
        JMenuItem menuItem = new JMenuItem("Generate C2 profile");
        //父级菜单
        //JMenu jMenu = new JMenu("Generate C2 profile");
        //jMenu.add(menuItem);
        listMenuItems.add(menuItem);

        menuItem.addActionListener(e -> {
           //  生成C2 profile的GUI框
            C2ui frame = new C2ui();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
            frame.setFile(new Generator(invocation,this.callbacks).getProfile());
        });
        return listMenuItems;
    }


}
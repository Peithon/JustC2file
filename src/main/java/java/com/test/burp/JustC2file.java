package java.com.test.burp;

import burp.*;
import javax.swing.*;
import java.com.test.burp.ui.C2ui;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class JustC2file implements IBurpExtender, IContextMenuFactory
{
    private static final String name = "JustC2file";
    private PrintWriter stdout;
    private IExtensionHelpers helpers;
    private PrintWriter stderr;
    @Override
    public void registerExtenderCallbacks(IBurpExtenderCallbacks callbacks)
    {
        stdout = new PrintWriter(callbacks.getStdout(), true);
        stderr = new PrintWriter(callbacks.getStderr(), true);
        this.helpers = callbacks.getHelpers();
        callbacks.setExtensionName(name);
        stdout.println("[INFO] ------------------------------------------------------------------------\n" +
                "[INFO] C2 profile generator\n" +
                "[INFO] ------------------------------------------------------------------------\n" +
                "[INFO] 用法: 同时选中一个Get请求和一个Post请求，然后右键点击该插件。\n" +
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
            frame.setFile(new Generator(invocation,this.helpers).getExampleFile());
//            Map<String, Object> dataMap = new HashMap<String, Object>();
//            new Data(dataMap).putData();
//            // getSelectedMessages()函数用于获取当前显示的或用户选中的HTTP请求/响应的细节
//            // analyzeRequest()函数用于分析HTTP请求信息以便获取到多个键的值
//            IHttpRequestResponse[] messages = invocation.getSelectedMessages();
//            HttpData idata = new HttpData(dataMap);
//            for (IHttpRequestResponse message : messages) {
//                byte[] req = message.getRequest();
//                IRequestInfo analyzedRequest = helpers.analyzeRequest(req);
//                String method = analyzedRequest.getMethod();
//                /*****************获取header**********************/
//                List<String> headers = analyzedRequest.getHeaders();
//                //处理响应包 getResponse获得的是字节序列
//                IResponseInfo analyzeResponse = helpers.analyzeResponse(message.getResponse());
//                List<String> iheaders = analyzeResponse.getHeaders();
//                // 循环获取参数，判断类型，进行加密处理后，再构造新的参数，合并到新的请求包中。
//                if (method.equals("POST")) {
//                    System.out.println("hello POST");
//                    idata.putPostRequestHeaders(headers);
//                    idata.putPostResponseHeaders(iheaders);
//                } else if (method.equals("GET")) {
//                    System.out.println("hello GET");
//                    idata.putGetRequestHeaders(headers);
//                    idata.putGetResponseHeaders(iheaders);
//                }
//
//            }
        });
        return listMenuItems;
    }


}
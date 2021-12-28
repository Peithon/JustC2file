package java.com.test.burp;

import burp.*;
import com.test.burp.persiancat.Data;
import com.test.burp.persiancat.HttpData;
import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.*;
import java.util.*;

public class Generator {
    private IContextMenuInvocation invocation;
    private IExtensionHelpers helpers;
    private Configuration cfg;
    private String example;
    private String method;

    public Generator(IContextMenuInvocation invocation, IExtensionHelpers helpers) {
        this.invocation = invocation;
        this.helpers = helpers;
        // step1 创建freeMarker配置实例
        this.cfg = new Configuration();
    }

    public String getExampleFile(){
        try {
            // step2 获取模版路径
            cfg.setClassForTemplateLoading(this.getClass(),"/templates");
            // step3 创建数据模型
            Map<String, Object> dataMap = new HashMap<String, Object>();
            new Data(dataMap).putData();
            // getSelectedMessages()函数用于获取当前显示的或用户选中的HTTP请求/响应的细节
            // analyzeRequest()函数用于分析HTTP请求信息以便获取到多个键的值
            IHttpRequestResponse[] messages = invocation.getSelectedMessages();
            HttpData idata = new HttpData(dataMap);
            for (IHttpRequestResponse message : messages) {
                byte[] req = message.getRequest();
                IRequestInfo analyzedRequest = helpers.analyzeRequest(req);
                method = analyzedRequest.getMethod();
                /*****************获取header**********************/
                List<String> headers = analyzedRequest.getHeaders();
                //处理响应包 getResponse获得的是字节序列
                IResponseInfo analyzeResponse = helpers.analyzeResponse(message.getResponse());
                List<String> iheaders = analyzeResponse.getHeaders();
                // 循环获取参数，判断类型，进行加密处理后，再构造新的参数，合并到新的请求包中。
                if (method.equals("POST")) {
                    idata.putPostRequestHeaders(headers);
                    idata.putPostResponseHeaders(iheaders);
                } else if (method.equals("GET")) {
                    idata.putGetRequestHeaders(headers);
                    idata.putGetResponseHeaders(iheaders);
                }

            }
            // step4 加载模版文件
            Template template = cfg.getTemplate("c2profile_template.ftl");
            // step5 生成数据
            StringWriter strWriter = new StringWriter();
            template.process(dataMap, strWriter);
            example = strWriter.toString();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return example;
    }
}

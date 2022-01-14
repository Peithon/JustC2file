package com.test.burp;

import burp.*;
import com.test.burp.dao.impl.AnalyzeInfo;
import com.test.burp.service.*;
import com.test.burp.service.impl.*;
import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.*;

/**
 * @program: JustC2file
 * @author: Peithon
 * @create: 2022-01-08 09:09
 **/
public class Generator {
    private PersiancatService persiancatService = new PersiancatServiceImpl();
    private HttpsCertificateService httpsCertificateService = new HttpsCertificateServiceImpl();
    private PostExService postExService = new PostExServiceImpl();
    private HttpStagerService httpStagerService = new HttpStagerServiceImpl();
    private HttpGetBeaconService httpGetBeaconService = new HttpGetBeaconServiceImpl();
    private HttpPostBeaconService httpPostBeaconService = new HttpPostBeaconServiceImpl();
    private DnsBeaconService dnsBeaconService = new DnsBeaconServiceImpl();
    private AnalyzeInfo analyzeInfo = new AnalyzeInfo();
    private IContextMenuInvocation invocation;
    private IExtensionHelpers helpers;
    private IBurpExtenderCallbacks callbacks;
    private String example;

    public Generator(IContextMenuInvocation invocation, IBurpExtenderCallbacks callbacks) {
        this.invocation = invocation;
        this.helpers = callbacks.getHelpers();
        this.callbacks = callbacks;
    }

    public String getProfile(){
        Map<String, Object> dataMap = new HashMap<String, Object>();
        // getSelectedMessages()函数用于获取当前显示的或用户选中的HTTP请求/响应的细节
        // analyzeRequest()函数用于分析HTTP请求信息以便获取到多个键的值
        dataMap.putAll(persiancatService.putdataPersiancat());
        dataMap.putAll(postExService.putdataPostEx());
        dataMap.putAll(httpsCertificateService.putdataHttpsCertificate());
        IHttpRequestResponse[] messages = invocation.getSelectedMessages();
        IHttpRequestResponse stagermessages = getHttpStager(messages);
        httpStagerService.dataHttpStager(callbacks,stagermessages);
        PrintWriter stderr = new PrintWriter(callbacks.getStderr(), true);
        for (IHttpRequestResponse message : messages) {
            stderr.println("IHttpRequestResponse message: "+message);
            IRequestInfo analyzedRequest = helpers.analyzeRequest(message.getRequest());
            if(!message.equals(stagermessages)) {
                if (analyzedRequest.getMethod().equals("POST")) {
                    httpPostBeaconService.dataHttpPostBeacon(callbacks,message);
                } else if (analyzedRequest.getMethod().equals("GET")) {
                    httpGetBeaconService.dataHttpGetBeacon(callbacks,message);
                }
            }
        }
        stderr.println("[end-message] ------------------------------------------------------------------------");
        dataMap.putAll(httpStagerService.putdataHttpStager());
        dataMap.putAll(httpGetBeaconService.putdataHttpGetBeacon());
        dataMap.putAll(httpPostBeaconService.putdataHttpPostBeacon());
        dataMap.putAll(dnsBeaconService.putdataDnsBeacon());
        return getExampleFile(dataMap);
    }

    public String getExampleFile(Map<String, Object> dataMap){
        try {
            // step1 创建freeMarker配置实例
            Configuration cfg = new Configuration();
            // step2 获取模版路径
            cfg.setClassForTemplateLoading(this.getClass(),"/templates");
            // step3 创建数据模型
            //Map<String, Object> dataMap = new HashMap<String, Object>();
            // step4 加载模版文件
            Template template = cfg.getTemplate("c2profile_template4.2.ftl");
            // step5 生成数据
            StringWriter strWriter = new StringWriter();
            template.process(dataMap, strWriter);
            example = strWriter.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return example;
    }

    public IHttpRequestResponse getHttpStager(IHttpRequestResponse[] messages){
        List<IHttpRequestResponse> getlist = new ArrayList<>();
        List<IHttpRequestResponse> postlist = new ArrayList<>();
        IHttpRequestResponse returnIHttp = null;
        for (IHttpRequestResponse message : messages) {
            IRequestInfo analyzedRequest = helpers.analyzeRequest(message.getRequest());
            if (analyzedRequest.getMethod().equals("POST")) {
                postlist.add(message);
            } else if (analyzedRequest.getMethod().equals("GET")) {
                getlist.add(message);
            }
        }
        if (getlist.size() >= postlist.size()){
            for (IHttpRequestResponse iHttpRequestResponse : getlist) {
                if (analyzeInfo.isExistResponseBody(callbacks, iHttpRequestResponse)) {
                    returnIHttp = iHttpRequestResponse;
                    break;
                }
            }
            if(returnIHttp == null){
                returnIHttp = getlist.get(new Random().nextInt(getlist.size()));
            }
        }else {
            for (IHttpRequestResponse iHttpRequestResponse : postlist) {
                if (analyzeInfo.isExistResponseBody(callbacks, iHttpRequestResponse)) {
                    returnIHttp = iHttpRequestResponse;
                    break;
                }
            }
            if(returnIHttp == null){
                returnIHttp = postlist.get(new Random().nextInt(postlist.size()));
            }
        }
        return returnIHttp;
    }
}

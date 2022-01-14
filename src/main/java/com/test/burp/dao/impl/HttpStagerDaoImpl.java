package com.test.burp.dao.impl;

import burp.IBurpExtenderCallbacks;
import burp.IHttpRequestResponse;
import burp.IRequestInfo;
import burp.IResponseInfo;
import com.test.burp.dao.HttpStagerDao;
import com.test.burp.model.HttpStager;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: JustC2file
 * @author: Peithon
 * @github: https://github.com/Peithon/JustC2file
 * @create: 2022-01-11 14:55
 **/
public class HttpStagerDaoImpl implements HttpStagerDao {
    //需要多个stager时换成List
    //List<HttpStager> list = new ArrayList<HttpStager>();
    private HttpStager httpStager = new HttpStager();
    private LoadFile file = new LoadFile();
    private AnalyzeInfo analyzeInfo = new AnalyzeInfo();
    private Map<String, Object> dataMap = new HashMap<String, Object>();
    @Override
    public HttpStager dataHttpStager(IBurpExtenderCallbacks callbacks, IHttpRequestResponse message) {

        IResponseInfo analyzeResponse = callbacks.getHelpers().analyzeResponse(message.getResponse());
        List<String> iheaders = analyzeResponse.getHeaders();
        List<String> listihead = analyzeInfo.formatHeaders(analyzeInfo.lookHeaders(iheaders));
        httpStager.setServer_header1(listihead.get(0));
        httpStager.setServer_header2(listihead.get(1));
        httpStager.setServer_header3(listihead.get(2));
        httpStager.setServer_header4(listihead.get(3));
        httpStager.setServer_Content_Type(analyzeInfo.lookContentType(iheaders));
        httpStager.setServer_Server(analyzeInfo.lookServer(iheaders));
       // httpStager.setOutput_encode(analyzeInfo.getEncode());
        if(analyzeInfo.isExistResponseBody(callbacks,message)){
            String resp = new String(message.getResponse());
            int bodyOffset = analyzeResponse.getBodyOffset();
            List<String> list = analyzeInfo.analyzeBody(resp.substring(bodyOffset));
            httpStager.setOutput_prepend(list.get(1));
            httpStager.setOutput_prepend2(list.get(0));
            httpStager.setOutput_append(list.get(2));
        }else{
            httpStager.setOutput_prepend("");
            httpStager.setOutput_prepend2("");
            httpStager.setOutput_append("");
        }
        byte[] req = message.getRequest();
        IRequestInfo analyzedRequest = callbacks.getHelpers().analyzeRequest(req);
        List<String> headers = analyzedRequest.getHeaders();
        httpStager.setClient_Accept(lookAccept(headers));
        try {
            String stager_uri = lookStager_uri();
            if(stager_uri.split("/").length < 2){
                httpStager.setUri_x86(stager_uri.split("\\s+")[0]);
                httpStager.setUri_x64(stager_uri.split("\\s+")[1]);
            }else {
                httpStager.setUri_x86("/rp"+lookUri(headers));
                httpStager.setUri_x64("/s"+lookUri(headers));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return httpStager;
    }

    @Override
    public Map<String, Object> putdataHttpStager() {
        dataMap.put("stager_uri_x86",httpStager.getUri_x86());
        dataMap.put("stager_uri_x64",httpStager.getUri_x64());
        dataMap.put("stager_server_ContentType",httpStager.getServer_Content_Type());
        dataMap.put("stager_server_Server",httpStager.getServer_Server());
        dataMap.put("stager_server_header1",httpStager.getServer_header1());
        dataMap.put("stager_server_header2",httpStager.getServer_header2());
        dataMap.put("stager_server_header3",httpStager.getServer_header3());
        dataMap.put("stager_server_header4",httpStager.getServer_header4());
        dataMap.put("stager_client_Accept",httpStager.getClient_Accept());
        dataMap.put("stager_output_prepend",httpStager.getOutput_prepend());
        dataMap.put("stager_output_append",httpStager.getOutput_append());
       // dataMap.put("stager_output_encode",httpStager.getOutput_encode());
        dataMap.put("stager_output_prepend2",httpStager.getOutput_prepend2());
        return dataMap;
    }

    public String lookStager_uri() throws IOException {
        return file.readLine("stager_uri");
    }

    public String lookUri(List<String> headers){
        String uri = "";
        for (String header : headers) {
            if (header.startsWith("GET")) {
                uri = header.split("\\s+")[1].split("[?]")[0];
                break;
            }else if(header.startsWith("POST")){
                uri = header.split("\\s+")[1].split("[?]")[0];
                break;
            }
        }
        return uri;
    }

    public String lookAccept(List<String> headers){
        String accept = "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8";
        for (String header : headers) {
            if (header.startsWith("Accept:")) {
                accept = header.split("[:]\\s+")[1].trim();
                break;
            }
        }
        return accept;
    }
}

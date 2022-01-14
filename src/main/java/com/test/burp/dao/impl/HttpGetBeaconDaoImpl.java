package com.test.burp.dao.impl;

import burp.IBurpExtenderCallbacks;
import burp.IHttpRequestResponse;
import burp.IRequestInfo;
import burp.IResponseInfo;
import com.test.burp.dao.HttpGetBeaconDao;
import com.test.burp.model.HttpGetBeacon;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * @program: JustC2file
 * @author: Peithon
 * @create: 2022-01-08 09:09
 **/
public class HttpGetBeaconDaoImpl implements HttpGetBeaconDao {
    private HttpGetBeacon httpGetBeacon = new HttpGetBeacon();
    private Map<String, Object> dataMap = new HashMap<String, Object>();
    private AnalyzeInfo analyzeInfo = new AnalyzeInfo();
    @Override
    public HttpGetBeacon dataHttpGetBeacon(IBurpExtenderCallbacks callbacks, IHttpRequestResponse message) {
        byte[] req = message.getRequest();
        IRequestInfo analyzedRequest = callbacks.getHelpers().analyzeRequest(req);
        List<String> headers = analyzedRequest.getHeaders();
        httpGetBeacon.setUri(analyzeInfo.formatUri(lookUri(headers)));
        httpGetBeacon.setHost(analyzeInfo.lookHost(headers));
        List<String> listhead = analyzeInfo.formatHeaders(analyzeInfo.lookHeaders(headers));
        httpGetBeacon.setGet_client_header1(listhead.get(0));
        httpGetBeacon.setGet_client_header2(listhead.get(1));
        httpGetBeacon.setGet_client_header3(listhead.get(2));
        httpGetBeacon.setGet_client_header4(listhead.get(3));
       // httpGetBeacon.setGet_metadata_encode(analyzeInfo.getEncode());
        IResponseInfo analyzeResponse = callbacks.getHelpers().analyzeResponse(message.getResponse());
        List<String> iheaders = analyzeResponse.getHeaders();
        List<String> listihead = analyzeInfo.formatHeaders(analyzeInfo.lookHeaders(iheaders));
        httpGetBeacon.setGet_server_header1(listihead.get(0));
        httpGetBeacon.setGet_server_header2(listihead.get(1));
        httpGetBeacon.setGet_server_header3(listihead.get(2));
        httpGetBeacon.setGet_server_header4(listihead.get(3));
        //httpGetBeacon.setGet_server_header(analyzeInfo.formatHeaders(analyzeInfo.lookHeaders(iheaders)));
        httpGetBeacon.setGet_server_contentType(analyzeInfo.lookContentType(iheaders));
        httpGetBeacon.setGet_server_server(analyzeInfo.lookServer(iheaders));
        httpGetBeacon.setGet_server_encode(analyzeInfo.getEncode());
        if(analyzeInfo.isExistResponseBody(callbacks,message)) {
            String resp = new String(message.getResponse());
            int bodyOffset = analyzeResponse.getBodyOffset();
            List<String> list = analyzeInfo.analyzeBody(resp.substring(bodyOffset));
            httpGetBeacon.setGet_server_prepend(list.get(1));
            httpGetBeacon.setGet_server_prepend2(list.get(0));
            httpGetBeacon.setGet_server_append(list.get(2));
        }else{
            httpGetBeacon.setGet_server_prepend("");
            httpGetBeacon.setGet_server_prepend2("");
            httpGetBeacon.setGet_server_append("");
        }
        return httpGetBeacon;
    }

    @Override
    public Map<String, Object> putdataHttpGetBeacon() {
        dataMap.put("http_get_uri", httpGetBeacon.getUri());
        dataMap.put("get_client_header1",httpGetBeacon.getGet_client_header1());
        dataMap.put("get_client_header2",httpGetBeacon.getGet_client_header2());
        dataMap.put("get_client_header3",httpGetBeacon.getGet_client_header3());
        dataMap.put("get_client_header4",httpGetBeacon.getGet_client_header4());
        dataMap.put("http_get_host",httpGetBeacon.getHost());
        dataMap.put("http_getReq_Server",httpGetBeacon.getGet_server_server());
        dataMap.put("http_getReq_ContentType",httpGetBeacon.getGet_server_contentType());
        dataMap.put("get_server_header1",httpGetBeacon.getGet_server_header1());
        dataMap.put("get_server_header2",httpGetBeacon.getGet_server_header2());
        dataMap.put("get_server_header3",httpGetBeacon.getGet_server_header3());
        dataMap.put("get_server_header4",httpGetBeacon.getGet_server_header4());
        //dataMap.put("get_metadata_encode",httpGetBeacon.getGet_metadata_encode());
        dataMap.put("get_server_encode",httpGetBeacon.getGet_server_encode());
        dataMap.put("get_server_prepend",httpGetBeacon.getGet_server_prepend());
        dataMap.put("get_server_prepend2",httpGetBeacon.getGet_server_prepend2());
        dataMap.put("get_server_append",httpGetBeacon.getGet_server_append());
        return dataMap;
    }

    public String lookUri(List<String> headers){
        String uri = "";
        for (String header : headers) {
            if (header.startsWith("GET")) {
                uri = header.split("\\s+")[1].split("[?]")[0];
            }
        }
        return uri;
    }


}

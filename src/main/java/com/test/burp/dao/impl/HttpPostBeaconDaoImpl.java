package com.test.burp.dao.impl;

import burp.IBurpExtenderCallbacks;
import burp.IHttpRequestResponse;
import burp.IRequestInfo;
import burp.IResponseInfo;
import com.test.burp.dao.HttpPostBeaconDao;
import com.test.burp.model.HttpPostBeacon;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @program: JustC2file
 * @author: Peithon
 * @github: https://github.com/Peithon/JustC2file
 * @create: 2022-01-10 15:44
 **/
public class HttpPostBeaconDaoImpl implements HttpPostBeaconDao {
    private HttpPostBeacon httpPostBeacon = new HttpPostBeacon();
    private Map<String, Object> dataMap = new HashMap<String, Object>();
    private AnalyzeInfo analyzeInfo = new AnalyzeInfo();
    private LoadFile file = new LoadFile();
    @Override
    public HttpPostBeacon dataHttpPostBeacon(IBurpExtenderCallbacks callbacks, IHttpRequestResponse message) {
        byte[] req = message.getRequest();
        IRequestInfo analyzedRequest = callbacks.getHelpers().analyzeRequest(req);
        List<String> headers = analyzedRequest.getHeaders();
        List<String> listhead = analyzeInfo.formatHeaders(analyzeInfo.lookHeaders(headers));
        httpPostBeacon.setPost_client_header1(listhead.get(0));
        httpPostBeacon.setPost_client_header2(listhead.get(1));
        httpPostBeacon.setPost_client_header3(listhead.get(2));
        httpPostBeacon.setPost_client_header4(listhead.get(3));
        httpPostBeacon.setHttp_post_uri(analyzeInfo.formatUri(lookUri(headers)));
        httpPostBeacon.setHttp_post_host(analyzeInfo.lookHost(headers));
        httpPostBeacon.setPost_client_content(analyzeInfo.lookContentType(headers));
        List<String> param = lookParameter();
        httpPostBeacon.setPost_client_parameter1(param.get(0));
        httpPostBeacon.setPost_client_parameter2(param.get(1));
        httpPostBeacon.setPost_client_parameter3(param.get(2));
        //httpPostBeacon.setPost_client_encode(analyzeInfo.getEncode());
        IResponseInfo analyzeResponse = callbacks.getHelpers().analyzeResponse(message.getResponse());
        List<String> iheaders = analyzeResponse.getHeaders();
        List<String> listihead = analyzeInfo.formatHeaders(analyzeInfo.lookHeaders(iheaders));
        httpPostBeacon.setPost_server_header1(listihead.get(0));
        httpPostBeacon.setPost_server_header2(listihead.get(1));
        httpPostBeacon.setPost_server_header3(listihead.get(2));
        httpPostBeacon.setPost_server_header4(listihead.get(3));
        httpPostBeacon.setPost_server_server(analyzeInfo.lookServer(iheaders));
        httpPostBeacon.setPost_server_content(analyzeInfo.lookContentType(iheaders));
        httpPostBeacon.setPost_server_encode(analyzeInfo.getEncode());
        if(analyzeInfo.isExistResponseBody(callbacks,message)) {
            String resp = new String(message.getResponse());
            int bodyOffset = analyzeResponse.getBodyOffset();
            List<String> list = analyzeInfo.analyzeBody(resp.substring(bodyOffset));
            //第二行
            httpPostBeacon.setPost_server_prepend(list.get(1));
            //第一行
            httpPostBeacon.setPost_server_prepend2(list.get(0));
            httpPostBeacon.setPost_server_append(list.get(2));
        }else{
            httpPostBeacon.setPost_server_prepend("");
            httpPostBeacon.setPost_server_prepend2("");
            httpPostBeacon.setPost_server_append("");
        }
        return httpPostBeacon;
    }

    @Override
    public Map<String, Object> putdataHttpPostBeacon() {
        dataMap.put("http_post_uri",httpPostBeacon.getHttp_post_uri());
        dataMap.put("post_client_content",httpPostBeacon.getPost_client_content());
        dataMap.put("post_client_header1",httpPostBeacon.getPost_client_header1());
        dataMap.put("post_client_header2",httpPostBeacon.getPost_client_header2());
        dataMap.put("post_client_header3",httpPostBeacon.getPost_client_header3());
        dataMap.put("post_client_header4",httpPostBeacon.getPost_client_header4());
        dataMap.put("http_post_host",httpPostBeacon.getHttp_post_host());
        dataMap.put("post_client_parameter1",httpPostBeacon.getPost_client_parameter1());
        dataMap.put("post_client_parameter2",httpPostBeacon.getPost_client_parameter2());
        dataMap.put("post_client_parameter3",httpPostBeacon.getPost_client_parameter3());
        dataMap.put("post_server_server",httpPostBeacon.getPost_server_server());
        dataMap.put("post_server_content",httpPostBeacon.getPost_server_content());
        dataMap.put("post_server_header1",httpPostBeacon.getPost_server_header1());
        dataMap.put("post_server_header2",httpPostBeacon.getPost_server_header2());
        dataMap.put("post_server_header3",httpPostBeacon.getPost_server_header3());
        dataMap.put("post_server_header4",httpPostBeacon.getPost_server_header4());
        //dataMap.put("post_client_encode",httpPostBeacon.getPost_client_encode());
        dataMap.put("post_server_encode",httpPostBeacon.getPost_server_encode());
        dataMap.put("post_server_prepend",httpPostBeacon.getPost_server_prepend());
        dataMap.put("post_server_prepend2",httpPostBeacon.getPost_server_prepend2());
        dataMap.put("post_server_append",httpPostBeacon.getPost_server_append());
        return dataMap;
    }

    public String lookUri(List<String> headers){
        String uri = "";
        for (String header : headers) {
            if (header.startsWith("POST")) {
                uri = header.split("\\s+")[1].split("[?]")[0];
            }
        }
        return uri;
    }

    public List<String> lookParameter(){
        int randnum = new Random().nextInt(2)+2;
        List<String> list = new ArrayList<>(searchParam(randnum));
        List<String> line = new ArrayList<>();
        String str = "qwertyuiopasdfghjklzxcvbnmZXCVBNMLKJHGFDSAQWERTYUIOP";
        for (int i = 0; i< 3;i++){
            line.add(i,"");
        }
        for (int j = 0; j < randnum; j++){
            //将字符str顺序打乱
            String[] liststr= str.split("");
            StringBuilder strBuff = new StringBuilder();
            Collections.shuffle(Arrays.asList(liststr));
            StringBuilder out= new StringBuilder("");
            for(String s:liststr){
                out.append(s);
            }
            str = out.toString();
            int num = new Random().nextInt(7)+6;
            for (int i = 0 ; i < num; i++){
                int number = new Random().nextInt(str.length());
                strBuff.append(str.charAt(number));
            }
            line.set(j,"parameter \""+list.get(j)+"\" \""+strBuff+"\";");
        }
        return line;
    }

    public List<String> searchParam(int randnum) {
        List<String> list = new ArrayList<>();
        for (int i=0; i < randnum; i++){
            try {
                list.add(file.readLine("parameters"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        list = list.stream().distinct().collect(Collectors.toList());
        if (list.size() == randnum){
            return list;
        }else {
            return searchParam(randnum);
        }
    }
}

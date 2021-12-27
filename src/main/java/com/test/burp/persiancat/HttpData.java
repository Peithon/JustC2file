package com.test.burp.persiancat;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import static java.util.Arrays.asList;

public class HttpData {
    private Map<String, Object> dataMap;

    public HttpData(Map<String, Object> dataMap){
        this.dataMap = dataMap;
    }

    //设置http-post client的参数
    public void putPostRequestHeaders(List<String> headers){
        dataMap.put("random_header",getHeader());
        dataMap.put("http_post_id","id");
        dataMap.put("http_post_parameter1",getParam());
        dataMap.put("http_post_encode",getEncode());
        dataMap.put("http_post_parameter2",getParam());
        for (String header : headers){
            dataMap.put("http_post_request_content","text/xml");
            if (header.startsWith("POST")) {
                dataMap.put("http_post_uri",header.split("\\s+")[1].split("[?]")[0]);
            }else if(header.startsWith("Host")){
                dataMap.put("http_post_host",header.split("\\s+")[1]);
            }else if(header.startsWith("Content-Type")){
                dataMap.put("http_post_request_content",String.valueOf(header.split("[:]")[1]).trim());
            }
        }
    }

    //设置http-post server的参数
    public void putPostResponseHeaders(List<String> headers){
        dataMap.put("http_post_server","Apache");
        dataMap.put("http_post_response_content","application/octet-stream");
        dataMap.put("Connection","close");
        for (String header : headers){
            if(header.startsWith("Server")){
                dataMap.put("http_post_server",String.valueOf(header.split("[:]")[1]).trim());
            }else if(header.startsWith("Content-Type")){
                dataMap.put("http_post_response_content",String.valueOf(header.split("[:]")[1]).trim());
            }else if(header.startsWith("Connection")){
                dataMap.put("Connection",String.valueOf(header.split("[:]")[1]).trim());
            }
        }
    }

    //设置http-get client的参数
    public void putGetRequestHeaders(List<String> headers){
        for (String header : headers) {
//            System.out.println(header);
            if (header.startsWith("GET")) {
                dataMap.put("http_get_uri", header.split("\\s+")[1]);
            } else if (header.startsWith("Host")) {
                dataMap.put("http_get_host", header.split("\\s+")[1]);
            }
        }
    }

    //设置http-get server的参数
    public void putGetResponseHeaders(List<String> headers){
        dataMap.put("http_getReq_Server","Nginx");
        for (String header : headers){
            if(header.startsWith("Server")){
                dataMap.put("http_getReq_Server",String.valueOf(header.split("[:]")[1]).trim());
            }
        }
    }

    public String getHeader(){
        Map<String,String> map = new HashMap<String,String>();
        map.put("Accept-Encoding","gzip, deflate");
        map.put("Accept-Language","en-US");
        map.put("Cache-Control","must-revalidate");
        map.put("Pragma","no-cache");
        List<String> ualist = asList(
                "Accept-Encoding",
                "Accept-Language",
                "Cache-Control",
                "Pragma"
        );
        String str = ualist.get(new Random().nextInt(ualist.size()));
        return "header"+" \""+str+"\" \""+map.get(str)+"\";";
    }

    public String getEncode(){
        List<String> encode = asList(
                "netbiosu;",
                "base64;",
                "base64url;",
                "mask;",
                "netbios;"
        );
        return encode.get(new Random().nextInt(encode.size()));
    }

    private String getParam() {
        List<String> param = asList(
                "username",
                "title",
                "mtype",
                "submit",
                "fidarray",
                "optype",
                "newtypeid",
                "targetgroup",
                "ajax",
                "confirmed",
                "finished",
                "total",
                "endtime",
                "blogids",
                "friend",
                "ip",
                "orderby",
                "ordersc",
                "fromumanage",
                "perpage",
                "ids",
                "preview",
                "styleid",
                "scrolltop",
                "anchor"
        );
        String str = "qwertyuiopasdfghjklzxcvbnmZXCVBNMLKJHGFDSAQWERTYUIOP";
        StringBuffer strBuff = new StringBuffer();
        Random ran = new Random();
        int num = new Random().nextInt(5)+6;
        for (int i = 0 ; i < num; i++){
            int number = ran.nextInt(52);
            strBuff.append(str.charAt(number));
        }
        String res = "\""+param.get(new Random().nextInt(param.size()))+"\""+" \""+strBuff.toString()+"\"";
        return res;
    }
}

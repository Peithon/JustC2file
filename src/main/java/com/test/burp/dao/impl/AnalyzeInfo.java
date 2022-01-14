package com.test.burp.dao.impl;

import burp.IBurpExtenderCallbacks;
import burp.IHttpRequestResponse;
import burp.IResponseInfo;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;
/**
 * @program: JustC2file
 * @author: Peithon
 * @create: 2022-01-08 09:09
 **/
public class AnalyzeInfo {
    //处理GET/POST的Requset/Response
    public Map<String, Object> lookHeaders(List<String> headers){
        Map<String, Object> dataMap = new HashMap<String, Object>();
        for (String header : headers) {
            if (header.startsWith("GET")) {
                //System.out.println("剔除 GET 请求行");
            }else if(header.startsWith("POST")){
                //System.out.println("剔除 POST 请求行");
            }else if(header.startsWith("User-Agent")){
                //System.out.println("剔除 User-Agent 头");
            }else if(header.startsWith("Cookie")){
                //System.out.println("剔除 Cookie 头");
            }else if(header.startsWith("HTTP")){
                //System.out.println("剔除 HTTP 响应行");
            }else if (header.startsWith("Set-Cookie")){
                //System.out.println("剔除 Set-Cookie 头");
            }else if(header.startsWith("Content-Type")){
                //System.out.println("默认存在");
            }else if(header.startsWith("Server")){
                //System.out.println("默认存在");
            }else if(header.startsWith("Date"  ) || header.startsWith("Content-Length")) {
                //System.out.println("不配置，在http-config中补充");
            }else if(header.startsWith("Accept-Encoding")){
                //System.out.println("GET中 默认存在，");
            }else if(header.startsWith("Host")){
                //System.out.println("剔除 Host 头");
            }else if (header.startsWith("Accept-Language")){
                //通过逗号分割来携带多国语言。第一个会是首选的语言，其它语言会携带一个“q”值，来表示用户对该语言的喜好程度（0~1）
                //zh-CN,zh;q=0.9,en-US;q=0.8,en;q=0.7
                //System.out.println("剔除 Accept-Language 头,需要的话自己添加或者后期代码更新做替换处理");
            }else if(header.startsWith("sec-") || header.startsWith("Sec-") || header.startsWith("DNT:")){
                //sec-ch-ua: " Not A;Brand";v="99", "Chromium";v="96", "Google Chrome";v="96"
                //DNT: 1
                //sec-ch-ua-mobile: ?0Sec-Fetch-Site: same-site
                //Sec-Fetch-Mode: cors
                //Sec-Fetch-Dest: empty
            }else {
                List<String> lists = Arrays.asList(header.split("[:]\\s+"));
                StringBuilder line = new StringBuilder();
                String key ="";
                for (int i = 0; i < lists.size(); i++){
                    if (i == 0){
                        key = lists.get(0);
                    }else{
                        line.append(lists.get(i));
                    }
                }
                if(key.equals("") && line.toString().equals("")){
                   // System.out.println("");
                }else {
                    dataMap.put(key, line.toString().trim().replaceAll("\"","\\\\\""));
                }
            }
        }
        return dataMap;
    }

    //设置编码类型
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

    //格式化header头
    public List<String> formatHeaders(Map<String, Object> map){
        List<String> listall = new ArrayList<>(map.keySet());
        List<String> list = new ArrayList<>();
        if (listall.size() > 3){
            for (int j= 0 ; j < listall.size()/3+1 ; j++){
                list.add(listall.get(new Random().nextInt(listall.size())));
            }
            list = list.stream().distinct().collect(Collectors.toList());
        }else{
            list.addAll(listall);
        }
        List<String> line = new ArrayList<>();
        for (int i = 0; i< 4;i++){
            line.add(i,"");
        }
        for (int i = 0 ; i < list.size() ; i++){
            if(i == 4){
                break;
            }
            line.set(i,"header \""+list.get(i)+"\" \""+map.get(list.get(i))+"\";");
        }
        return line;
    }
    //格式化uri
    public  String formatUri(String uri) {
        List<String> list = asList(uri.split("/"));
        StringBuilder uriStr = new StringBuilder();
        if (list.size() <= 7) {
            uriStr = new StringBuilder(uri);
        } else {
            for (int i = 1; i < 7; i++) {
                uriStr.append("/").append(list.get(i));
            }
        }
        return uriStr.toString();
    }

    //格式化body
    public  String formatHtml(String body){
        body =body.replaceAll("<!--(.|[\r\n])*?-->","");
        body = decodeUnicode(body);
        body = body.replaceAll("[\u4e00-\u9fa5]","docs");
        //str = str.replaceAll("/[*](.|[\r\n])*?[*]/","");
        body = body.replaceAll("\t|\r|\n","");
       // str = str.replaceAll("\\\\","\\\\\\\\");
        return body;
    }
    public String lookHost(List<String> headers){
        String host = "";
        for (String header : headers) {
            if (header.startsWith("Host")) {
                host = header.split("[:]\\s+")[1].trim();
            }
        }
        return host;
    }

    public String lookServer(List<String> headers){
        String server = "Apache";
        for (String header : headers) {
            if (header.startsWith("Server:")) {
                server = header.split("[:]\\s+")[1].trim();
            }
        }
        return server;
    }

    public String lookContentType(List<String> headers){
        String contentType = "text/plain;charset=UTF-8";
        for (String header : headers) {
            if (header.startsWith("Content-Type")) {
                contentType = header.split("[:]\\s+")[1].trim();
            }
        }
        return contentType;
    }

    //判断响应中的是否存在body
    public Boolean isExistResponseBody(IBurpExtenderCallbacks callbacks, IHttpRequestResponse message){
        IResponseInfo analyzeResponse = callbacks.getHelpers().analyzeResponse(message.getResponse());
        //响应包是没有参数的概念的，大多需要修改的内容都在body中
        String resp = new String(message.getResponse());
        int bodyOffset = analyzeResponse.getBodyOffset();
        //获取响应的 MIME 类型，如 HTTP 标头中所述。
        //String sss = analyzeResponse.getStatedMimeType();
        //获取响应的 MIME 类型，从 HTTP 消息正文的内容推断
        //String ssst = analyzeResponse.getInferredMimeType();
        String body = resp.substring(bodyOffset);
        //如果body中不存在内容，返回false
        return !body.equals("");
    }

    //处理响应中的body存在"的情况，并返回三部分供调用
    public List<String> analyzeBody(String body){
        body = formatHtml(body);
        List<String> list = new ArrayList<>();
        if(body.length() < 4211){
            list.add(moBody(body.substring(0,body.length()/3)));
            list.add(moBody(body.substring(body.length()/3,body.length()/3*2)));
            list.add(moBody(body.substring(body.length()/3*2,body.length())));
        }else{
            int length1 = new Random().nextInt(600)+1506;
            int length2 = body.length()- length1;
            int len = (length1+length2)/2+new Random().nextInt(335)+577;
            list.add(moBody(body.substring(0,length1)));
            list.add(moBody(body.substring((length1+length2)/2,len)));
            list.add(moBody(body.substring(length2,body.length())));
        }
        return list;
    }

    //特殊字符处理
    public String moBody(String body){
        body = body.replaceAll("\\\\","\\\\\\\\");
        body =  body.replaceAll("\"","\\\\\"");
        return  body;
    }

    //  将网页中的unicode转UTF-8
    public String decodeUnicode(String str) {
        Charset set = StandardCharsets.UTF_16;
        Pattern p = Pattern.compile("\\\\u([0-9a-fA-F]{4})");
        Matcher m = p.matcher(str);
        int start = 0;
        int start2 = 0;
        StringBuilder sb = new StringBuilder();
        while (m.find(start)) {
            start2 = m.start();
            if (start2 > start) {
                String seg = str.substring(start, start2);
                sb.append(seg);
            }
            String code = m.group(1);
            int i = Integer.valueOf(code, 16);
            byte[] bb = new byte[4];
            bb[0] = (byte) ((i >> 8) & 0xFF);
            bb[1] = (byte) (i & 0xFF);
            ByteBuffer b = ByteBuffer.wrap(bb);
            sb.append(String.valueOf(set.decode(b)).trim());
            start = m.end();
        }
        start2 = str.length();
        if (start2 > start) {
            String seg = str.substring(start, start2);
            sb.append(seg);
        }
        return sb.toString();
    }

}

package com.test.burp.testcode;

import com.test.burp.dao.impl.AnalyzeInfo;
import com.test.burp.dao.impl.LoadFile;
import com.test.burp.service.HttpsCertificateService;
import com.test.burp.service.PersiancatService;
import com.test.burp.service.PostExService;
import com.test.burp.service.impl.HttpsCertificateServiceImpl;
import com.test.burp.service.impl.PersiancatServiceImpl;
import com.test.burp.service.impl.PostExServiceImpl;
import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;

public class FreemarkerDemo {
    private final String TEMPLATE_PATH = "src/main/resources/templates";
    private static PersiancatService persiancatService = new PersiancatServiceImpl();
    private static HttpsCertificateService httpsCertificateService = new HttpsCertificateServiceImpl();
    private static PostExService postExService = new PostExServiceImpl();
    private static AnalyzeInfo analyzeInfo = new AnalyzeInfo();
    public FreemarkerDemo()  {
        // step1 创建freeMarker配置实例
        Configuration configuration = new Configuration();
        try {
            // step2 获取模版路径
            configuration.setDirectoryForTemplateLoading(new File(TEMPLATE_PATH));
            // step3 创建数据模型
            Map<String, Object> dataMap = new HashMap<String, Object>();
            dataMap.put("http_get_uri", new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()));
            dataMap.put("get_client_header1", "AutoCode;\r\n\t\t1231231\r\n\t\ttyyttttt");
            //rand.nextInt(n)中的参数n代表的是生成随机整数的数量,整数取值为[30,60]
            dataMap.put("http_get_host",String.valueOf((new Random().nextInt(30)+30)*100));
            dataMap.put("http_getReq_Server","15");
            // step4 加载模版文件
            Template template = configuration.getTemplate("test.ftl");
            // step5 生成数据
            //Writer out = null;
            //out = new OutputStreamWriter(System.out);
            // step6 输出文件
            StringWriter strWriter = new StringWriter();
            template.process(dataMap, strWriter);
            String str = strWriter.toString();
            System.out.println(str);
//            获取时间
//            Date date = new Date();
//            SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
//            System.out.println(dateFormat.format(date));
        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                if (null != out) {
//                    out.flush();
//                }
//            } catch (Exception e2) {
//                e2.printStackTrace();
//            }
        }
    }

    public static String lookTestParameter(){
        int randnum = new Random().nextInt(3)+3;
        List<String> list = new ArrayList<>(searchTestParam(randnum));
        StringBuilder line = new StringBuilder();
        String str = "qwertyuiopasdfghjklzxcvbnmZXCVBNMLKJHGFDSAQWERTYUIOP-1234567890_";

        for (int j = 0; j < randnum; j++){
            StringBuilder strBuff = new StringBuilder();
            //将字符str顺序打乱
            String[] liststr= str.split("");
            Collections.shuffle(asList(liststr));
            StringBuilder out= new StringBuilder("");
            for(String s:liststr){
                out.append(s);
            }
            str = out.toString();
            int num = new Random().nextInt(10)+6;
            for (int i = 0 ; i < num; i++){
                int number = new Random().nextInt(str.length());
                strBuff.append(str.charAt(number));
            }
            if (j == 0){
                line.append("parameter \"").append(list.get(j)).append("\" \"").append(strBuff).append("\";");
            }else {
                line.append("\r\n\t\tparameter \"").append(list.get(j)).append("\" \"").append(strBuff).append("\";");
            }
        }
        return line.toString();
    }
    public static List<String> searchTestParam(int randnum) {
        List<String> list = new ArrayList<>();
        for (int i=0; i < randnum; i++){
            try {
                list.add(new LoadFile().readLine("parameters"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        list = list.stream().distinct().collect(Collectors.toList());
        if (list.size() == randnum){
            return list;
        }else {
            return searchTestParam(randnum);
        }
    }
    public static String lookTestHeaders(Map<String, Object> map){
        List<String> listall = new ArrayList<>(map.keySet());
        List<String> list = new ArrayList<>();
        if (listall.size() > 3){
            for (int j= 0 ; j < listall.size() ; j++){
                int num = new Random().nextInt(listall.size());
                list.add(listall.get(num));
            }
            list = list.stream().distinct().collect(Collectors.toList());
        }else{
            list.addAll(listall);
        }
        StringBuilder line = new StringBuilder();
        for (int i = 0 ; i < list.size() ; i++){
            if (i == 0){
                line.append("header \"").append(list.get(i)).append("\" \"").append(map.get(list.get(i))).append("\";");
            }else {
                line.append("\r\n\t\theader \"").append(list.get(i)).append("\" \"").append(map.get(list.get(i))).append("\";");
            }
        }
        return line.toString();
    }

    public static String lookUri(List<String> headers){
        String uri = "";
        for (String header : headers) {
            if (header.startsWith("GET")) {
                uri = (header.split("\\s+")[1].split("[?]")[0]);
            }
        }
       // System.out.println(uri);
        return uri;
    }

    //格式化uri
    public static String formatUri(String uri){
        List<String> list = asList(uri.split("/"));
        StringBuilder uriStr = new StringBuilder();
        if(list.size() <= 6){
            uriStr = new StringBuilder(uri);
        }else {
            for (int i = 1; i< 7;i ++){
                uriStr.append("/").append(list.get(i));
            }
        }
        return uriStr.toString();
    }
    public static void main(String[] args) throws IOException {
        Map<String, Object> dataMap = new HashMap<String, Object>();
       // persiancatService.dataPersiancat();
        //dataMap.putAll(persiancatService.putdataPersiancat());
        //dataMap.putAll(httpsCertificateService.putdataHttpsCertificate());
        //dataMap.putAll(postExService.putdataPostEx());
        //System.out.println(dataMap);
        List<String> testlist = asList(
                "GET /og/_/js/k=og.qtm.en_US.spppbM4LMIk.O/rt=j/m=qabr,qgl,q_dnp,qdid,qcwid,qbg,qbd,qapid,qald/exm=qaaw,qadd,qaid,qein,qhaw,qhbr,qhch,qhga,qhid,qhin,qhpr/d=1/ed=1/rs=AA2YrTuopUsWYZY3-5Ts97yUUVoGW6GKgA HTTP/1.1","Host: www.google.com","Sec-Fetch-Dest: empty","Sec-Fetch-Dest2: empty","Accept-Language: zh-CN,zh;q=0.9,en-US;q=0.8,en;q=0.7","Content-Type: text/plain;charset=UTF-8","Referer: https://www.google.com/",
                "Accept-Encoding: gzip, deflate"
        );
//        System.out.println(dataMap.size());
//        String header = "Content-Security-Policy: object-src 'none';base-uri 'self';script-src 'nonce-Jr/dv0OrZoGbY5jRuHrS/Q==' 'strict-dynamic' 'report-sample' 'unsafe-eval' 'unsafe-inline' https: http:;report-uri https://csp.withgoogle.com/csp/gws/cdt1";
//        List<String> lists = Arrays.asList(header.split("[:]\\s+"));
//        String line = "";
//        for (int i = 0; i < lists.size(); i++){
//            if (i == 0){
//                System.out.println(lists.get(0));
//            }else{
//               line += lists.get(i);
//            }
//            System.out.println(line);
//
//        }
//        System.out.println(String.valueOf(header.split("[:]\\s+")[1]).trim());
//
//       new FreemarkerDemo();
//        System.out.println(lookUri(testlist));
//        System.out.println(formatUri(lookUri(testlist)));
        //dataMap = analyzeInfo.lookHeaders(testlist);
        //System.out.println(lookTestHeaders(analyzeInfo.lookHeaders(testlist)));
        System.out.println(new Random().nextInt(39999)+12500);
        List<String> list = new ArrayList<>();
        for (int i = 0; i< 5;i++){
            list.add(i,"");
        }
        for (int i = 0; i< 5;i++){
            list.set(i,"sdfsd");
        }
        System.out.println(list);
        //System.out.println(new ReadFile().readLoad("testhtml").substring(0,3000));
        //System.out.println(lookTestParameter());
        //System.out.println(new ReadFile().readLoad("testhtml").replaceAll("\"","\\\\\""));
    }

    public static String decodeUnicode(String str) {
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
    //特殊字符处理
    public static String moBody(String body){
        body = body.replaceAll("[\u4e00-\u9fa5]","");
        body = body.replaceAll("\\\\","\\\\\\\\");
        body =  body.replaceAll("\"","\\\\\"");
        return  body;
    }
    public static String formatHtml(String body){
        String str =body.replaceAll("<!--(.|[\r\n])*?-->","");
        //str = str.replaceAll("/[*](.|[\r\n])*?[*]/","");
        str = str.replaceAll("\\\\","\\\\\\\\");
        str = str.replaceAll("\t|\r|\n","");
        return str;
    }
    public static List<String> analyzeBody(String body){
        List<String> list = new ArrayList<>();
        if(body.length() < 10000){
            list.add(body.substring(0,body.length()/3).replaceAll("\"","\\\\\""));
            list.add(body.substring(body.length()/3,body.length()/3*2).replaceAll("\"","\\\\\""));
            list.add(body.substring(body.length()/3*2,body.length()).replaceAll("\"","\\\\\""));
        }else{
            int length1 = new Random().nextInt(600)+3500;
            int length2 = body.length()- length1;
            int len = (length1+length2)/2+new Random().nextInt(635)+877;
            list.add(body.substring(0,length1).replaceAll("\"","\\\\\""));
            list.add(body.substring((length1+length2)/2,len).replaceAll("\"","\\\\\""));
            list.add(body.substring(length2,body.length()).replaceAll("\"","\\\\\""));
        }

        return list;
    }
}

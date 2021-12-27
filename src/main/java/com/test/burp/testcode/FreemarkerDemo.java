package com.test.burp.testcode;

import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.*;

import static java.util.Arrays.asList;

public class FreemarkerDemo {
    private final String TEMPLATE_PATH = "src/main/resources/templates";

    public FreemarkerDemo()  {
        // step1 创建freeMarker配置实例
        Configuration configuration = new Configuration();
        try {
            // step2 获取模版路径
            configuration.setDirectoryForTemplateLoading(new File(TEMPLATE_PATH));
            // step3 创建数据模型
            Map<String, Object> dataMap = new HashMap<String, Object>();
            dataMap.put("timestamp", new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()));
            dataMap.put("sample_name", "AutoCode");
            //rand.nextInt(n)中的参数n代表的是生成随机整数的数量,整数取值为[30,60]
            dataMap.put("sleeptime",String.valueOf((new Random().nextInt(30)+30)*100));
            dataMap.put("jitter","15");
            dataMap.put("maxdns",String.valueOf(new Random().nextInt(34)+220));
            dataMap.put("useragent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/96.0.4664.93 Safari/537.36");
            // step4 加载模版文件
            Template template = configuration.getTemplate("c2profile_template.ftl");
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
    public static void main(String[] args) throws MalformedURLException {
        int a = 10;
        int b = 99;
        String header = "Server: gws";
        String out = header.startsWith("Server") ? String.valueOf(header.split("[:]")[1]).trim() : "text/xml" ;
        //int result = a > b ? a++ : b--;
        System.out.println(header);
        System.out.println(header.startsWith("Server"));
        System.out.println(out);
        String content = "GET /js/src/scrollspy/js?v=6.4.0";
        try{
            System.out.println(content.split("\\s+")[1].split("[?]")[0].split("[.]")[1]);
        }catch (Exception e){
            System.out.println(content.split("\\s+")[1].split("[?]")[0]);
        }

    }
}

package java.com.test.burp.persiancat;

import java.text.SimpleDateFormat;
import java.util.*;

import static java.util.Arrays.asList;

public class Data {
    private Map<String, Object> dataMap;
    public Data(Map<String, Object> dataMap){
        this.dataMap = dataMap;
    }
    public void putData(){
        dataMap.put("timestamp", getTimestamp());
        dataMap.put("sample_name", getSample_name());
        dataMap.put("sleeptime",getSleeptime());
        dataMap.put("jitter",getJitter());
        dataMap.put("maxdns",getMaxdns());
        dataMap.put("useragent",getUseragent());
        /*
        * 默认的DNS_IDLE为0.0.0.0，也可能被监听到
        * */
        dataMap.put("dns_idle",getDns_idle());
        /*
        * CS在创建Beacon HTTPS监听时，是包含SSL证书的，该证书是CS默认的证书，其中包含了CobaltStrike等关键字信息，不修改的情况下，会被直接监测到。
        * */
        dataMap.put("https_certificate_C",getCertificateC());
        dataMap.put("https_certificate_V",getCertificateV());
        dataMap.put("https_certificate_CN",getCertificateCN());
        dataMap.put("https_certificate_OU",getCertificateOU());
        dataMap.put("https_certificate_O",getCertificateOU());
        /**
         * CS会在目标机放一个小的payload，然后由这个小的payload去下载大马，这个过程是个分段过程，不是一次下载回来的；
         * 其中下载请求相关的流量特征，可以通过http-stager来定义
         * */
        dataMap.put("host_stage",getHost_stage());
    }
    public String getTimestamp(){
        // C2配置文件创建时间
        return new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date());
    }
    public String getSample_name(){
        //脚本名称
        return "AutoCode";
    }
    public String getSleeptime(){
        //sleeptime 设置用于配置 Beacons 默认签入的频率（以毫秒为单位），默认60秒。
        return String.valueOf((new Random().nextInt(25)+30)*100);
    }
    public String getJitter(){
        /**jitter用于按指定的百分比改变签入间隔；它接受 0 - 99 的值。
         * 例如：
         * set sleeptime "60000";
         * set jitter    "20";
         * jitter(抖动率)指定的随机时间量，Beacons 将在 48（60-60*20%） 到 72（60+60*20%） 秒之间的任何时间签入。
         * 增加签入抖动可以减少某些安全监控解决方案检测到的机会
         **/
        return String.valueOf(new Random().nextInt(20));
    }
    public String getMaxdns(){
        /**
        *maxdns配置 Cobalt Strike 在上传数据时使用的最大主机名长度，默认值为 255。
         * 这可能会在某些安全设备上标记。此设置配置得越低，可能生成的 DNS 流量就越多。
         * 无论哪种方式，目标都会看到整体 DNS 流量激增，但在将此设置更改为较低值时要记住这一点很重要。
        **/
        return String.valueOf(new Random().nextInt(17)+237);
    }
    public String getUseragent(){
        /**
         * useragent配置将用于 HTTP 流量的用户代理字符串
         * 常用Useragent  http://www.useragentstring.com/pages/useragentstring.php
         * asList 是 Arrays 的静态方法，这里使用了静态导入。这种方式添加的是不可变的 List, 即不能添加、删除等操作
         */
        List<String> ualist = asList(
              "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/96.0.4664.93 Safari/537.36",
                "Mozilla/5.0 (iPad; CPU iPad OS 5_1_1 like Mac OS X) AppleWebKit/536.1 (KHTML, like Gecko) CriOS/46.0.850.0 Mobile/40I689 Safari/536.1",
                "Mozilla/5.0 (iPad; CPU iPad OS 9_3_5 like Mac OS X) AppleWebKit/535.0 (KHTML, like Gecko) CriOS/63.0.888.0 Mobile/87Y454 Safari/535.0",
                "Mozilla/5.0 (iPad; CPU iPad OS 6_1_6 like Mac OS X) AppleWebKit/536.1 (KHTML, like Gecko) FxiOS/17.5d2973.0 Mobile/56S750 Safari/536.1",
                "Mozilla/5.0 (Android 10; Mobile; rv:80.0) Gecko/20100101 Firefox/80.0",
                "Mozilla/5.0 (iPhone; CPU iPhone OS 14_0 like Mac OS X) AppleWebKit/604.1.28 (KHTML, like Gecko) CriOS/96.0.4664.53 Mobile/14E5239e Safari/602.1",
                "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/64.0.3282.140 Safari/537.36 Edge/18.17763",
                "Mozilla/5.0 (Windows NT 10.0; WOW64; Trident/7.0; rv:11.0) like Gecko",
                "Mozilla/5.0 (iPhone; CPU iPhone OS 8_3 like Mac OS X) AppleWebKit/600.1.4 (KHTML, like Gecko) Version/8.0 Mobile/12F70 Safari/600.1.4",
                "Mozilla/5.0 (iPhone; CPU iPhone OS 7_0_4 like Mac OS X) AppleWebKit/537.51.1 (KHTML, like Gecko) CriOS/31.0.1650.18 Mobile/11B554a Safari/8536.25",
                "Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 5.2; Trident/4.0)",
                "Mozilla/5.0 (compatible; MSIE 6.0; Windows NT 5.1; Trident/4.0)",
                "Mozilla/5.0 (iPad; CPU iPad OS 10_3_4 like Mac OS X) AppleWebKit/535.1 (KHTML, like Gecko) CriOS/14.0.860.0 Mobile/48W083 Safari/535.1",
                "Mozilla/5.0 (iPad; CPU iPad OS 6_1_6 like Mac OS X) AppleWebKit/531.1 (KHTML, like Gecko) CriOS/26.0.861.0 Mobile/13D727 Safari/531.1",
                "Mozilla/5.0 (Windows NT 5.1) AppleWebKit/534.2 (KHTML, like Gecko) Chrome/48.0.852.0 Safari/534.2",
                "Mozilla/5.0 (Windows 98; gez-ET; rv:1.9.2.20) Gecko/2011-03-11 18:04:51 Firefox/3.6.14",
                "Mozilla/5.0 (compatible; MSIE 7.0; Windows NT 10.0; Trident/5.0)",
                "Opera/8.98.(Windows CE; ko-KR) Presto/2.9.170 Version/11.00",
                "Mozilla/5.0 (compatible; MSIE 8.0; Windows NT 4.0; Trident/4.0)",
                "Mozilla/5.0 (Windows NT 4.0) AppleWebKit/532.1 (KHTML, like Gecko) Chrome/13.0.883.0 Safari/532.1",
                "Mozilla/5.0 (Windows; U; Windows NT 5.2) AppleWebKit/533.24.3 (KHTML, like Gecko) Version/5.0 Safari/533.24.3",
                "Mozilla/5.0 (compatible; MSIE 5.0; Windows 98; Win 9x 4.90; Trident/3.1)",
                "Mozilla/5.0 (iPad; CPU iPad OS 14_2 like Mac OS X) AppleWebKit/535.1 (KHTML, like Gecko) FxiOS/16.0x3961.0 Mobile/06N899 Safari/535.1",
                "Mozilla/5.0 (Android 4.0.4; Mobile; rv:47.0) Gecko/47.0 Firefox/47.0",
                "Mozilla/5.0 (Android 2.1; Mobile; rv:59.0) Gecko/59.0 Firefox/59.0",
                "Mozilla/5.0 (Linux; Android 2.2.2) AppleWebKit/534.2 (KHTML, like Gecko) Chrome/59.0.803.0 Safari/534.2",
                "Mozilla/5.0 (iPad; CPU iPad OS 10_3_4 like Mac OS X) AppleWebKit/532.1 (KHTML, like Gecko) CriOS/30.0.834.0 Mobile/77D555 Safari/532.1",
                "Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10_11_7) AppleWebKit/532.2 (KHTML, like Gecko) Chrome/27.0.899.0 Safari/532.2",
                "Mozilla/5.0 (iPad; CPU iPad OS 5_1_1 like Mac OS X) AppleWebKit/535.2 (KHTML, like Gecko) FxiOS/10.9y6286.0 Mobile/07K099 Safari/535.2",
                "Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10_10_7 rv:4.0; ps-AF) AppleWebKit/532.43.6 (KHTML, like Gecko) Version/5.0 Safari/532.43.6",
                "Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10_5_6) AppleWebKit/534.1 (KHTML, like Gecko) Chrome/47.0.856.0 Safari/534.1",
                "Mozilla/5.0 (iPad; CPU iPad OS 3_1_3 like Mac OS X) AppleWebKit/533.1 (KHTML, like Gecko) FxiOS/15.5y8112.0 Mobile/22K809 Safari/533.1",
                "Mozilla/5.0 (iPad; CPU iPad OS 10_3_3 like Mac OS X) AppleWebKit/532.0 (KHTML, like Gecko) CriOS/63.0.825.0 Mobile/59Q933 Safari/532.0",
                "Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10_12_0 rv:2.0; bs-BA) AppleWebKit/532.2.5 (KHTML, like Gecko) Version/4.0.4 Safari/532.2.5"
        );

        return ualist.get(new Random().nextInt(ualist.size()));
    }
    public String getDns_idle(){
        /**dns_idle 选项用于向 DNS 信标发送没有任务排队的信号。
         * 我们可以设置任何 IP，但 Google DNS 服务器被主机广泛用于 DNS，因此我们可以使用它。
         * 同样，这是自定义目标 DNS 服务器的理想选择。
         * */
        List<String> dnslist = asList(
                "223.5.5.5","8.8.4.4","8.8.8.8","101.226.4.6","114.114.114.114","119.29.29.29","223.6.6.6",
                "114.114.115.115","114.114.114.119","9.9.9.9","149.112.112.112"
        );
        return  dnslist.get(new Random().nextInt(dnslist.size()));
    }
    public String getCertificateC(){
        //Certificate C 的值
        List<String> certlistc = asList(
                "AF", "AX", "AL", "DZ", "AS", "AD", "AO", "AI", "AQ", "AG", "AR",
                "AM", "AW", "AU", "AT", "AZ", "BS", "BH", "BD", "BB", "BY", "BE",
                "BZ", "BJ", "BM", "BT", "BO", "BQ", "BA", "BW", "BV", "BR", "IO",
                "BN", "BG", "BF", "BI", "CV", "KH", "CM", "CA", "KY", "CF", "TD",
                "CL", "CN", "CX", "CC", "CO", "KM", "CG", "CD", "CK", "CR", "CI",
                "HR", "CU", "CW", "CY", "CZ", "DK", "DJ", "DM", "DO", "EC", "EG",
                "SV", "GQ", "ER", "EE", "ET", "FK", "FO", "FJ", "FI", "FR", "GF",
                "PF", "TF", "GA", "GM", "GE", "DE", "GH", "GI", "GR", "GL", "GD",
                "GP", "GU", "GT", "GG", "GN", "GW", "GY", "HT", "HM", "VA", "HN",
                "HK", "HU", "IS", "IN", "ID", "IR", "IQ", "IE", "IM", "IL", "IT",
                "JM", "JP", "JE", "JO", "KZ", "KE", "KI", "KP", "KR", "KW", "KG",
                "LA", "LV", "LB", "LS", "LR", "LY", "LI", "LT", "LU", "MO", "MK",
                "MG", "MW", "MY", "MV", "ML", "MT", "MH", "MQ", "MR", "MU", "YT",
                "MX", "FM", "MD", "MC", "MN", "ME", "MS", "MA", "MZ", "MM", "NA",
                "NR", "NP", "NL", "NC", "NZ", "NI", "NE", "NG", "NU", "NF", "MP",
                "NO", "OM", "PK", "PW", "PS", "PA", "PG", "PY", "PE", "PH", "PN",
                "PL", "PT", "PR", "QA", "RE", "RO", "RU", "RW", "BL", "SH", "KN",
                "LC", "MF", "PM", "VC", "WS", "SM", "ST", "SA", "SN", "RS", "SC",
                "SL", "SG", "SX", "SK", "SI", "SB", "SO", "ZA", "GS", "SS", "ES",
                "LK", "SD", "SR", "SJ", "SZ", "SE", "CH", "SY", "TW", "TJ", "TZ",
                "TH", "TL", "TG", "TK", "TO", "TT", "TN", "TR", "TM", "TC", "TV",
                "UG", "UA", "AE", "GB", "US", "UM", "UY", "UZ", "VU", "VE", "VN",
                "VG", "VI", "WF", "EH", "YE", "ZM", "ZW"
        );
        return certlistc.get(new Random().nextInt(certlistc.size()));
    }

    public String getCertificateV(){
        return "365";
    }

    public String getCertificateCN(){
        return "www.bing.com";
    }
    public String getCertificateOU(){
        return "bing.com";
    }

    public String getHost_stage(){
        /**传输数据、Exp时，内容建议分段传输，和运输投资类似，分批的安全性比一次的更高。
         * true表示进行分段传输，false表示不进行分段传输，这里默认配置为true
         */
        return "true";
    }


}

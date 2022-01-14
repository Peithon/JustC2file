package com.test.burp.dao.impl;

import com.test.burp.dao.DnsBeaconDao;
import com.test.burp.model.DnsBeacon;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @program: JustC2file
 * @author: Peithon
 * @github: https://github.com/Peithon/JustC2file
 * @create: 2022-01-13 16:03
 **/
public class DnsBeaconDaoImpl implements DnsBeaconDao {
    private DnsBeacon dnsBeacon = new DnsBeacon();
    private LoadFile file = new LoadFile();
    Map<String, Object> dataMap = new HashMap<String, Object>();
    @Override
    public DnsBeacon dataDnsBeacon() {
        dnsBeacon.setMaxdns(lookMaxdns());
        dnsBeacon.setDns_max_txt(lookDns_max_txt());
        dnsBeacon.setDns_sleep(lookDns_sleep());
        try {
            dnsBeacon.setDns_idle(file.readLine("dns_idle"));
            dnsBeacon.setDns_stager_prepend(file.readLine("dns_stager_prepend"));
            dnsBeacon.setDns_stager_subhost(file.readLine("dns_stager_subhost"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dnsBeacon;
    }

    @Override
    public Map<String, Object> putdataDnsBeacon() {
        dataMap.put("maxdns",dnsBeacon.getMaxdns());
        dataMap.put("dns_max_txt",dnsBeacon.getDns_max_txt());
        dataMap.put("dns_idle",dnsBeacon.getDns_idle());
        dataMap.put("dns_sleep",dnsBeacon.getDns_sleep());
        dataMap.put("dns_stager_prepend",dnsBeacon.getDns_stager_prepend());
        dataMap.put("dns_stager_subhost",dnsBeacon.getDns_stager_subhost());
        return dataMap;
    }

    public String lookMaxdns(){
        /*
         maxdns配置 Cobalt Strike 在上传数据时使用的最大主机名长度，默认值为 255。
          这可能会在某些安全设备上标记。此设置配置得越低，可能生成的 DNS 流量就越多。
          无论哪种方式，目标都会看到整体 DNS 流量激增，但在将此设置更改为较低值时要记住这一点很重要。
         */
        return String.valueOf(new Random().nextInt(6)+249);
    }

    public String lookDns_max_txt(){
        return "248";
    }

    public String lookDns_sleep(){
        return "0";
    }

}

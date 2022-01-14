package com.test.burp.dao.impl;

import com.test.burp.dao.PersiancatDao;
import com.test.burp.model.Persiancat;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
/**
 * @program: JustC2file
 * @author: Peithon
 * @create: 2022-01-08 09:09
 **/
public class PersiancatDaoImpl implements PersiancatDao {
    private Persiancat persiancat= new Persiancat();
    private LoadFile file = new LoadFile();
    Map<String, Object> dataMap = new HashMap<String, Object>();
    @Override
    public Persiancat dataPersiancat() {
        persiancat.setTimestamp(lookTimestamp());
        persiancat.setSample_name(lookSample_name());
        persiancat.setSleeptime(lookSleeptime());
        persiancat.setJitter(lookJitter());
        persiancat.setData_jitter(lookDataJitter());
        persiancat.setHost_stage(lookHost_stage());
        persiancat.setTcp_port(lookTcp_port());
        try {
            persiancat.setUseragent(file.readLine("useragents"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return persiancat;
    }

    @Override
    public Map<String, Object> putdataPersiancat() {
        dataMap.put("timestamp", persiancat.getTimestamp());
        dataMap.put("sample_name", persiancat.getSample_name());
        dataMap.put("sleeptime",persiancat.getSleeptime());
        dataMap.put("jitter",persiancat.getJitter());
        dataMap.put("data_jitter",persiancat.getData_jitter());
        dataMap.put("useragent",persiancat.getUseragent());
        /*
         * 默认的DNS_IDLE为0.0.0.0，也可能被监听到
         * */
        dataMap.put("tcp_port",persiancat.getTcp_port());
        /*
          CS会在目标机放一个小的payload，然后由这个小的payload去下载大马，这个过程是个分段过程，不是一次下载回来的；
          其中下载请求相关的流量特征，可以通过http-stager来定义
          */
        dataMap.put("host_stage",persiancat.getHost_stage());
        return dataMap;
    }

    public String lookTimestamp(){
        // C2配置文件创建时间
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }

    public String lookSample_name(){
        //脚本名称
        return "CobaltStrike Beacon";
    }
    public String lookSleeptime(){
        //sleeptime 设置用于配置 Beacons 默认签入的频率（以毫秒为单位），默认60秒。
        return String.valueOf((new Random().nextInt(25)+30)*1000);
    }
    public String lookJitter(){
        /*jitter用于按指定的百分比改变签入间隔；它接受 0 - 99 的值。
          例如：
          set sleeptime "60000";
          set jitter    "20";
          jitter(抖动率)指定的随机时间量，Beacons 将在 48（60-60*20%） 到 72（60+60*20%） 秒之间的任何时间签入。
          增加签入抖动可以减少某些安全监控解决方案检测到的机会
         */
        return String.valueOf(new Random().nextInt(20)+1);
    }

    public String lookDataJitter(){
        /*将随机长度字符串（最多 data_jitter 值）附加到 http-get 和 http-post 服务器输出。
         */
        return String.valueOf(new Random().nextInt(14)+97);
    }

    public String lookTcp_port(){
        //默认端口是 4444。可以动态更改，但在切换到动态端口之前，将始终首先使用配置文件中设置的端口。
        return String.valueOf(new Random().nextInt(40000)+12500);
    }

    public String lookHost_stage(){
        /*传输数据、Exp时，内容建议分段传输，和运输投资类似，分批的安全性比一次的更高。
          true表示进行分段传输，false表示不进行分段传输，这里默认配置为false
         */
        return "false";
    }
}

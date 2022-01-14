package com.test.burp.model;
/**
 * @program: JustC2file
 * @author: Peithon
 * @create: 2022-01-08 09:09
 **/
public class Persiancat {
    private String timestamp;
    private String sample_name;
    private String sleeptime;
    private String jitter;
    private String data_jitter;
    private String tcp_port;
    private String useragent;
    private String host_stage;

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getSample_name() {
        return sample_name;
    }

    public void setSample_name(String sample_name) {
        this.sample_name = sample_name;
    }

    public String getSleeptime() {
        return sleeptime;
    }

    public void setSleeptime(String sleeptime) {
        this.sleeptime = sleeptime;
    }

    public String getJitter() {
        return jitter;
    }

    public void setJitter(String jitter) {
        this.jitter = jitter;
    }

    public String getData_jitter() {
        return data_jitter;
    }

    public void setData_jitter(String data_jitter) {
        this.data_jitter = data_jitter;
    }

    public String getTcp_port() {
        return tcp_port;
    }

    public void setTcp_port(String tcp_port) {
        this.tcp_port = tcp_port;
    }

    public String getUseragent() {
        return useragent;
    }

    public void setUseragent(String useragent) {
        this.useragent = useragent;
    }

    public String getHost_stage() {
        return host_stage;
    }

    public void setHost_stage(String host_stage) {
        this.host_stage = host_stage;
    }

}

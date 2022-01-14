package com.test.burp.model;

/**
 * @program: JustC2file
 * @author: Peithon
 * @github: https://github.com/Peithon/JustC2file
 * @create: 2022-01-12 12:21
 **/
public class DnsBeacon {
    private String dns_idle;
    private String dns_max_txt;
    private String dns_sleep;
    private String dns_ttl;
    private String maxdns;
    private String dns_stager_prepend;
    private String dns_stager_subhost;
    private String beacon;
    private String get_A;
    private String get_AAAA;
    private String get_TXT;
    private String put_metadata;
    private String put_output;
    private String ns_response;

    public String getDns_idle() {
        return dns_idle;
    }

    public void setDns_idle(String dns_idle) {
        this.dns_idle = dns_idle;
    }

    public String getDns_max_txt() {
        return dns_max_txt;
    }

    public void setDns_max_txt(String dns_max_txt) {
        this.dns_max_txt = dns_max_txt;
    }

    public String getDns_sleep() {
        return dns_sleep;
    }

    public void setDns_sleep(String dns_sleep) {
        this.dns_sleep = dns_sleep;
    }

    public String getDns_ttl() {
        return dns_ttl;
    }

    public void setDns_ttl(String dns_ttl) {
        this.dns_ttl = dns_ttl;
    }

    public String getMaxdns() {
        return maxdns;
    }

    public void setMaxdns(String maxdns) {
        this.maxdns = maxdns;
    }

    public String getDns_stager_prepend() {
        return dns_stager_prepend;
    }

    public void setDns_stager_prepend(String dns_stager_prepend) {
        this.dns_stager_prepend = dns_stager_prepend;
    }

    public String getDns_stager_subhost() {
        return dns_stager_subhost;
    }

    public void setDns_stager_subhost(String dns_stager_subhost) {
        this.dns_stager_subhost = dns_stager_subhost;
    }

    public String getBeacon() {
        return beacon;
    }

    public void setBeacon(String beacon) {
        this.beacon = beacon;
    }

    public String getGet_A() {
        return get_A;
    }

    public void setGet_A(String get_A) {
        this.get_A = get_A;
    }

    public String getGet_AAAA() {
        return get_AAAA;
    }

    public void setGet_AAAA(String get_AAAA) {
        this.get_AAAA = get_AAAA;
    }

    public String getGet_TXT() {
        return get_TXT;
    }

    public void setGet_TXT(String get_TXT) {
        this.get_TXT = get_TXT;
    }

    public String getPut_metadata() {
        return put_metadata;
    }

    public void setPut_metadata(String put_metadata) {
        this.put_metadata = put_metadata;
    }

    public String getPut_output() {
        return put_output;
    }

    public void setPut_output(String put_output) {
        this.put_output = put_output;
    }

    public String getNs_response() {
        return ns_response;
    }

    public void setNs_response(String ns_response) {
        this.ns_response = ns_response;
    }
}

package com.test.burp.model;

/**
 * @program: JustC2file
 * @author: Peithon
 * @github: https://github.com/Peithon/JustC2file
 * @create: 2022-01-10 19:54
 **/
public class HttpStager {
    private String uri_x86;
    private String uri_x64;
    private String server_Content_Type;
    private String server_Server;
    private String server_header1;
    private String server_header2;
    private String server_header3;
    private String server_header4;
    private String output_prepend;
    private String output_append;
    private String client_Accept;
    private String output_encode;
    private String output_prepend2;

    public String getUri_x86() {
        return uri_x86;
    }

    public void setUri_x86(String uri_x86) {
        this.uri_x86 = uri_x86;
    }

    public String getUri_x64() {
        return uri_x64;
    }

    public void setUri_x64(String uri_x64) {
        this.uri_x64 = uri_x64;
    }

    public String getServer_Content_Type() {
        return server_Content_Type;
    }

    public void setServer_Content_Type(String server_Content_Type) {
        this.server_Content_Type = server_Content_Type;
    }

    public String getServer_Server() {
        return server_Server;
    }

    public void setServer_Server(String server_Server) {
        this.server_Server = server_Server;
    }

    public String getServer_header1() {
        return server_header1;
    }

    public void setServer_header1(String server_header1) {
        this.server_header1 = server_header1;
    }

    public String getServer_header2() {
        return server_header2;
    }

    public void setServer_header2(String server_header2) {
        this.server_header2 = server_header2;
    }

    public String getServer_header3() {
        return server_header3;
    }

    public void setServer_header3(String server_header3) {
        this.server_header3 = server_header3;
    }

    public String getServer_header4() {
        return server_header4;
    }

    public void setServer_header4(String server_header4) {
        this.server_header4 = server_header4;
    }

    public String getOutput_prepend() {
        return output_prepend;
    }

    public void setOutput_prepend(String output_prepend) {
        this.output_prepend = output_prepend;
    }

    public String getOutput_append() {
        return output_append;
    }

    public void setOutput_append(String output_append) {
        this.output_append = output_append;
    }

    public String getClient_Accept() {
        return client_Accept;
    }

    public void setClient_Accept(String client_Accept) {
        this.client_Accept = client_Accept;
    }

    public String getOutput_encode() {
        return output_encode;
    }

    public void setOutput_encode(String output_encode) {
        this.output_encode = output_encode;
    }

    public String getOutput_prepend2() {
        return output_prepend2;
    }

    public void setOutput_prepend2(String output_prepend2) {
        this.output_prepend2 = output_prepend2;
    }
}

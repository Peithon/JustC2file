package com.test.burp.model;
/**
 * @program: JustC2file
 * @author: Peithon
 * @create: 2022-01-08 09:09
 **/
public class HttpsCertificate {
    private String c;
    private String cn;
    private String ou;
    private String o;
    private String validity;

    public String getC() {
        return c;
    }

    public void setC(String c) {
        this.c = c;
    }

    public String getCn() {
        return cn;
    }

    public void setCn(String cn) {
        this.cn = cn;
    }

    public String getOu() {
        return ou;
    }

    public void setOu(String ou) {
        this.ou = ou;
    }

    public String getO() {
        return o;
    }

    public void setO(String o) {
        this.o = o;
    }

    public String getValidity() {
        return validity;
    }

    public void setValidity(String validity) {
        this.validity = validity;
    }

    @Override
    public String toString() {
        return "HttpsCertificate{" +
                "c='" + c + '\'' +
                ", cn='" + cn + '\'' +
                ", ou='" + ou + '\'' +
                ", o='" + o + '\'' +
                ", validity='" + validity + '\'' +
                '}';
    }
}

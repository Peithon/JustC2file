package com.test.burp.dao.impl;

import com.test.burp.dao.HttpsCertificateDao;
import com.test.burp.model.HttpsCertificate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import static java.util.Arrays.asList;
/**
 * @program: JustC2file
 * @author: Peithon
 * @create: 2022-01-08 09:09
 **/
public class HttpsCertificateDaoImpl implements HttpsCertificateDao {
    private HttpsCertificate  httpsCertificate= new HttpsCertificate();
    Map<String, Object> dataMap = new HashMap<String, Object>();

    @Override
    public HttpsCertificate dataHttpsCertificate() {
        httpsCertificate.setC(lookCertificateC());
        httpsCertificate.setCn(lookCertificateCN());
        httpsCertificate.setO(lookCertificateOU());
        httpsCertificate.setOu(lookCertificateOU());
        httpsCertificate.setValidity(lookCertificateV());
        return httpsCertificate;
    }

    @Override
    public Map<String, Object> putdataHttpsCertificate() {
        /*
         * CS在创建Beacon HTTPS监听时，是包含SSL证书的，该证书是CS默认的证书，其中包含了CobaltStrike等关键字信息，不修改的情况下，会被直接监测到。
         * */
        dataMap.put("https_certificate_C",httpsCertificate.getC());
        dataMap.put("https_certificate_V",httpsCertificate.getValidity());
        dataMap.put("https_certificate_CN",httpsCertificate.getCn());
        dataMap.put("https_certificate_OU",httpsCertificate.getOu());
        dataMap.put("https_certificate_O",httpsCertificate.getO());
        return dataMap;
    }
    public String lookCertificateC(){
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

    public String lookCertificateV(){
        return "365";
    }

    public String lookCertificateCN(){
        return "www.bing.com";
    }
    public String lookCertificateOU(){
        return "bing.com";
    }
}

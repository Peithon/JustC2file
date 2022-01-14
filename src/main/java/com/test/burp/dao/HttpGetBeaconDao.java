package com.test.burp.dao;

import burp.IBurpExtenderCallbacks;
import burp.IHttpRequestResponse;
import com.test.burp.model.HttpGetBeacon;

import java.util.Map;
/**
 * @program: JustC2file
 * @author: Peithon
 * @create: 2022-01-08 09:09
 **/
public interface HttpGetBeaconDao {
    HttpGetBeacon dataHttpGetBeacon(IBurpExtenderCallbacks callbacks,IHttpRequestResponse message);
    Map<String, Object> putdataHttpGetBeacon();
}

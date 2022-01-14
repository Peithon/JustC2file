package com.test.burp.service;

import burp.IBurpExtenderCallbacks;
import burp.IHttpRequestResponse;
import com.test.burp.model.HttpGetBeacon;

import java.util.Map;

public interface HttpGetBeaconService {
    HttpGetBeacon dataHttpGetBeacon(IBurpExtenderCallbacks callbacks, IHttpRequestResponse message);
    Map<String, Object> putdataHttpGetBeacon();
}

package com.test.burp.service;

import burp.IBurpExtenderCallbacks;
import burp.IHttpRequestResponse;
import com.test.burp.model.HttpPostBeacon;

import java.util.Map;

public interface HttpPostBeaconService {
    HttpPostBeacon dataHttpPostBeacon(IBurpExtenderCallbacks callbacks, IHttpRequestResponse message);
    Map<String, Object> putdataHttpPostBeacon();
}

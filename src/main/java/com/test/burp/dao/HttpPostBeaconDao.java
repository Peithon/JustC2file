package com.test.burp.dao;

import burp.IBurpExtenderCallbacks;
import burp.IHttpRequestResponse;
import com.test.burp.model.HttpPostBeacon;

import java.util.Map;

public interface HttpPostBeaconDao {
    HttpPostBeacon dataHttpPostBeacon(IBurpExtenderCallbacks callbacks, IHttpRequestResponse message);
    Map<String, Object> putdataHttpPostBeacon();
}

package com.test.burp.service;

import burp.IBurpExtenderCallbacks;
import burp.IHttpRequestResponse;
import com.test.burp.model.HttpStager;

import java.util.Map;

public interface HttpStagerService {
    HttpStager dataHttpStager(IBurpExtenderCallbacks callbacks, IHttpRequestResponse message);
    Map<String, Object> putdataHttpStager();
}

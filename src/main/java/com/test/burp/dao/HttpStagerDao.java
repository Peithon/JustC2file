package com.test.burp.dao;

import burp.IBurpExtenderCallbacks;
import burp.IHttpRequestResponse;
import com.test.burp.model.HttpStager;

import java.util.Map;

public interface HttpStagerDao {
    HttpStager dataHttpStager(IBurpExtenderCallbacks callbacks, IHttpRequestResponse message);
    Map<String, Object> putdataHttpStager();
}

package com.test.burp.service.impl;

import burp.IBurpExtenderCallbacks;
import burp.IHttpRequestResponse;
import com.test.burp.dao.HttpGetBeaconDao;
import com.test.burp.dao.impl.HttpGetBeaconDaoImpl;
import com.test.burp.model.HttpGetBeacon;
import com.test.burp.service.HttpGetBeaconService;

import java.util.Map;

/**
 * @program: JustC2file
 * @author: Peithon
 * @github: https://github.com/Peithon/JustC2file
 * @create: 2022-01-10 15:25
 **/
public class HttpGetBeaconServiceImpl implements HttpGetBeaconService {
    private HttpGetBeaconDao httpGetBeaconDao = new HttpGetBeaconDaoImpl();
    @Override
    public HttpGetBeacon dataHttpGetBeacon(IBurpExtenderCallbacks callbacks, IHttpRequestResponse message) {
        return httpGetBeaconDao.dataHttpGetBeacon(callbacks,message);
    }

    @Override
    public Map<String, Object> putdataHttpGetBeacon() {
        return httpGetBeaconDao.putdataHttpGetBeacon();
    }
}

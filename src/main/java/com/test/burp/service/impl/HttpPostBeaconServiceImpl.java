package com.test.burp.service.impl;

import burp.IBurpExtenderCallbacks;
import burp.IHttpRequestResponse;
import com.test.burp.dao.HttpPostBeaconDao;
import com.test.burp.dao.impl.HttpPostBeaconDaoImpl;
import com.test.burp.model.HttpPostBeacon;
import com.test.burp.service.HttpPostBeaconService;

import java.util.Map;

/**
 * @program: JustC2file
 * @author: Peithon
 * @github: https://github.com/Peithon/JustC2file
 * @create: 2022-01-10 19:51
 **/
public class HttpPostBeaconServiceImpl implements HttpPostBeaconService {
    private HttpPostBeaconDao httpPostBeaconDao = new HttpPostBeaconDaoImpl();
    @Override
    public HttpPostBeacon dataHttpPostBeacon(IBurpExtenderCallbacks callbacks, IHttpRequestResponse message) {
        return httpPostBeaconDao.dataHttpPostBeacon(callbacks,message);
    }

    @Override
    public Map<String, Object> putdataHttpPostBeacon() {
        return httpPostBeaconDao.putdataHttpPostBeacon();
    }
}

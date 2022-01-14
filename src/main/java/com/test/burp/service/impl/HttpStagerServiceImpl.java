package com.test.burp.service.impl;

import burp.IBurpExtenderCallbacks;
import burp.IHttpRequestResponse;
import com.test.burp.dao.HttpStagerDao;
import com.test.burp.dao.impl.HttpStagerDaoImpl;
import com.test.burp.model.HttpStager;
import com.test.burp.service.HttpStagerService;

import java.util.Map;

/**
 * @program: JustC2file
 * @author: Peithon
 * @github: https://github.com/Peithon/JustC2file
 * @create: 2022-01-11 15:46
 **/
public class HttpStagerServiceImpl implements HttpStagerService {
    private HttpStagerDao httpStagerDao = new HttpStagerDaoImpl();
    @Override
    public HttpStager dataHttpStager(IBurpExtenderCallbacks callbacks, IHttpRequestResponse message) {
        return httpStagerDao.dataHttpStager(callbacks,message);
    }

    @Override
    public Map<String, Object> putdataHttpStager() {
        return httpStagerDao.putdataHttpStager();
    }
}

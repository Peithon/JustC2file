package com.test.burp.service.impl;

import com.test.burp.dao.HttpsCertificateDao;
import com.test.burp.dao.impl.HttpsCertificateDaoImpl;
import com.test.burp.model.HttpsCertificate;
import com.test.burp.service.HttpsCertificateService;

import java.util.Map;
/**
 * @program: JustC2file
 * @author: Peithon
 * @create: 2022-01-08 09:09
 **/
public class HttpsCertificateServiceImpl implements HttpsCertificateService {
    private HttpsCertificateDao httpsCertificateDao = new HttpsCertificateDaoImpl();
    @Override
    public HttpsCertificate dataHttpsCertificate() {
        return httpsCertificateDao.dataHttpsCertificate();
    }

    @Override
    public Map<String, Object> putdataHttpsCertificate() {
        this.dataHttpsCertificate();
        return httpsCertificateDao.putdataHttpsCertificate();
    }
}

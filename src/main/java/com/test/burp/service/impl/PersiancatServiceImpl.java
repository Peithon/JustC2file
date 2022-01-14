package com.test.burp.service.impl;

import com.test.burp.dao.PersiancatDao;
import com.test.burp.dao.impl.PersiancatDaoImpl;
import com.test.burp.model.Persiancat;
import com.test.burp.service.PersiancatService;

import java.util.Map;
/**
 * @program: JustC2file
 * @author: Peithon
 * @create: 2022-01-08 09:09
 **/
public class PersiancatServiceImpl implements PersiancatService {
    private PersiancatDao persiancatDao = new PersiancatDaoImpl();

    @Override
    public Persiancat dataPersiancat() {
        return persiancatDao.dataPersiancat();
    }

    @Override
    public Map<String, Object> putdataPersiancat() {
        this.dataPersiancat();
        return persiancatDao.putdataPersiancat();
    }

}

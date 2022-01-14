package com.test.burp.dao;

import com.test.burp.model.Persiancat;

import java.util.Map;
/**
 * @program: JustC2file
 * @author: Peithon
 * @create: 2022-01-08 09:09
 **/
public interface PersiancatDao {
    Persiancat dataPersiancat();
    Map<String, Object> putdataPersiancat();
}

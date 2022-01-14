package com.test.burp.service.impl;

import com.test.burp.dao.DnsBeaconDao;
import com.test.burp.dao.impl.DnsBeaconDaoImpl;
import com.test.burp.model.DnsBeacon;
import com.test.burp.service.DnsBeaconService;

import java.util.Map;

/**
 * @program: JustC2file
 * @author: Peithon
 * @github: https://github.com/Peithon/JustC2file
 * @create: 2022-01-13 16:37
 **/
public class DnsBeaconServiceImpl implements DnsBeaconService {
    private DnsBeaconDao dnsBeaconDao = new DnsBeaconDaoImpl();
    @Override
    public DnsBeacon dataDnsBeacon() {
        return dnsBeaconDao.dataDnsBeacon();
    }

    @Override
    public Map<String, Object> putdataDnsBeacon() {
        this.dataDnsBeacon();
        return dnsBeaconDao.putdataDnsBeacon();
    }
}

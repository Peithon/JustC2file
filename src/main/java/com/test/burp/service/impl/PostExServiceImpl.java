package com.test.burp.service.impl;

import com.test.burp.dao.PostExDao;
import com.test.burp.dao.impl.PostExDaoImpl;
import com.test.burp.model.PostEx;
import com.test.burp.service.PostExService;

import java.util.Map;
/**
 * @program: JustC2file
 * @author: Peithon
 * @create: 2022-01-08 09:09
 **/
public class PostExServiceImpl implements PostExService {
    private PostExDao postExDao = new PostExDaoImpl();
    @Override
    public PostEx dataPostEx() {
        return postExDao.dataPostEx();
    }

    @Override
    public Map<String, Object> putdataPostEx() {
        this.dataPostEx();
        return postExDao.putdataPostEx();
    }
}

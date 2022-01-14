package com.test.burp.dao.impl;

import com.test.burp.dao.PostExDao;
import com.test.burp.model.PostEx;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
/**
 * @program: JustC2file
 * @author: Peithon
 * @create: 2022-01-08 09:09
 **/
public class PostExDaoImpl implements PostExDao {
    private PostEx postEx = new PostEx();
    private LoadFile file = new LoadFile();
    Map<String, Object> dataMap = new HashMap<String, Object>();
    @Override
    public PostEx dataPostEx() {
        try {
            String spawnto = lookSpawnto();
            postEx.setSpawnto_x86(spawnto.split("\\s+")[0]);
            postEx.setSpawnto_x64(spawnto.split("\\s+")[1]);
            postEx.setPipename(file.readLine("pipename"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return postEx;
    }

    @Override
    public Map<String, Object> putdataPostEx() {
        dataMap.put("spawnto_x86",postEx.getSpawnto_x86());
        dataMap.put("spawnto_x64",postEx.getSpawnto_x64());
        dataMap.put("pipename",postEx.getPipename());
        return dataMap;
    }

    public String lookSpawnto() throws IOException {
        return file.readLine("spawnto");
    }
}

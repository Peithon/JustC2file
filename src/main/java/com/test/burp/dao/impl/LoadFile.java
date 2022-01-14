package com.test.burp.dao.impl;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Random;
/**
 * @program: JustC2file
 * @author: Peithon
 * @create: 2022-01-08 09:09
 **/
public class LoadFile {
    public String readLine(String filename) throws IOException{
        String line = "";
        InputStream input = this.getClass().getResourceAsStream("/wordlists/"+filename);
        try {
            //assert input != null;
            List<String> lines = IOUtils.readLines(input,"UTF-8");
            line = lines.get(new Random().nextInt(lines.size())).trim();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return line;
    }
}


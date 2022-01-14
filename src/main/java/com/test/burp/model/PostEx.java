package com.test.burp.model;
/**
 * @program: JustC2file
 * @author: Peithon
 * @create: 2022-01-08 09:09
 **/
public class PostEx {
    private String spawnto_x86;
    private String spawnto_x64;
    private String pipename;

    public String getSpawnto_x86() {
        return spawnto_x86;
    }

    public void setSpawnto_x86(String spawnto_x86) {
        this.spawnto_x86 = spawnto_x86;
    }

    public String getSpawnto_x64() {
        return spawnto_x64;
    }

    public void setSpawnto_x64(String spawnto_x64) {
        this.spawnto_x64 = spawnto_x64;
    }

    public String getPipename() {
        return pipename;
    }

    public void setPipename(String pipename) {
        this.pipename = pipename;
    }

    @Override
    public String toString() {
        return "PostEx{" +
                "spawnto_x86='" + spawnto_x86 + '\'' +
                ", spawnto_x64='" + spawnto_x64 + '\'' +
                ", pipename='" + pipename + '\'' +
                '}';
    }
}

package com.example.C_10_1.bean;

/**
 * Created by 付晓龙
 * on 2020-02-04
 * 热更新bean
 */
public class MCAppHotFixBean extends MCBaseUpdateBean {
    private String hotfix_build_id;
    private String hotfix_md5;


    public String getHotfix_build_id() {
        return hotfix_build_id;
    }

    public void setHotfix_build_id(String hotfix_build_id) {
        this.hotfix_build_id = hotfix_build_id;
    }

    public String getHotfix_md5() {
        return hotfix_md5;
    }

    public void setHotfix_md5(String hotfix_md5) {
        this.hotfix_md5 = hotfix_md5;
    }
}

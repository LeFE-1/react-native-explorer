package com.example.C_10_1.bean;

import java.io.Serializable;

/**
 * Created by 付晓龙
 * on 2020-02-04
 * 基础更新bean
 */
public class MCBaseUpdateBean implements Serializable {
    private String id;
    private String app_id;
    private String build_env;
    private String version_id;
    private String build_id;
    private String app_range;
    private String update_max_count;
    private String status;
    private String is_deleted;
    private String hit_count;
    private String c_t;
    private String c_u;
    private String u_t;
    private String u_u;
    private String description;
    private String app_md5;
    private String app_sha1;
    private String url;
    private String app_range_text;
    private int updateType;

    public String getDescription() {
        return description;
    }

    public String getApp_md5() {
        return app_md5;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getUpdateType() {
        return updateType;
    }

    public void setUpdateType(int updateType) {
        this.updateType = updateType;
    }

    @Override
    public String toString() {
        return "BaseUpdateBean{" +
                "id='" + id + '\'' +
                ", app_id='" + app_id + '\'' +
                ", build_env='" + build_env + '\'' +
                ", version_id='" + version_id + '\'' +
                ", build_id='" + build_id + '\'' +
                ", app_range='" + app_range + '\'' +
                ", update_max_count='" + update_max_count + '\'' +
                ", status='" + status + '\'' +
                ", is_deleted='" + is_deleted + '\'' +
                ", hit_count='" + hit_count + '\'' +
                ", c_t='" + c_t + '\'' +
                ", c_u='" + c_u + '\'' +
                ", u_t='" + u_t + '\'' +
                ", u_u='" + u_u + '\'' +
                ", description='" + description + '\'' +
                ", app_md5='" + app_md5 + '\'' +
                ", app_sha1='" + app_sha1 + '\'' +
                ", url='" + url + '\'' +
                ", app_range_text='" + app_range_text + '\'' +
                ", updateType=" + updateType +
                '}';
    }
}

package com.example.C_10_1.bean;

/**
 * Created by 付晓龙
 * on 2020-02-04
 * 更新结果bean
 */
public class MCUpdateResponseBean {
    public DataBean data;
    public int ret;
    public String msg;
    public int errCode;

    public static class DataBean {
        public MCAppHotFixBean hotfix;
    }
}

package com.example.C_10_1.interf;

import com.example.C_10_1.bean.MCUpdateResponseBean;

/**
 * Created by 付晓龙
 * on 2020-02-04
 */
public interface MCUpdateResponseListener {
    void onSuccess(MCUpdateResponseBean.DataBean bean);

    void onError(String msg);
}

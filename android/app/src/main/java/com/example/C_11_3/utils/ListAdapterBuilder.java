package com.example.C_11_3.utils;

import android.content.Context;

import com.example.C_11_3.ListAdapter;
import com.example.C_11_3.bean.ItemBean;
import com.facebook.react.ReactInstanceManager;

import java.util.ArrayList;

/**
 * Created by yangpan
 * on 2020-02-25
 */
public class ListAdapterBuilder {

    private ListAdapter mAdapter;

    public ListAdapterBuilder(ReactInstanceManager reactInstanceManager, Context context) {
        mAdapter = new ListAdapter(reactInstanceManager, context);
    }

    public ListAdapterBuilder setItemModuleName(String itemModuleName) {
        mAdapter.setItemModuleName(itemModuleName);
        return this;
    }

    public ListAdapterBuilder setData(ArrayList<ItemBean> data) {
        mAdapter.setData(data);
        return this;
    }

    public ListAdapterBuilder setItemHeight(int itemHeight) {
        mAdapter.setItemHeight(itemHeight);
        return this;
    }

    public ListAdapter build() {
        return mAdapter;
    }

}

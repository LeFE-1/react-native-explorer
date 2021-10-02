package com.example.C_11_3.utils;

import com.example.C_11_3.bean.ItemBean;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;

import java.util.ArrayList;

/**
 * Created by yangpan
 * on 2020-02-25
 */
public class DataConvertUtil {

    public static ArrayList<ItemBean> getDataFrom(ReadableArray data) {
        ArrayList<ItemBean> convertedData = new ArrayList<>();
        if (data == null) return convertedData;
        for (int i = 0; i < data.size(); i++) {
            ReadableMap element = data.getMap(i);
            if (element == null) continue;
            ItemBean itemBean = new ItemBean();
            if (element.hasKey("text")) {
                itemBean.text = element.getString("text");
            }
            convertedData.add(itemBean);
        }
        return convertedData;
    }

}

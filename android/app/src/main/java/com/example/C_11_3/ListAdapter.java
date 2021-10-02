package com.example.C_11_3;

import android.content.Context;
import android.os.Bundle;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.C_11_3.bean.ItemBean;
import com.facebook.react.ReactInstanceManager;
import com.facebook.react.ReactRootView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yangpan
 * on 2020-02-25
 */
public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ListItemViewHolder> {

    private ReactInstanceManager mReactInstanceManager;
    private Context mContext;
    private int mItemHeight;
    private String mItemModuleName;
    private ArrayList<ItemBean> data;
    // 用于回收释放
    private List<ReactRootView> mCycledViewList = new ArrayList<>();

    public ListAdapter(ReactInstanceManager reactInstanceManager, Context context) {
        mReactInstanceManager = reactInstanceManager;
        mContext = context;
    }

    @NonNull
    @Override
    public ListItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ReactRootView rootView = new ReactRootView(mContext);
        mCycledViewList.add(rootView); // 收集构造的 ReactRootView 用于最后批量销毁
        return new ListItemViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull ListItemViewHolder holder, int position) {
        Bundle props = new Bundle();
        props.putString("text", data.get(position).text);
        holder.bind(props);
    }

    private void startApp(ReactRootView rootView, Bundle props) {
        if (rootView == null) {
            rootView = new ReactRootView(mContext); // 内部的布局和渲染交给 RN 完成
        }
        // 此处指定了高度等布局信息，可以保证列表在滚动时的性能更好
        rootView.setLayoutParams(new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, getItemHeight()));
        rootView.startReactApplication(mReactInstanceManager, getItemModuleName(), props);
    }

    /**
     * 销毁所有缓存的 ReactRootView
     */
    public void destroyAllCacheViews() {
        for (ReactRootView rootView: mCycledViewList) {
            if (rootView != null) {
                rootView.unmountReactApplication();
            }
        }
        mCycledViewList.clear();
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    public void setData(ArrayList<ItemBean> data) {
        this.data = data;
    }

    public ArrayList<ItemBean> getData() {
        return data;
    }

    public String getItemModuleName() {
        return mItemModuleName;
    }

    public void setItemModuleName(String itemModuleName) {
        mItemModuleName = itemModuleName;
    }

    public int getItemHeight() {
        return mItemHeight;
    }

    public void setItemHeight(int itemHeight) {
        mItemHeight = itemHeight;
    }

    class ListItemViewHolder extends RecyclerView.ViewHolder {

        private ReactRootView mItemView;

        public ListItemViewHolder(@NonNull ReactRootView itemView) {
            super(itemView);
            mItemView = itemView;
        }

        public void bind(Bundle props) {
            if (mItemView.getReactInstanceManager() == null) {
                startApp(mItemView, props);
            } else {
                mItemView.setAppProperties(props);
            }
        }

    }

}

package com.example.C_11_3;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.C_11_3.utils.ListAdapterBuilder;
import com.example.R;
import com.facebook.react.ReactInstanceManager;

/**
 * Created by yangpan
 * on 2020-02-25
 */
public class LargeListView extends LinearLayout {

    private Context mContext;
    private ReactInstanceManager mReactInstanceManager;
    private ListAdapter mAdapter;
    private ListAdapterBuilder mAdapterBuilder;
    private RecyclerView mRecyclerView;

    public LargeListView(Context context, ReactInstanceManager reactInstanceManager) {
        super(context);
        mContext = context;
        mReactInstanceManager = reactInstanceManager;
        initView();
    }

    private void initView() {
        LayoutInflater.from(mContext).inflate(R.layout.large_list_view, this, true);
        mRecyclerView = findViewById(R.id.rv_large_list);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mAdapterBuilder = new ListAdapterBuilder(mReactInstanceManager, mContext);

    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        mAdapter = mAdapterBuilder.build();
        mRecyclerView.setAdapter(mAdapter);
    }

    public ListAdapter getAdapter() {
        return mAdapter;
    }

    public ListAdapterBuilder getAdapterBuilder() {
        return mAdapterBuilder;
    }

    /**
     * 销毁所有创建的 ReactRootView
     */
    public void destroyAllReactRootViews() {
        if (mAdapter != null && mAdapter.getData() != null && mAdapter.getData().size() != 0) {
            mAdapter.destroyAllCacheViews(); // 销毁列表缓存的所有 RN 视图
        }
    }
}

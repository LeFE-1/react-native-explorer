package com.example.C_8;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.R;
import com.facebook.infer.annotation.Assertions;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.uimanager.events.RCTEventEmitter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Created by yangpan
 * on 2020-01-07
 */
public class NativeListViewManager extends SimpleViewManager<RecyclerView> {
    public static final String EVENT_ON_ITEM_CLICKED = "onClickedItem";
    private static final int COMMAND_ID_SCROLL_TO_INDEX = 666;
    private ThemedReactContext mReactContext;


    /**
     * @return 原生端导出给 JS 端使用的原生组件名称
     * 可以在 JS 端通过 const NativeList = requireNativeComponent("ListView"); 引入封装的原生 UI 组件
     */
    @Nonnull
    @Override
    public String getName() {
        return "ListView";
    }

    /**
     * 创建原生 UI 组件实例
     *
     * @param reactContext 上下文对象
     * @return 原生 UI 组件实例
     */
    @Nonnull
    @Override
    protected RecyclerView createViewInstance(@Nonnull ThemedReactContext reactContext) {
        mReactContext = reactContext;
        RecyclerView listView = new RecyclerView(reactContext);
        listView.setAdapter(new ListAdapter());
        listView.setLayoutManager(new LinearLayoutManager(reactContext));
        listView.setId(R.id.native_list_view); // 设定 ViewId 用于原生 View 向 RN 发送事件
        return listView;
    }

    /**
     * 通过 @ReactProp 注解为 JS 端的 UI 组件增加一个自定义参数，name 为参数名称
     * 注解的方法必须具有 public void 关键词
     *
     * @param view 原生 UI 组件实例
     * @param data JS 端传入的自定义参数的值
     */
    @ReactProp(name = "dataSource")
    public void setData(RecyclerView view, ReadableArray data) {
        ArrayList<String> list = new ArrayList<>();
        for (Object content : data.toArrayList()) {
            list.add((String) content);
        }
        ((ListAdapter) view.getAdapter()).setData(list);
    }

    /**
     * 注册回调事件
     *
     * @return 原生组件与 JS 端的回调事件映射表
     */
    @Override
    @Nullable
    public Map<String, Object> getExportedCustomDirectEventTypeConstants() {
        MapBuilder.Builder<String, Object> builder = MapBuilder.builder();
        // 注意，这里使用 builder 传入的 key 为原生事件名称，value 为 JS 端映射的回调事件名称
        // 此处为了方便，使原生事件名称和 JS 端回调事件名称保持了一致，均为 onClickedItem
        // 原生端需要在事件发生时调用 ReactContext.getJSModule(RCTEventEmitter.class).receiveEvent() 方法将事件以 key 值注册的原生事件名称将事件进行发送
        // JS 端需要在组件的 props 中以 value 中 registrationName 对应的回调事件名称作为参数将事件及数据进行接收，如 <ListView onClickedItem={(data) => {console.log(data)}/>
        builder.put(EVENT_ON_ITEM_CLICKED, MapBuilder.of("registrationName", EVENT_ON_ITEM_CLICKED));
        return builder.build();
    }

    /**
     * 注册原生 UI 组件可提供的方法映射表
     *
     * @return 方法映射表
     */
    @Override
    public Map<String, Integer> getCommandsMap() {
        Map<String, Integer> commandsMap = new HashMap<>();
        // scrollTo 表示 JS 端组件可以调用的方法名称，COMMAND_ID_SCROLL_TO_INDEX 表示方法调用时原生端接收到的指令 ID
        commandsMap.put("scrollTo", COMMAND_ID_SCROLL_TO_INDEX);
        return commandsMap;
    }

    /**
     * 原生端接收到指令 ID 的处理
     *
     * @param view        原生 UI 组件
     * @param commandType JS 端调用组件方法时传递给原生的指令 ID
     * @param args        JS 端调用组件方法时，方法的入参
     */
    @Override
    public void receiveCommand(final RecyclerView view, int commandType, @Nullable ReadableArray args) {
        Assertions.assertNotNull(view);
        // 当 JS 端调用的方法对应的指令 ID 是 COMMAND_ID_SCROLL_TO_INDEX 时
        if (commandType == COMMAND_ID_SCROLL_TO_INDEX) {
            if (args == null) return;
            int position = args.getInt(0);
            view.smoothScrollToPosition(position); // 调用原生列表组件的方法完成滚动逻辑
        }
    }

    /**
     * 列表元素项的 Holder 类，用于管理元素项布局，并为列表元素项绑定数据
     */
    class ItemViewHolder extends RecyclerView.ViewHolder {
        private TextView tvContent;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            tvContent = itemView.findViewById(R.id.tv_example_name);
            itemView.setOnClickListener(v -> { // 列表元素项的点击事件
                // 拼接事件传递的数据
                WritableMap params = Arguments.createMap();
                params.putInt("index", this.getAdapterPosition());
                // 发送点击事件，JS 端即可通过回调接收到事件及传递的数据
                mReactContext.getJSModule(RCTEventEmitter.class).receiveEvent(
                    ((View) itemView.getParent()).getId(),
                    EVENT_ON_ITEM_CLICKED,
                    params);
            });
        }

        /**
         * 绑定数据的方法
         *
         * @param content 绑定的内容
         */
        public void bind(String content) {
            tvContent.setText(content);
        }
    }

    /**
     * 管理列表的适配器，负责将列表的每一个元素项进行创建、绑定和更新
     */
    class ListAdapter extends RecyclerView.Adapter<ItemViewHolder> {
        ArrayList<String> data;

        /**
         * 设置列表的数据源
         *
         * @param data 数据源
         */
        void setData(ArrayList<String> data) {
            this.data = data;
        }

        /**
         * 创建列表元素项布局，并将元素项布局传递到 Holder 中
         *
         * @param parent   列表组件
         * @param viewType 可以根据不同 Type 加载不同的元素项布局，此处没有使用
         * @return 具有元素项布局的 Holder
         */
        @NonNull
        @Override
        public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_example, parent, false);
            return new ItemViewHolder(itemView);
        }

        /**
         * 从列表数据源获取数据，通过 Holder 绑定至相应位置的列表元素项中
         *
         * @param holder   相应位置的列表元素项 Holder
         * @param position 相应的位置
         */
        @Override
        public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
            holder.bind(data.get(position));
        }

        /**
         * 列表长度
         *
         * @return 列表长度
         */
        @Override
        public int getItemCount() {
            return data == null ? 0 : data.size() - 1;
        }
    }
}

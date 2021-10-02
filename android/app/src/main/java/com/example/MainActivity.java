package com.example;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    private void initViews() {
        RecyclerView rv_example_list = findViewById(R.id.rv_example_list);
        rv_example_list.setLayoutManager(new LinearLayoutManager(this));
        ExampleAdapter exampleAdapter = new ExampleAdapter(RouterManager.getInstance().getRoutesList());
        rv_example_list.setAdapter(exampleAdapter);
    }

    class ExampleViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_example_name;

        public ExampleViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_example_name = itemView.findViewById(R.id.tv_example_name);
        }

        public void bind(String exampleName) {
            tv_example_name.setText(exampleName);
        }
    }

    public class ExampleAdapter extends RecyclerView.Adapter<ExampleViewHolder> {
        private ArrayList<String> mExampleList;


        public ExampleAdapter(ArrayList<String> exampleList) {
            mExampleList = exampleList;
        }

        @NonNull
        @Override
        public ExampleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_example, parent, false);
            return new ExampleViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(@NonNull ExampleViewHolder holder, int position) {
            String exampleName = mExampleList.get(position);
            holder.bind(exampleName);
            holder.itemView.setOnClickListener(v -> RouterManager.getInstance().startPage(exampleName));
        }

        @Override
        public int getItemCount() {
            return mExampleList == null ? 0 : mExampleList.size();
        }
    }

}
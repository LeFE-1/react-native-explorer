package com.example.C_2_4_2;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.R;

public class Activity extends AppCompatActivity {

    Button changeClipChildrenButton;
    Button goToRNButton;
    ViewGroup layout;

    boolean clipChildren = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        layout = (ViewGroup) LayoutInflater.from(this).inflate(R.layout.activity_2_4_2, null, false);
        setContentView(layout);

        changeClipChildrenButton = findViewById(R.id.change_clip_children_button);
        changeClipChildrenButton.setOnClickListener(v -> {
            changeClipChildrenButton.setText(clipChildren ? "父视图剪切子视图" : "子视图超出父视图");
            clipChildren = !clipChildren;
            layout.setClipChildren(clipChildren);
        });

        goToRNButton = findViewById(R.id.button2);
        goToRNButton.setOnClickListener(v -> {
            startActivity(new Intent(this, RNViewOverflowActivity.class));
        });
    }
}

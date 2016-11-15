package com.example.android.listviewdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button mStartArrayButton;
    private Button mStartSimpleButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mStartArrayButton = (Button) findViewById(R.id.btn_start_activity_array);
        mStartSimpleButton = (Button) findViewById(R.id.btn_start_activity_simple);

        mStartArrayButton.setOnClickListener(this);
        mStartSimpleButton.setOnClickListener(this);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_start_activity_array:
                startActivity(new Intent(this, ArrayAdapterActivity.class));
                break;
            case R.id.btn_start_activity_simple:
                startActivity(new Intent(this, SimpleAdapterActivity.class));
                break;
            default:
                break;
        }
    }
}
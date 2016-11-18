package com.example.android.listviewdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "MainActivity";
    private Button mStartArrayButton;
    private Button mStartSimpleButton;
    private Button mStartBaseButton;
    private Button mStartAutoButton;
    private Button mStartChatButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mStartArrayButton = (Button) findViewById(R.id.btn_start_activity_array);
        mStartSimpleButton = (Button) findViewById(R.id.btn_start_activity_simple);
        mStartBaseButton = (Button) findViewById(R.id.btn_start_activity_base);
        mStartAutoButton = (Button) findViewById(R.id.btn_start_activity_auto);
        mStartChatButton = (Button) findViewById(R.id.btn_start_activity_chat);

        mStartArrayButton.setOnClickListener(this);
        mStartSimpleButton.setOnClickListener(this);
        mStartBaseButton.setOnClickListener(this);
        mStartAutoButton.setOnClickListener(this);
        mStartChatButton.setOnClickListener(this);

    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_start_activity_array:
                startActivity(new Intent(this, ArrayAdapterActivity.class));
                break;
            case R.id.btn_start_activity_simple:
                startActivity(new Intent(this, SimpleAdapterActivity.class));
                break;
            case R.id.btn_start_activity_base:
                startActivity(new Intent(this, BaseAdapterActivity.class));
                break;
            case R.id.btn_start_activity_auto:
                startActivity(new Intent(this, AutoShowHideLayoutActivity.class));
                break;
            case R.id.btn_start_activity_chat:
                startActivity(new Intent(this, ChatListViewTestActivity.class));
                break;
            default:
                Log.e(TAG, "onClick: unknown id = " + v.getId());
                break;
        }
    }
}

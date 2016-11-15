package com.example.android.listviewdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ArrayAdapterActivity extends AppCompatActivity {
    private static final String TAG = "ArrayAdapterActivity";
    private ListView mListView;
    private String[] mNumbers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_array_adapter);
        mNumbers = getResources().getStringArray(R.array.numbers);

        mListView = (ListView) findViewById(R.id.lv_number);
        mListView.setAdapter(new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_single_choice,
                mNumbers));
        mListView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                /* i: View 在AdapterView中的位置
                *  l: View绑定的Item在data source中的位置
                *  此处是Array, 所以两者有相同的值*/
                Log.d(TAG, "onItemClick: i=" + i + ",l=" + l);
                setTitle("你点击了第" + i + "行");
            }
        });
    }
}

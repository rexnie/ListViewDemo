package com.example.android.listviewdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ArrayAdapterActivity extends AppCompatActivity {
    private ListView mListView;
    private String[] mNumbers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_array_adapter);
        mNumbers = getResources().getStringArray(R.array.numbers);

        mListView = (ListView) findViewById(R.id.lv_number);
        mListView.setAdapter(new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_checked,
                mNumbers));
        mListView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
    }
}

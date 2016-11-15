package com.example.android.listviewdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class SimpleAdapterActivity extends AppCompatActivity {
    private static final String TAG = "SimpleAdapterActivity";
    private ListView mListView;
    private String[] mNumbers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_array_adapter);

        mListView = (ListView) findViewById(R.id.lv_number);
        ArrayList<HashMap<String, Object>> listItem = new ArrayList<>();
        mNumbers = getResources().getStringArray(R.array.numbers);
        final int size = mNumbers.length;
        for (int i = 0; i < size; i++) {
            HashMap<String, Object> map = new HashMap<>();
            map.put("ItemImage", R.drawable.ic_setting);
            map.put("ItemTitle", "第" + (i + 1) + "行");
            map.put("ItemText", mNumbers[i]);
            listItem.add(map);
        }

        mListView.setAdapter(new SimpleAdapter(this,
                listItem, //要显示的数据源，a list of map
                R.layout.activity_simple_adapter, //每一项的layout
                new String[]{"ItemImage", "ItemTitle", "ItemText"}, //map中key
                new int[]{R.id.iv_item_image, R.id.tv_item_title, R.id.tv_item_text} //layout
                //中id
        ));

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

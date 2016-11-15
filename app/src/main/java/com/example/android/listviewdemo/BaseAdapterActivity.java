package com.example.android.listviewdemo;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class BaseAdapterActivity extends AppCompatActivity {
    private static final String TAG = "BaseAdapterActivity";
    private ListView mListView;
    private String[] mNumbers;
    private BaseAdapter mBaseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_array_adapter);

        mNumbers = getResources().getStringArray(R.array.numbers);
        mListView = (ListView) findViewById(R.id.lv_number);

        mBaseAdapter = new CustomAdapter(this);
        mListView.setAdapter(mBaseAdapter);
    }

    private class CustomAdapter extends BaseAdapter {
        private LayoutInflater mInflater;

        public CustomAdapter(Context c) {
            mInflater = LayoutInflater.from(c);
        }

        @Override
        public int getCount() {
            return mNumbers.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                convertView = mInflater.inflate(R.layout.activity_base_adapter, null);
                holder = new ViewHolder();
                /*得到各个控件的对象*/
                holder.itemTitle = (TextView) convertView.findViewById(R.id.tv_item_title);
                holder.itemText = (TextView) convertView.findViewById(R.id.tv_item_text);
                holder.itemButton = (Button) convertView.findViewById(R.id.btn_item_click);
                convertView.setTag(holder);//绑定ViewHolder对象
            } else {
                holder = (ViewHolder) convertView.getTag();
                holder.itemTitle.setText("第" + (position + 1) + "行");
                holder.itemText.setText(mNumbers[position]);

                holder.itemButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.d("MyListViewBase", "你点击了按钮" + position);
                    }
                });
            }
            return convertView;
        }
    }

    public final class ViewHolder {
        public TextView itemTitle;
        public TextView itemText;
        public Button itemButton;
    }
}

package com.example.android.listviewdemo;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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

        /* 数据源中item的个数 */
        @Override
        public int getCount() {
            return mNumbers.length;
        }

        /* 获取数据源中第position个item对应的数据 */
        @Override
        public String getItem(int position) {
            return mNumbers[position];
        }

        /* ListView 中位置position对应到数据源中的位置  */
        @Override
        public long getItemId(int position) {
            return position;
        }

        /**
         * 利用ListView会缓存View的特性，避免每次都重新layout View
         */
        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                //第一次创建View, 创建layout
                convertView = mInflater.inflate(R.layout.activity_base_adapter, null);
                holder = new ViewHolder();

                holder.itemTitle = (TextView) convertView.findViewById(R.id.tv_item_title);
                holder.itemText = (TextView) convertView.findViewById(R.id.tv_item_text);
                holder.itemButton = (Button) convertView.findViewById(R.id.btn_item_click);

                convertView.setTag(holder); //绑定ViewHolder对象到这个View
            } else {
                //此View已经缓存过了，通过tag找到他的布局
                holder = (ViewHolder) convertView.getTag();
            }

            if (holder != null) {
                // 更新View
                holder.itemTitle.setText("第" + (position + 1) + "行");
                holder.itemText.setText(getItem(position));

                holder.itemButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        setTitle("你点击了按钮" + position);
                    }
                });
            }

            return convertView;
        }
    }

    private final class ViewHolder {
        TextView itemTitle;
        TextView itemText;
        Button itemButton;
    }
}

package com.example.android.listviewdemo;

import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ChatListViewTestActivity extends AppCompatActivity {
    private static final String TAG = "ChatListViewTest";

    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_list_view_test);

        mListView = (ListView) findViewById(R.id.lv_list);

        List<ChatItemBean> datas = new ArrayList<>();
        Resources res = getResources();
        BitmapFactory.Options outOpts = new BitmapFactory.Options();
        outOpts.inSampleSize = 20;
        datas.add(new ChatItemBean(ChatItemBean.ITEM_TYPE_IN, "how are you?",
                BitmapFactory.decodeResource(res, R.drawable.chat_icon_in, outOpts)));

        datas.add(new ChatItemBean(ChatItemBean.ITEM_TYPE_OUT, "fine, thank you, and you?",
                BitmapFactory.decodeResource(res, R.drawable.chat_icon_out, outOpts)));

        datas.add(new ChatItemBean(ChatItemBean.ITEM_TYPE_IN, "i am fine too",
                BitmapFactory.decodeResource(res, R.drawable.chat_icon_in, outOpts)));

        datas.add(new ChatItemBean(ChatItemBean.ITEM_TYPE_OUT, "bye bye",
                BitmapFactory.decodeResource(res, R.drawable.chat_icon_out, outOpts)));

        datas.add(new ChatItemBean(ChatItemBean.ITEM_TYPE_IN, "See you",
                BitmapFactory.decodeResource(res, R.drawable.chat_icon_in, outOpts)));
        mListView.setAdapter(new ChatListViewAdapter(this, datas));
    }
}

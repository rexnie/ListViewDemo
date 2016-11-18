package com.example.android.listviewdemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by niedaocai on 18/11/2016.
 */

public class ChatListViewAdapter extends BaseAdapter {
    private List<ChatItemBean> mData;
    private LayoutInflater mInflater;

    public ChatListViewAdapter(Context context, List<ChatItemBean> data) {
        mData = data;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        ChatItemBean bean = (ChatItemBean) getItem(position);
        return bean.getType();
    }

    @Override
    public int getViewTypeCount() {
        return ChatItemBean.ITEM_TYPE_COUNT;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        ChatItemBean bean = (ChatItemBean) getItem(position);
        if (convertView == null) {
            holder = new ViewHolder();
            int type = getItemViewType(position);
            if (type == ChatItemBean.ITEM_TYPE_IN) {
                convertView = mInflater.inflate(R.layout.chat_item_in, null);
                holder.ivItem = (ImageView) convertView.findViewById(R.id.iv_item_in);
                holder.tvText = (TextView) convertView.findViewById(R.id.tv_text_in);
            } else if (type == ChatItemBean.ITEM_TYPE_OUT) {
                convertView = mInflater.inflate(R.layout.chat_item_out, null);
                holder.ivItem = (ImageView) convertView.findViewById(R.id.iv_item_out);
                holder.tvText = (TextView) convertView.findViewById(R.id.tv_text_out);
            }
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.ivItem.setImageBitmap(bean.getIcon());
        holder.tvText.setText(bean.getText());
        return convertView;
    }

    private final class ViewHolder {
        ImageView ivItem;
        TextView tvText;
    }
}

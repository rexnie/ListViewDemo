package com.example.android.listviewdemo;

import android.graphics.Bitmap;
import android.util.Log;

import static android.content.ContentValues.TAG;

/**
 * Created by niedaocai on 18/11/2016.
 */

public class ChatItemBean {
    public static final int ITEM_TYPE_IN = 0;
    public static final int ITEM_TYPE_OUT = 1;
    public static final int ITEM_TYPE_COUNT = 2;

    private int mType;
    private String mText;
    private Bitmap mIcon;

    public ChatItemBean(int type, String text, Bitmap icon) {
        if (type != ChatItemBean.ITEM_TYPE_IN && type != ChatItemBean.ITEM_TYPE_OUT) {
            Log.d(TAG, "newChatItem: type must be IN/OUT");
            return;
        }

        if (text.isEmpty()) {
            Log.d(TAG, "newChatItem: text can't be empty");
            return;
        }

        mType = type;
        mText = text;
        mIcon = icon;
    }

    public int getType() {
        return mType;
    }

    public void setType(int type) {
        mType = type;
    }

    public String getText() {
        return mText;
    }

    public void setText(String text) {
        mText = text;
    }

    public Bitmap getIcon() {
        return mIcon;
    }

    public void setIcon(Bitmap icon) {
        mIcon = icon;
    }
}

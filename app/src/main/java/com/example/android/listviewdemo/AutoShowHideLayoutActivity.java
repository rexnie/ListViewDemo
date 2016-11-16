package com.example.android.listviewdemo;
//http://www.jb51.net/article/79708.htm

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;

public class AutoShowHideLayoutActivity extends Activity {
    private static final String TAG = "AutoShowHideActivity";
    private ListView mListView;
    private String[] mNumbers;
    private int mTouchSlop;
    private float mFirstY;
    private float mCurrentY;
    private boolean mShow;
    private ObjectAnimator mAnimatorTitle;
    private ObjectAnimator mAnimatorList;
    private LinearLayout mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_auto_show_hide_layout);
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

        mTouchSlop = ViewConfiguration.get(this).getScaledTouchSlop();

        //mToolbar = getActionBar();

        mToolbar = (LinearLayout) findViewById(R.id.id_title);
        //setActionBar(mToolbar);
        /*View header = new View(this);
        header.setLayoutParams(new AbsListView.LayoutParams(
                        AbsListView.LayoutParams.MATCH_PARENT,
                        mToolbar.getHeight()
                )
        );
        mListView.addHeaderView(header);*/
        toolbarAnim(1);

        mListView.setOnTouchListener(mOnTouchListener);
    }

    View.OnTouchListener mOnTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            int direction = 0;
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    mFirstY = event.getY();
                    break;
                case MotionEvent.ACTION_MOVE:
                    mCurrentY = event.getY();

                    if (mCurrentY - mFirstY > mTouchSlop) {
                        direction = 0; //down
                    } else if (mFirstY - mCurrentY > mTouchSlop) {
                        direction = 1; //up
                    }

                    if (direction == 1) {
                        if (mShow) {
                            toolbarAnim(1);
                            mShow = !mShow;
                        }
                    } else if (direction == 0) {
                        if (!mShow) {
                            toolbarAnim(0);
                            mShow = !mShow;
                        }
                    }
                    break;
                case MotionEvent.ACTION_UP:
                    break;
                default:
                    break;
            }
            return false;
        }
    };

    private void toolbarAnim(int flag) {
        if (mAnimatorTitle != null && mAnimatorTitle.isRunning()) {
            mAnimatorTitle.cancel();
        }

        if (mAnimatorList != null && mAnimatorList.isRunning()) {
            mAnimatorList.cancel();
        }

        if (flag == 0) {
            mAnimatorTitle = ObjectAnimator.ofFloat(mToolbar, "TranslationY",
                    mToolbar.getTranslationY(), 0);
            mAnimatorList = ObjectAnimator.ofFloat(mListView, "TranslationY",
                    mListView.getTranslationY(),
                    22);
        } else {
            mAnimatorTitle = ObjectAnimator.ofFloat(mToolbar, "TranslationY",
                    mToolbar.getTranslationY(), -mToolbar.getHeight());
            mAnimatorList = ObjectAnimator.ofFloat(mListView, "TranslationY",
                    mListView.getTranslationY(), 0);
        }
        mAnimatorTitle.start();
        mAnimatorList.start();
    }
}

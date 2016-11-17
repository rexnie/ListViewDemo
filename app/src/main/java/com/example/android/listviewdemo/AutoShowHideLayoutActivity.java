package com.example.android.listviewdemo;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class AutoShowHideLayoutActivity extends AppCompatActivity {
    private static final String TAG = "AutoShowHideActivity";
    private ListView mListView;
    private String[] mNumbers;
    private int mTouchSlop;
    private float mFirstY;
    private float mCurrentY;
    private boolean mShow = true;
    private AnimatorSet mAnimatorSet;
    private Toolbar mToolbar;
    private FloatingActionButton mActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auto_show_hide_layout);
        mNumbers = getResources().getStringArray(R.array.numbers);

        mActionButton = (FloatingActionButton) findViewById(R.id.fab);
        mActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        mListView = (ListView) findViewById(R.id.list);
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

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        View header = new View(this);
        header.setLayoutParams(new AbsListView.LayoutParams(
                        AbsListView.LayoutParams.MATCH_PARENT,
                (int) getResources().getDimension(R.dimen.abc_action_bar_default_height_material)
                )
        );
        mListView.addHeaderView(header);

        mListView.setOnTouchListener(mOnTouchListener);

        mShow = true;
        toolbarAnim(false);
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
                        if (!mShow) {
                            toolbarAnim(true);
                            mShow = !mShow;
                        }
                    } else if (direction == 0) {
                        if (mShow) {
                            toolbarAnim(false);
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

    private void toolbarAnim(boolean hide) {
        if (mAnimatorSet != null && mAnimatorSet.isRunning()) {
            mAnimatorSet.cancel();
        }

        if (mAnimatorSet == null) {
            mAnimatorSet = new AnimatorSet();
            mAnimatorSet.setDuration(300);
        }
        ObjectAnimator toolbar, list, footer;

        if (hide) {
            toolbar = ObjectAnimator.ofFloat(mToolbar, "TranslationY",
                    mToolbar.getTranslationY(), -mToolbar.getHeight());
            list = ObjectAnimator.ofFloat(mListView, "TranslationY",
                    mListView.getTranslationY(),
                    0f);
            footer = ObjectAnimator.ofFloat(mActionButton, "TranslationY",
                    mActionButton.getTranslationY(),
                    mActionButton.getBottom() + mActionButton.getHeight());
        } else { //show
            toolbar = ObjectAnimator.ofFloat(mToolbar, "TranslationY",
                    mToolbar.getTranslationY(), 0f);
            list = ObjectAnimator.ofFloat(mListView, "TranslationY",
                    mListView.getTranslationY(), 0f);
            footer = ObjectAnimator.ofFloat(mActionButton, "TranslationY",
                    mActionButton.getTranslationY(), 0);
        }
        ArrayList<Animator> animations = new ArrayList<>();
        animations.add(toolbar);
        animations.add(list);
        animations.add(footer);
        mAnimatorSet.playTogether(animations);
        mAnimatorSet.start();
    }
}

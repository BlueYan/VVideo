package com.mark.vvideo.bilibili.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.astuetz.PagerSlidingTabStrip;
import com.mark.vvideo.R;
import com.mark.vvideo.base.BaseActivity;
import com.mark.vvideo.bilibili.adapter.DetailPagerAdapter;
import com.mvp.library.utils.LogUtils;

import butterknife.BindView;

/**
 * Author: Mark.
 * Date: 2016/9/20.
 * Function:
 */
public class DetailActivity extends BaseActivity {

    private static final String TAG = DetailActivity.class.getSimpleName();

    @BindView(R.id.id_toolbar)
    Toolbar mToolbar;

    @BindView(R.id.id_collapsingToolbar)
    CollapsingToolbarLayout mCollapsingToolbar;

    @BindView(R.id.tabs)
    PagerSlidingTabStrip mTabs;

    @BindView(R.id.id_viewpager)
    ViewPager mViewpager;

    private String mAid;

    private DetailPagerAdapter mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        mAid = getIntent().getStringExtra("aid");
        LogUtils.d("onCreate mAid = " + mAid);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_bili_detail;
    }

    @Override
    protected void initView() {
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        mCollapsingToolbar.setTitle("蕾姆");
        if ( mAdapter == null ) {
            mAdapter = new DetailPagerAdapter(getSupportFragmentManager(), mAid);
        }
        mViewpager.setOffscreenPageLimit(1);
        mViewpager.setAdapter(mAdapter);
        mTabs.setViewPager(mViewpager);
        initEvent();
    }

    private void initEvent() {
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}

package com.mark.vvideo.bilibili.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.Toolbar;

import com.mark.vvideo.R;
import com.mark.vvideo.base.BaseActivity;

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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
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
        mCollapsingToolbar.setTitle("12321312312");

    }
}

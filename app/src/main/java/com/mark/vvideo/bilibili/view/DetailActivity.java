package com.mark.vvideo.bilibili.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.astuetz.PagerSlidingTabStrip;
import com.facebook.drawee.view.SimpleDraweeView;
import com.mark.vvideo.R;
import com.mark.vvideo.base.BaseActivity;
import com.mark.vvideo.bilibili.adapter.DetailPagerAdapter;
import com.mark.vvideo.bilibili.contract.IntroductionContract;
import com.mark.vvideo.bilibili.model.entry.Introduction;
import com.mark.vvideo.bilibili.presenter.IntroductionPresenter;
import com.mvp.library.utils.LogUtils;

import butterknife.BindView;

/**
 * Author: Mark.
 * Date: 2016/9/20.
 * Function:
 */
public class DetailActivity extends BaseActivity implements IntroductionContract.View {

    private static final String TAG = DetailActivity.class.getSimpleName();

    @BindView(R.id.id_toolbar)
    Toolbar mToolbar;

    @BindView(R.id.id_collapsingToolbar)
    CollapsingToolbarLayout mCollapsingToolbar;

    @BindView(R.id.tabs)
    PagerSlidingTabStrip mTabs;

    @BindView(R.id.id_viewpager)
    ViewPager mViewpager;

    @BindView(R.id.id_sdv_pic)
    SimpleDraweeView mSdvPic;

    private String mAid;

    private DetailPagerAdapter mAdapter;

    private IntroductionContract.Presenter mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        mAid = getIntent().getStringExtra("aid");
        mPresenter = new IntroductionPresenter(this);
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
        initEvent();
        mPresenter.getIntroduction(Integer.valueOf(mAid));
    }

    private void initEvent() {
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void setIntroduction(Introduction introduction) {
        LogUtils.d("set introduction");
        if (mAdapter == null) {
            mAdapter = new DetailPagerAdapter(getSupportFragmentManager(), mAid, introduction);
            mViewpager.setOffscreenPageLimit(1);
            mViewpager.setAdapter(mAdapter);
            mTabs.setViewPager(mViewpager);
        }
        mSdvPic.setImageURI(introduction.getPic()); //设置封面图片
        mCollapsingToolbar.setTitle(introduction.getTitle());
        mCollapsingToolbar.setExpandedTitleTextAppearance(R.style.DetailActivity_Title);
    }

    @Override
    public void setPresenter(IntroductionContract.Presenter presenter) {
        this.mPresenter = presenter;
    }
}

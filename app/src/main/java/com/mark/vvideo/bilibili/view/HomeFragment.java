package com.mark.vvideo.bilibili.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.astuetz.PagerSlidingTabStrip;
import com.mark.vvideo.R;
import com.mark.vvideo.base.BaseFragment;
import com.mark.vvideo.bilibili.adapter.HomePagerAdapter;
import com.mark.vvideo.bilibili.contract.AllRankContract;
import com.mark.vvideo.bilibili.model.entry.AllRank;
import com.mark.vvideo.bilibili.presenter.AllRankPresenter;
import com.mvp.library.utils.LogUtils;

import java.util.List;

import butterknife.BindView;

/**
 * Author: Mark.
 * Date: 2016/9/18.
 * Function: Bilibili 首页
 */
public class HomeFragment extends BaseFragment implements AllRankContract.View {

    private static final String TAG = HomeFragment.class.getSimpleName();

    @BindView(R.id.tabs)
    PagerSlidingTabStrip mPagerSlidetags;

    @BindView(R.id.id_viewpager)
    ViewPager mViewpager;

    private AllRankContract.Presenter mPresenter; //持有P层接口对象

    private HomePagerAdapter mHomePagerAdapter;

    public static HomeFragment newInstance() {
        HomeFragment mFragment = new HomeFragment();
        return mFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mPresenter = new AllRankPresenter(this); //初始化P层
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_bilibili_home;
    }

    @Override
    protected void initView() {
        LogUtils.d("init bilibili view");
        mPresenter.setPagerTitle();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mPresenter.unsubscribe();
    }

    @Override
    public void setPresenter(AllRankContract.Presenter presenter) {
        mPresenter = presenter;
    }

    /**
     * 设置viewpager数据
     * @param allRanks
     */
    @Override
    public void setViewPager(List<AllRank> allRanks) {
        if ( mHomePagerAdapter == null ) {
            mHomePagerAdapter = new HomePagerAdapter(allRanks, getChildFragmentManager());
        }
        mViewpager.setAdapter(mHomePagerAdapter);
        mPagerSlidetags.setViewPager(mViewpager);
    }

}

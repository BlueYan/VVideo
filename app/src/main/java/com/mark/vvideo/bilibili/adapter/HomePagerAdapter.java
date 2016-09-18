package com.mark.vvideo.bilibili.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.mark.vvideo.bilibili.model.entry.AllRankModel;

import java.util.List;

/**
 * Author Mark
 * Date 16/9/18.
 * Function: 首页页面适配器
 */
public class HomePagerAdapter extends FragmentPagerAdapter {

    private static final String TAG = HomePagerAdapter.class.getSimpleName();

    private List<AllRankModel> allRankModels;

    public HomePagerAdapter(List<AllRankModel> allRankModels, FragmentManager fm) {
        super(fm);
        this.allRankModels = allRankModels;
    }

    @Override
    public Fragment getItem(int position) {
        return null;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return allRankModels.get(position).getSort_name();
    }

    @Override
    public int getCount() {
        return allRankModels.size();
    }
}

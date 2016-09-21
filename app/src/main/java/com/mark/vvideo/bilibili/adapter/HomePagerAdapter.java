package com.mark.vvideo.bilibili.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.mark.vvideo.bilibili.model.entry.AllRank;
import com.mark.vvideo.bilibili.view.CartoonFragment;

import java.util.List;

/**
 * Author Mark
 * Date 16/9/18.
 * Function: 首页页面适配器
 */
public class HomePagerAdapter extends FragmentPagerAdapter {

    private static final String TAG = HomePagerAdapter.class.getSimpleName();

    private List<AllRank> allRanks;

    public HomePagerAdapter(List<AllRank> allRanks, FragmentManager fm) {
        super(fm);
        this.allRanks = allRanks;
    }

    @Override
    public Fragment getItem(int position) {
        return CartoonFragment.newInstance(allRanks.get(position).getVideos());
    }

    @Override
    public CharSequence getPageTitle(int position) {
        CharSequence title = allRanks.get(position).getSort_name();
        return title;
    }

    @Override
    public int getCount() {
        return allRanks.size();
    }
}

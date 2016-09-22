package com.mark.vvideo.bilibili.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.mark.vvideo.bilibili.view.CommentFragment;
import com.mark.vvideo.bilibili.view.IntroductionFragment;
import com.mvp.library.utils.LogUtils;

/**
 * Author: Mark.
 * Date: 2016/9/22.
 * Function:
 */

public class DetailPagerAdapter extends FragmentPagerAdapter {

    private static final String[] titles = new String[]{"简介", "评论"};

    private String mAid;

    public DetailPagerAdapter(FragmentManager fm, String mAid) {
        super(fm);
        LogUtils.d("mAid = " + mAid);
        this.mAid = mAid;
    }

    @Override
    public Fragment getItem(int position) {
        switch ( position ) {
            case 0:
                return IntroductionFragment.newInstance(mAid);
            case 1:
                LogUtils.d("mAid = " + mAid);
                return CommentFragment.newInstance(mAid);
        }
        return null;
    }

    @Override
    public int getCount() {
        return titles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}

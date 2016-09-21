package com.mark.vvideo.bilibili.model.impl;

import com.mark.vvideo.bilibili.model.entry.AllRank;
import com.mark.vvideo.bilibili.model.iface.IAllRank;
import com.mark.vvideo.network.RetrofitManager;

import java.util.List;

import rx.Observable;

/**
 * Author: Mark.
 * Date: 2016/9/18.
 * Function: 实现获取特定的分类数据
 */
public class AllRankImpl implements IAllRank {

    private static final String TAG = AllRankImpl.class.getSimpleName();

    @Override
    public Observable<List<AllRank>> getAllRank() {
        return RetrofitManager.getInstance().getBiliApi().getAllRank();
    }
}

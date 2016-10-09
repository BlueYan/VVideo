package com.mark.vvideo.douyutv.model.impl;

import com.mark.vvideo.douyutv.model.entry.AllLive;
import com.mark.vvideo.douyutv.model.iface.IAllLive;
import com.mark.vvideo.network.RetrofitManager;

import java.util.List;

import rx.Observable;

/**
 * Author: Mark.
 * Date: 2016/9/22.
 * Function:
 */

public class AllLiveImpl implements IAllLive {

    private static final String TAG = AllLiveImpl.class.getSimpleName();

    public AllLiveImpl() {

    }

    @Override
    public Observable<AllLive> getAllLives(int limit, int offest) {
        return RetrofitManager.getInstance().getDouyuApi().getAllLives(limit, "android", offest);
    }
}

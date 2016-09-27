package com.mark.vvideo.bilibili.model.impl;

import com.mark.vvideo.bilibili.model.entry.Introduction;
import com.mark.vvideo.bilibili.model.iface.IIntroduction;
import com.mark.vvideo.network.RetrofitManager;

import rx.Observable;

/**
 * Author: Mark.
 * Date: 2016/9/22.
 * Function:
 */

public class IntroductionImpl implements IIntroduction {

    @Override
    public Observable<String> getVideoIntroduction(int aid) {
        return RetrofitManager.getInstance().getBiliApiWithNoGson().getIntroductions(aid);
    }
}

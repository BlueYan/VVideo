package com.mark.vvideo.bilibili.model.iface;

import com.mark.vvideo.bilibili.model.entry.Introduction;

import rx.Observable;

/**
 * Author: Mark.
 * Date: 2016/9/22.
 * Function:
 */

public interface IIntroduction {

    Observable<Introduction> getVideoIntroduction(int aid);

}

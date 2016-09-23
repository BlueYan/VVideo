package com.mark.vvideo.douyutv.model.iface;

import com.mark.vvideo.douyutv.model.entry.AllLive;

import java.util.List;

import rx.Observable;

/**
 * Author: Mark.
 * Date: 2016/9/22.
 * Function:
 */

public interface IAllLive {

    Observable<AllLive> getAllLives(int limit, int offest);

}

package com.mark.vvideo.bilibili.model.iface;

import com.mark.vvideo.bilibili.model.entry.AllRank;

import java.util.List;

import rx.Observable;

/**
 * Author: Mark.
 * Date: 2016/9/18.
 * Function:
 */
public interface IAllRank {

    Observable<List<AllRank>> getAllRank();

}

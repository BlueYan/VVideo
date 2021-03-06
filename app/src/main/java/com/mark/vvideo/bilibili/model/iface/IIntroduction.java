package com.mark.vvideo.bilibili.model.iface;

import com.mark.vvideo.bilibili.model.entry.Introduction;
import com.mark.vvideo.bilibili.model.entry.VideoInfo;

import master.flame.danmaku.danmaku.parser.BaseDanmakuParser;
import rx.Observable;

/**
 * Author: Mark.
 * Date: 2016/9/22.
 * Function:
 */

public interface IIntroduction {

    Observable<String> getVideoIntroduction(int aid);

    Observable<VideoInfo> getVideoInfo(int cid);

    Observable<BaseDanmakuParser> getDanmuInfo(int cid);
}

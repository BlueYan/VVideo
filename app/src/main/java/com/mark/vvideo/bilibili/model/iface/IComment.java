package com.mark.vvideo.bilibili.model.iface;

import com.mark.vvideo.bilibili.model.entry.Comment;

import java.util.List;

import rx.Observable;

/**
 * Author: Mark.
 * Date: 2016/9/21.
 * Function:
 */

public interface IComment {

    Observable<Comment> getComments(int aid, int page, int pageSize, int ver, String order);

}

package com.mark.vvideo.bilibili.model.impl;

import com.mark.vvideo.bilibili.model.entry.Comment;
import com.mark.vvideo.bilibili.model.iface.IComment;
import com.mark.vvideo.network.RetrofitManager;

import java.util.List;

import rx.Observable;

/**
 * Author: Mark.
 * Date: 2016/9/21.
 * Function:
 */

public class CommentImpl implements IComment {

    private static final String TAG = CommentImpl.class.getSimpleName();

    public CommentImpl() {

    }

    @Override
    public Observable<Comment> getComments(int aid, int page, int pageSize, int ver, String order) {
        return RetrofitManager.getInstance().getHostBiliApi().getComments(aid, page, pageSize, ver, order);
    }
}

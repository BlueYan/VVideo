package com.mark.vvideo.bilibili.contract;

import com.mark.vvideo.bilibili.model.entry.Comment;
import com.mvp.library.base.IBasePresenter;
import com.mvp.library.base.IBaseView;

import java.util.List;

/**
 * Author: Mark.
 * Date: 2016/9/21.
 * Function: 视频评论契约类
 */

public interface CommentContract {

    interface View extends IBaseView<Presenter> {
        void setComments(Comment mComment);
    }


    interface Presenter extends IBasePresenter {
        void getComments(int aid, int page, int pageSize, int ver, String order);
    }

}

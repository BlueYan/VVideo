package com.mark.vvideo.bilibili.contract;

import com.mvp.library.base.IBasePresenter;
import com.mvp.library.base.IBaseView;

/**
 * Author: Mark.
 * Date: 2016/9/21.
 * Function: 视频评论契约类
 */

public interface CommentContract {

    interface View extends IBaseView<Presenter> {

    }


    interface Presenter extends IBasePresenter {
        void getComments(int aid, int page, int pageSize, int ver);
    }

}

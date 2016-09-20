package com.mark.vvideo.bilibili.contract;

import com.mark.vvideo.bilibili.model.entry.AllRankModel;
import com.mvp.library.base.IBasePresenter;
import com.mvp.library.base.IBaseView;

import java.util.List;

/**
 * Author: Mark.
 * Date: 2016/9/18.
 * Function: 所有分类契约类
 */
public interface AllRankContract {


    interface View extends IBaseView<Presenter> {
        void setViewPager(List<AllRankModel> allRankModels);

    }

    interface Presenter extends IBasePresenter {
        void setPagerTitle();
    }
}

package com.mark.vvideo.bilibili.contract;

import com.mvp.library.base.IBasePresenter;
import com.mvp.library.base.IBaseView;

/**
 * Author: Mark.
 * Date: 2016/9/22.
 * Function:
 */

public interface IntroductionContract {

    interface View extends IBaseView<Presenter> {

    }

    interface Presenter extends IBasePresenter {
        void getIntroduction(int aid);
    }

}
package com.mark.vvideo.douyutv.contract;

import com.mvp.library.base.IBasePresenter;
import com.mvp.library.base.IBaseView;

/**
 * Author: Mark.
 * Date: 2016/9/22.
 * Function:
 */

public interface AllLiveContract {

    interface View extends IBaseView<Presenter> {

    }

    interface Presenter extends IBasePresenter {
        void getAllLives(int limit, int offest);
    }
}

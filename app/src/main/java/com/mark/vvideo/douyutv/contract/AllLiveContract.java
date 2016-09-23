package com.mark.vvideo.douyutv.contract;

import com.mark.vvideo.douyutv.model.entry.AllLive;
import com.mvp.library.base.IBasePresenter;
import com.mvp.library.base.IBaseView;

import java.util.List;

/**
 * Author: Mark.
 * Date: 2016/9/22.
 * Function:
 */

public interface AllLiveContract {

    interface View extends IBaseView<Presenter> {
        void setAllLives(AllLive mAllLive);
    }

    interface Presenter extends IBasePresenter {
        void getAllLives(int limit, int offest);
    }
}

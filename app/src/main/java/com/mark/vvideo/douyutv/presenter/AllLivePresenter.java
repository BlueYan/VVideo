package com.mark.vvideo.douyutv.presenter;

import com.mark.vvideo.douyutv.model.entry.AllLive;
import com.mark.vvideo.douyutv.model.iface.IAllLive;
import com.mark.vvideo.douyutv.model.impl.AllLiveImpl;
import com.mvp.library.base.BasePresenterImpl;
import com.mark.vvideo.douyutv.contract.AllLiveContract;

import java.util.List;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Author: Mark.
 * Date: 2016/9/22.
 * Function:
 */

public class AllLivePresenter extends BasePresenterImpl implements AllLiveContract.Presenter {

    private static final String TAG = AllLivePresenter.class.getSimpleName();

    private AllLiveContract.View mView;

    private IAllLive mAllLive;

    public AllLivePresenter(AllLiveContract.View mView) {
        this.mView = mView;
        mAllLive = new AllLiveImpl();
    }


    @Override
    public void getAllLives(int limit, int offest) {
        Subscription subscription = mAllLive.getAllLives(limit, offest)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<AllLive>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(List<AllLive> allLives) {

                    }
                });

        addSubscription(subscription);
    }

    @Override
    public void subscribe() {

    }
}

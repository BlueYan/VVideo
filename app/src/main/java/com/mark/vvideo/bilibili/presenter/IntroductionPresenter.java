package com.mark.vvideo.bilibili.presenter;

import com.mark.vvideo.bilibili.model.entry.Introduction;
import com.mark.vvideo.bilibili.model.iface.IIntroduction;
import com.mark.vvideo.bilibili.model.impl.IntroductionImpl;
import com.mvp.library.base.BasePresenterImpl;
import com.mark.vvideo.bilibili.contract.IntroductionContract;
import com.mvp.library.utils.LogUtils;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Author: Mark.
 * Date: 2016/9/22.
 * Function:
 */

public class IntroductionPresenter extends BasePresenterImpl implements IntroductionContract.Presenter {

    private static final String TAG = IntroductionPresenter.class.getSimpleName();

    private IIntroduction mIntroduction;

    private IntroductionContract.View mView;

    public IntroductionPresenter(IntroductionContract.View mView) {
        this.mView = mView;
        mIntroduction = new IntroductionImpl();
    }

    @Override
    public void getIntroduction(int aid) {
        Subscription s = mIntroduction.getVideoIntroduction(aid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Introduction>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(Introduction introduction) {
                        LogUtils.d("json = " + introduction.toString());
                    }
                });

        addSubscription(s);
    }

    @Override
    public void subscribe() {

    }
}

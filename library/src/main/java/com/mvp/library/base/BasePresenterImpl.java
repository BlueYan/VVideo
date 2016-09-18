package com.mvp.library.base;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * 创建人：
 * 创建时间： 2016/8/11 13
 * 功能概述:
 * 修改人：
 * 修改时间：
 */
public class BasePresenterImpl implements IBasePresenter {

    private CompositeSubscription mCompositeSubscription;

    protected void addSubscription(Subscription s) {
        if ( mCompositeSubscription == null ) {
            mCompositeSubscription = new CompositeSubscription();
        }
        this.mCompositeSubscription.add(s);
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onDestroy() {
        if ( mCompositeSubscription != null ) {
            mCompositeSubscription.unsubscribe();
        }
    }
}

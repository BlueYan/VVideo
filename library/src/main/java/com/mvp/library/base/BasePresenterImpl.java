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
public abstract class BasePresenterImpl implements IBasePresenter {

    private CompositeSubscription mCompositeSubscription;

    protected void addSubscription(Subscription s) {
        if ( mCompositeSubscription == null ) {
            mCompositeSubscription = new CompositeSubscription();
        }
        this.mCompositeSubscription.add(s);
    }

    /**
     * 用于开始绑定订阅事件
     * 例如获取页面数据并且调用V层显示
     */
    @Override
    public abstract void subscribe();

    @Override
    public void unsubscribe() {
        if ( mCompositeSubscription != null ) {
            mCompositeSubscription.unsubscribe();
        }
    }
}

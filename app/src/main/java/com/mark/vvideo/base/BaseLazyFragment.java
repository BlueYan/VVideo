package com.mark.vvideo.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Author: Mark.
 * Date: 2016/9/18.
 * Function:
 */
public abstract class BaseLazyFragment extends BaseFragment {

    private static final String TAG = BaseLazyFragment.class.getSimpleName();

    protected boolean isVisible;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if ( isVisibleToUser ) {
            isVisible = true;
            onVisible();
        } else {
            isVisible = false;
            onInVisible();
        }
    }

    protected void onVisible() {
        onLazyInit();
    }

    protected void onInVisible() {

    }

    /**
     * 懒加载 会比onCreateview生命周期方法先调用
     */
    protected abstract void onLazyInit();

}

package com.mark.vvideo.base;

/**
 * Author: Mark.
 * Date: 2016/9/18.
 * Function:
 */
public abstract class BaseLazyFragment extends BaseFragment {

    private static final String TAG = BaseLazyFragment.class.getSimpleName();

    protected boolean isVisible;

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
     * 懒加载
     */
    protected abstract void onLazyInit();

}

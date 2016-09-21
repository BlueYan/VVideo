package com.mark.vvideo.bilibili.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mark.vvideo.base.BaseLazyFragment;

/**
 * Author Mark
 * Date 16/9/21.
 * Function:
 */

public class IntroductionFragment extends BaseLazyFragment {

    private static final String TAG = IntroductionFragment.class.getSimpleName();

    public static IntroductionFragment newInstance() {
        IntroductionFragment mFragment = new IntroductionFragment();
        return mFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    protected void onLazyInit() {

    }

    @Override
    protected int getLayoutId() {
        return 0;
    }

    @Override
    protected void initView() {

    }
}

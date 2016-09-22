package com.mark.vvideo.douyutv.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.mark.vvideo.R;
import com.mark.vvideo.base.BaseFragment;
import com.mark.vvideo.douyutv.model.entry.AllLive;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Author: Mark.
 * Date: 2016/9/22.
 * Function:
 */

public class AllLiveFragment extends BaseFragment {

    private static final String TAG = AllLiveFragment.class.getSimpleName();

    @BindView(R.id.id_xRecyclerview)
    XRecyclerView mXRecyclerview;



    public static AllLiveFragment newInstance() {
        AllLiveFragment mFragment = new AllLiveFragment();
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
    protected int getLayoutId() {
        return R.layout.fragment_douyu_alllive;
    }

    @Override
    protected void initView() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}

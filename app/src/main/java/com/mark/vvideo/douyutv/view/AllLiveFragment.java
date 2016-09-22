package com.mark.vvideo.douyutv.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.mark.vvideo.R;
import com.mark.vvideo.base.BaseFragment;
import com.mark.vvideo.douyutv.adapter.AllLiveAdapter;
import com.mark.vvideo.douyutv.contract.AllLiveContract;
import com.mark.vvideo.douyutv.model.entry.AllLive;
import com.mark.vvideo.douyutv.presenter.AllLivePresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Author: Mark.
 * Date: 2016/9/22.
 * Function:
 */

public class AllLiveFragment extends BaseFragment implements AllLiveContract.View{

    private static final String TAG = AllLiveFragment.class.getSimpleName();

    @BindView(R.id.id_xRecyclerview)
    XRecyclerView mXRecyclerview;

    private AllLiveContract.Presenter mPresenter;

    private AllLiveAdapter mAdapter;

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
        mPresenter = new AllLivePresenter(this);
        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(getContext());
        mXRecyclerview.setLayoutManager(mLinearLayoutManager);
        mPresenter.getAllLives(20, 0);  //开始调用P层的获取数据代码
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mPresenter.unsubscribe();

    }

    @Override
    public void setPresenter(AllLiveContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void setAllLives(List<AllLive> mAllLives) {
        if ( mAdapter == null ) {
            mAdapter = new AllLiveAdapter(getContext(), mAllLives);
        }
        mXRecyclerview.setAdapter(mAdapter);
    }
}

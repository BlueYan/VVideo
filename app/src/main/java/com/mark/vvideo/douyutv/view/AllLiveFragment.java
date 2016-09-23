package com.mark.vvideo.douyutv.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.mark.vvideo.R;
import com.mark.vvideo.base.BaseFragment;
import com.mark.vvideo.douyutv.adapter.AllLiveAdapter;
import com.mark.vvideo.douyutv.contract.AllLiveContract;
import com.mark.vvideo.douyutv.model.entry.AllLive;
import com.mark.vvideo.douyutv.presenter.AllLivePresenter;
import com.mark.vvideo.util.DividerItemDecoration;
import com.mvp.library.utils.LogUtils;

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

    private static final int mLimit = 20;

    private int mOffest = 0;

    private List<AllLive.Data> mData;

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
        initRecyclerView();
        mPresenter.getAllLives(mLimit, mOffest);  //开始调用P层的获取数据代码
    }

    private void initRecyclerView() {
        final LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(getContext());
        mXRecyclerview.setLayoutManager(mLinearLayoutManager);
        mXRecyclerview.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL_LIST));
        mXRecyclerview.setLoadingMoreEnabled(true); //开启加载更多功能
        mXRecyclerview.setLoadingMoreProgressStyle(ProgressStyle.BallClipRotatePulse); //设置加载更多样式
        mXRecyclerview.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                mOffest = 0;
                mData.clear();
                mPresenter.getAllLives(mLimit, mOffest);
            }

            @Override
            public void onLoadMore() {
                mOffest = mOffest + 20;
                mPresenter.getAllLives(mLimit, mOffest);
            }
        });
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
    public void setAllLives(AllLive mAllLives) {
        if ( mAdapter == null ) {
            mData = mAllLives.getData();
            mAdapter = new AllLiveAdapter(getContext(), mData);
            mXRecyclerview.setAdapter(mAdapter);
        } else {
            if ( mData.size() == 0 ) {
                mXRecyclerview.refreshComplete();
            } else {
                mXRecyclerview.loadMoreComplete(); //通知加载完毕
            }
            mAdapter.addAll(mData.size(), mAllLives.getData());
        }

    }
}

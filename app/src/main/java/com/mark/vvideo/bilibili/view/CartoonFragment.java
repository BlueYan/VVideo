package com.mark.vvideo.bilibili.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.mark.vvideo.R;
import com.mark.vvideo.adapter.CommonAdapter;
import com.mark.vvideo.base.BaseLazyFragment;
import com.mark.vvideo.bilibili.adapter.CartoonAdapter;
import com.mark.vvideo.bilibili.contract.AllRankContract;
import com.mark.vvideo.bilibili.model.entry.AllRankModel;
import com.mvp.library.utils.LogUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Author: Mark.
 * Date: 2016/9/18.
 * Function:
 */
public class CartoonFragment extends BaseLazyFragment {

    private static final String TAG = CartoonFragment.class.getSimpleName();

    @BindView(R.id.id_rv)
    XRecyclerView mRv;

    private static List<AllRankModel.VideosBean> mVideosBeans;

    private CartoonAdapter mAdapter;

    public static CartoonFragment newInstance(List<AllRankModel.VideosBean> videosBeens) {
        mVideosBeans = videosBeens;
        CartoonFragment mFragment = new CartoonFragment();
        return mFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_cartoon;
    }


    @Override
    protected void initView() {
        LogUtils.d("onCreateView");
        GridLayoutManager manager = new GridLayoutManager(getContext(), 2);
        mRv.setLayoutManager(manager);
        if ( mAdapter == null ) {
            mAdapter = new CartoonAdapter(getContext(), mVideosBeans);
            mAdapter.setOnItemClickListener(new CommonAdapter.OnItemClickListener() {
                @Override
                public void onClickListener(View view, int position) {
                    Intent intent = new Intent(getContext(), DetailActivity.class);
                    startActivity(intent);
                }
            });
        }
        mRv.setAdapter(mAdapter);
    }

    @Override
    protected void onLazyInit() {
        LogUtils.d("lazy init");
    }
}

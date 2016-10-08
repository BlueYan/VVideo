package com.mark.vvideo.bilibili.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.mark.vvideo.R;
import com.mark.vvideo.adapter.CommonAdapter;
import com.mark.vvideo.base.BaseLazyFragment;
import com.mark.vvideo.bilibili.adapter.CartoonAdapter;
import com.mark.vvideo.bilibili.model.entry.AllRank;
import com.mvp.library.utils.LogUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Author: Mark.
 * Date: 2016/9/18.
 * Function:
 */
public class CartoonFragment extends BaseLazyFragment {

    private static final String TAG = CartoonFragment.class.getSimpleName();

    @BindView(R.id.id_rv)
    XRecyclerView mRv;

    private List<AllRank.VideosBean> mVideosBeans;

    private CartoonAdapter mAdapter;

    private boolean isPerpared = false;

    public static CartoonFragment newInstance(List<AllRank.VideosBean> videosBeans) {
        CartoonFragment mFragment = new CartoonFragment();
        Bundle args = new Bundle();
        args.putParcelableArrayList("videoBeans", (ArrayList<? extends Parcelable>) videosBeans);
        mFragment.setArguments(args);
        return mFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mVideosBeans = getArguments().getParcelableArrayList("videoBeans");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        isPerpared = true;
        onLazyInit();
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_cartoon;
    }


    @Override
    protected void initView() {
        GridLayoutManager manager = new GridLayoutManager(getContext(), 2);
        mRv.setLayoutManager(manager);
        mRv.setPullRefreshEnabled(false);
        if ( mAdapter == null ) {
            mAdapter = new CartoonAdapter(getContext(), mVideosBeans);
            mAdapter.setOnItemClickListener(new CommonAdapter.OnItemClickListener() {
                @Override
                public void onClickListener(View view, int position) {
                    Intent intent = new Intent(getContext(), DetailActivity.class);
                    intent.putExtra("aid", mVideosBeans.get(position -1).getAid());
                    startActivity(intent);
                }
            });
        }
        mRv.setAdapter(mAdapter);
    }

    @Override
    protected void onLazyInit() {
        if ( !isPerpared || !isVisible ) {
            return;
        }
    }
}

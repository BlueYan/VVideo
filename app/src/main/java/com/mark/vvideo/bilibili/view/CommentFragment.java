package com.mark.vvideo.bilibili.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.jcodecraeer.xrecyclerview.progressindicator.indicator.PacmanIndicator;
import com.mark.vvideo.R;
import com.mark.vvideo.base.BaseFragment;
import com.mark.vvideo.base.BaseLazyFragment;
import com.mark.vvideo.bilibili.adapter.CommentAdapter;
import com.mark.vvideo.bilibili.contract.CommentContract;
import com.mark.vvideo.bilibili.model.entry.Comment;
import com.mark.vvideo.bilibili.presenter.CommentPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Author: Mark.
 * Date: 2016/9/21.
 * Function: 评论页面
 */

public class CommentFragment extends BaseLazyFragment implements CommentContract.View {

    private static final String TAG = CommentFragment.class.getCanonicalName();

    @BindView(R.id.id_xRecyclerview)
    XRecyclerView mXRecyclerview;

    @BindView(R.id.id_msg_et)
    EditText mMsgEt;

    private int mAid;

    private int mPage = 1;

    private static final int mPageSize = 20;

    private static final int mVer = 3;

    private CommentAdapter mAdapter;

    private List<Comment> mComments;

    private CommentContract.Presenter mPresenter;


    public static CommentFragment newInstance(int aid) {
        CommentFragment mFragment = new CommentFragment();
        Bundle args = new Bundle();
        args.putInt("aid", aid);
        mFragment.setArguments(args);
        return mFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAid = getArguments().getInt("aid");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        mPresenter = new CommentPresenter(this);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    /**
     * 懒加载
     * 获取评论的数据
     */
    @Override
    protected void onLazyInit() {
        mPresenter.getComments(mAid, mPage, mPageSize, mVer);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_comment;
    }

    @Override
    protected void initView() {
        LinearLayoutManager mLinearManager = new LinearLayoutManager(getContext());
        mXRecyclerview.setLayoutManager(mLinearManager);
        if ( mAdapter == null ) {
            mAdapter = new CommentAdapter(getContext(), mComments);
        }
        mXRecyclerview.setAdapter(mAdapter);
    }


    @Override
    public void setPresenter(CommentContract.Presenter presenter) {
        this.mPresenter = presenter;
    }

    /**
     * 获取从P层返回的数据
     * @param mComments
     */
    @Override
    public void setComments(List<Comment> mComments) {
        this.mComments = mComments;
    }
}

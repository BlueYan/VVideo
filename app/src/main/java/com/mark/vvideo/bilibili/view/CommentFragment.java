package com.mark.vvideo.bilibili.view;

import android.os.Bundle;
import android.support.annotation.IntegerRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.mark.vvideo.R;
import com.mark.vvideo.base.BaseLazyFragment;
import com.mark.vvideo.bilibili.adapter.CommentAdapter;
import com.mark.vvideo.bilibili.adapter.HostCommentAdapter;
import com.mark.vvideo.bilibili.contract.CommentContract;
import com.mark.vvideo.bilibili.model.entry.Comment;
import com.mark.vvideo.bilibili.presenter.CommentPresenter;
import com.mark.vvideo.douyutv.contract.AllLiveContract;
import com.mark.vvideo.util.DividerItemDecoration;
import com.mvp.library.utils.LogUtils;

import java.util.List;

import butterknife.BindView;

/**
 * Author: Mark.
 * Date: 2016/9/21.
 * Function: 评论页面
 */

public class CommentFragment extends BaseLazyFragment implements CommentContract.View {

    private static final String TAG = CommentFragment.class.getCanonicalName();

    @BindView(R.id.id_xRecyclerview)
    XRecyclerView mXRecyclerview;

    private String mAid;

    private int mPage = 1;

    private static final int mPageSize = 20;

    private static final int mVer = 3;

    private CommentAdapter mAdapter;

    private HostCommentAdapter mHostCommentAdapter; //热门评论适配器

    private Comment mComment;

    private CommentContract.Presenter mPresenter;

    private boolean isPerpared = false;

    private View mHeaderView;

    private RecyclerView mHeaderRecyclerView;

    public static CommentFragment newInstance(String aid) {
        CommentFragment mFragment = new CommentFragment();
        Bundle args = new Bundle();
        args.putString("aid", aid);
        mFragment.setArguments(args);
        return mFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAid = getArguments().getString("aid");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        mPresenter = new CommentPresenter(this);
        isPerpared = true;
        onLazyInit();
        mHeaderView = inflater.inflate(R.layout.view_comment_header, container, false);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    /**
     * 懒加载
     * 获取评论的数据
     */
    @Override
    protected void onLazyInit() {
        if ( !isPerpared || !isVisible ) {
            LogUtils.d("CommentFragment lazy init");
            return;
        }
        mPresenter.getComments(Integer.valueOf(mAid), mPage, mPageSize, mVer, "hot");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_bili_comment;
    }

    @Override
    protected void initView() {
        LogUtils.d("CommentFragment initView");
        DividerItemDecoration mDivider = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL_LIST);
        LinearLayoutManager mLinearManager = new LinearLayoutManager(getContext());
        mXRecyclerview.setLayoutManager(mLinearManager);
        mXRecyclerview.setPullRefreshEnabled(false);
        mXRecyclerview.addItemDecoration(mDivider);

        //初始化头部Recyclerview
        LinearLayoutManager mHeaderLinearManager = new LinearLayoutManager(getContext()); //不能用同一个layout manager设置
        mHeaderRecyclerView = (RecyclerView) mHeaderView.findViewById(R.id.id_header_recyclerview);
        mHeaderRecyclerView.setLayoutManager(mHeaderLinearManager);
        mHeaderRecyclerView.addItemDecoration(mDivider);
    }


    @Override
    public void setPresenter(CommentContract.Presenter presenter) {
        this.mPresenter = presenter;
    }

    /**
     * 获取从P层返回的数据
     * @param mComment
     */
    @Override
    public void setComments(Comment mComment) {
        this.mComment = mComment;

        if ( mAdapter == null ) {
            mAdapter = new CommentAdapter(getContext(), mComment.getList());
        }
//        if ( mHostCommentAdapter == null ) {
//            mHostCommentAdapter = new HostCommentAdapter(getContext(), mComment.getHotList());
//            mHeaderRecyclerView.setAdapter(mHostCommentAdapter);
//            mXRecyclerview.addHeaderView(mHeaderView); //添加热门评论作为头部view
//        }
        LogUtils.d("set comments");
        mXRecyclerview.setAdapter(mAdapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mPresenter.unsubscribe();
    }
}

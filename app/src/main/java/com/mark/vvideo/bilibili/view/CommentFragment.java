package com.mark.vvideo.bilibili.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.mark.vvideo.R;
import com.mark.vvideo.base.BaseFragment;
import com.mark.vvideo.base.BaseLazyFragment;
import com.mark.vvideo.bilibili.adapter.CommentAdapter;
import com.mark.vvideo.bilibili.contract.CommentContract;

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

    private String mAid;

    private int mPage = 1;

    private static final String mPageSize = "20";

    private static final String mVer = "3";

    private CommentAdapter mAdapter;



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

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    protected void onLazyInit() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_comment;
    }

    @Override
    protected void initView() {

    }


    @Override
    public void setPresenter(CommentContract.Presenter presenter) {

    }
}

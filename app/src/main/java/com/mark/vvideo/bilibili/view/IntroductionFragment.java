package com.mark.vvideo.bilibili.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mark.vvideo.R;
import com.mark.vvideo.base.BaseLazyFragment;
import com.mark.vvideo.bilibili.contract.IntroductionContract;
import com.mark.vvideo.bilibili.model.entry.Introduction;
import com.mark.vvideo.bilibili.presenter.IntroductionPresenter;
import com.mvp.library.utils.LogUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Author Mark
 * Date 16/9/21.
 * Function: 简介界面
 */

public class IntroductionFragment extends BaseLazyFragment implements IntroductionContract.View {

    private static final String TAG = IntroductionFragment.class.getSimpleName();

    @BindView(R.id.textView)
    TextView mTitleView;

    private boolean isPrepared = false;

    private IntroductionContract.Presenter mPresenter;

    private String mAid;

    public static IntroductionFragment newInstance(String aid) {
        IntroductionFragment mFragment = new IntroductionFragment();
        Bundle args = new Bundle();
        args.putString("aid", aid);
        mFragment.setArguments(args);
        return mFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        mAid = getArguments().getString("aid");
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mPresenter = new IntroductionPresenter(this);
        isPrepared = true;
        onLazyInit();
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    protected void onLazyInit() {
        if (!isPrepared || !isVisible) {
            return;
        }
       // mPresenter.getIntroduction(Integer.valueOf(mAid));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_bili_introductaion;
    }

    @Override
    protected void initView() {

    }

    @Override
    public void setPresenter(IntroductionContract.Presenter presenter) {
        this.mPresenter = presenter;
    }

    @Override
    public void setIntroduction(Introduction introduction) {
        LogUtils.d("introduction.title = " + introduction.getTitle());
        mTitleView.setText(introduction.getTitle());
    }
}

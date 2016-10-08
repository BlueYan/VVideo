package com.mark.vvideo.bilibili.view;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.mark.vvideo.R;
import com.mark.vvideo.base.BaseLazyFragment;
import com.mark.vvideo.bilibili.adapter.TagAdapter;
import com.mark.vvideo.bilibili.contract.IntroductionContract;
import com.mark.vvideo.bilibili.model.entry.Introduction;
import com.mark.vvideo.bilibili.model.entry.Tag;
import com.mark.vvideo.util.Utils;
import com.mvp.library.utils.LogUtils;
import com.mvp.library.utils.SizeUtils;
import com.mvp.library.utils.TimeUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Author Mark
 * Date 16/9/21.
 * Function: 简介界面
 */

public class IntroductionFragment extends BaseLazyFragment {

    private static final String TAG = IntroductionFragment.class.getSimpleName();

    @BindView(R.id.id_tv_title)
    TextView mTvTitle;

    @BindView(R.id.id_tv_play_num)
    TextView mTvPlayNum;

    @BindView(R.id.id_tv_review_num)
    TextView mTvReviewNum;

    @BindView(R.id.id_tv_desc)
    TextView mTvDesc;

    @BindView(R.id.id_tv_share_num)
    TextView mTvShareNum;

    @BindView(R.id.id_ib_share)
    ImageButton mIbShare;

    @BindView(R.id.id_tv_coin_num)
    TextView mTvCoinNum;

    @BindView(R.id.id_ib_coin)
    ImageButton mIbCoin;

    @BindView(R.id.id_tv_collected_num)
    TextView mTvCollectedNum;

    @BindView(R.id.id_ib_collected)
    ImageButton mIbCollected;

    @BindView(R.id.id_tv_download_num)
    TextView mTvDownloadNum;

    @BindView(R.id.id_ib_download)
    ImageButton mIbDownload;

    @BindView(R.id.id_sdv_author_header)
    SimpleDraweeView mSdvAuthorHeader;

    @BindView(R.id.id_tv_author)
    TextView mTvAuthor;

    @BindView(R.id.id_tv_time)
    TextView mTvTime;

    @BindView(R.id.id_ll_action)
    LinearLayout idLlAction;

    @BindView(R.id.id_view_line)
    View idViewLine;

    @BindView(R.id.id_ll_video_tag)
    LinearLayout mLlVideoTag;


    private boolean isPrepared = false;

    private IntroductionContract.Presenter mPresenter;

    private String mAid;

    private TagAdapter mTagAdapter;

    private Introduction mIntroduction;

    public static IntroductionFragment newInstance(Introduction introduction, String aid) {
        IntroductionFragment mFragment = new IntroductionFragment();
        Bundle args = new Bundle();
        args.putString("aid", aid);
        args.putParcelable("introduction", introduction);
        mFragment.setArguments(args);
        return mFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        mAid = getArguments().getString("aid");
        mIntroduction = getArguments().getParcelable("introduction");
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        isPrepared = true;
        onLazyInit();
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    protected void onLazyInit() {
        if (!isPrepared || !isVisible) {
            return;
        }
        LogUtils.d("lazy init");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_bili_introductaion;
    }

    @Override
    protected void initView() {
        setIntroduction(mIntroduction);
    }

    public void setIntroduction(Introduction introduction) {
        mTvTitle.setText(introduction.getTitle());
        mTvPlayNum.setText(Utils.parseNumber(Integer.valueOf(introduction.getPlay())));
        mTvReviewNum.setText(Utils.parseNumber(Integer.valueOf(introduction.getVideoReview())));
        mTvDesc.setText(introduction.getDescription());
        mTvShareNum.setText(introduction.getReview());
        mTvCoinNum.setText(introduction.getCoins());
        mTvCollectedNum.setText(introduction.getFavorites());
        mSdvAuthorHeader.setImageURI(introduction.getFace());
        mTvAuthor.setText(introduction.getAuthor());
        mTvTime.setText(TimeUtils.getDateDiff(introduction.getCreatedAt(),
                new SimpleDateFormat("yyyy-MM-dd HH:mm")) + "投稿");
        String tagArray[] = introduction.getTag().split(",");
       /* for (int i = 0; i < tagArray.length; i++) {
            TextView mTag = new TextView(getContext());
            mTag.setText(tagArray[i]);
            mTag.setBackgroundColor(Color.parseColor("#CCCCCC"));
            mTag.setPadding(SizeUtils.dp2px(getContext(), 5), SizeUtils.dp2px(getContext(), 5),
                    SizeUtils.dp2px(getContext(), 5), SizeUtils.dp2px(getContext(), 5));
            mLlVideoTag.addView(mTag);
        }*/
    }

}

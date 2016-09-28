package com.mark.vvideo.bilibili.view;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TextView;

import com.astuetz.PagerSlidingTabStrip;
import com.facebook.drawee.view.SimpleDraweeView;
import com.mark.vvideo.R;
import com.mark.vvideo.base.BaseActivity;
import com.mark.vvideo.bilibili.adapter.DetailPagerAdapter;
import com.mark.vvideo.bilibili.contract.IntroductionContract;
import com.mark.vvideo.bilibili.model.entry.Introduction;
import com.mark.vvideo.bilibili.model.entry.VideoInfo;
import com.mark.vvideo.bilibili.presenter.IntroductionPresenter;
import com.mark.vvideo.widget.media.AndroidMediaController;
import com.mark.vvideo.widget.media.IjkVideoView;
import com.mvp.library.utils.LogUtils;

import butterknife.BindView;
import master.flame.danmaku.ui.widget.DanmakuView;
import tv.danmaku.ijk.media.player.IMediaPlayer;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;

/**
 * Author: Mark.
 * Date: 2016/9/20.
 * Function:
 */
public class DetailActivity extends BaseActivity implements IntroductionContract.View {

    private static final String TAG = DetailActivity.class.getSimpleName();

    @BindView(R.id.id_toolbar)
    Toolbar mToolbar;

    @BindView(R.id.id_collapsingToolbar)
    CollapsingToolbarLayout mCollapsingToolbar;

    @BindView(R.id.tabs)
    PagerSlidingTabStrip mTabs;

    @BindView(R.id.id_viewpager)
    ViewPager mViewpager;

    @BindView(R.id.id_sdv_pic)
    SimpleDraweeView mSdvPic;

    @BindView(R.id.id_ijkplayer_view)
    IjkVideoView mIjkplayerView;

    @BindView(R.id.id_danmuku_view)
    DanmakuView mDanmukuView;

    @BindView(R.id.id_loading_progress)
    ProgressBar mLoadingProgress;

    @BindView(R.id.id_loading_text)
    TextView mLoadingText;

    @BindView(R.id.bili_anim)
    ImageView mBiliAnim;

    @BindView(R.id.video_start_info)
    TextView mVideoStartInfo;

    @BindView(R.id.id_appbarLayout)
    AppBarLayout idAppbarLayout;

    @BindView(R.id.id_cl)
    CoordinatorLayout idCl;

    @BindView(R.id.id_play_btn)
    FloatingActionButton mPlayBtn;

    @BindView(R.id.buffering_indicator)
    RelativeLayout bufferingIndicator;

    @BindView(R.id.video_start)
    RelativeLayout mVideoStart;

    private String mAid;

    private DetailPagerAdapter mAdapter;

    private IntroductionContract.Presenter mPresenter;

    private String mVideoUrl;

    private Introduction mIntroduction;

    private View mVideoView;

    private boolean mBackPressed;

    private AndroidMediaController mMediaController; //控制器

    private AnimationDrawable mLoadingAnim; //动画

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        mAid = getIntent().getStringExtra("aid");
        mPresenter = new IntroductionPresenter(this);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_bili_detail;
    }

    @Override
    protected void initView() {
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        initEvent();
        mPresenter.getIntroduction(Integer.valueOf(mAid));
        initPlayer();
    }

    private void initPlayer() {
        mVideoView = findViewById(R.id.id_video_view);
        //init ijk library
        IjkMediaPlayer.loadLibrariesOnce(null);
        IjkMediaPlayer.native_profileBegin("libijkplayer.so");
        mMediaController = new AndroidMediaController(this, false);
        mIjkplayerView.setMediaController(mMediaController);

        mIjkplayerView.setOnErrorListener(new IMediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(IMediaPlayer iMediaPlayer, int i, int i1) {
                return false;
            }
        });
    }

    private void initEvent() {
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mPlayBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSdvPic.setVisibility(View.GONE);
                mPresenter.getVideoInfo(mIntroduction.getListBean().getCid());
            }
        });
    }

    @Override
    public void setIntroduction(Introduction introduction) {
        mIntroduction = introduction;
        LogUtils.d("set introduction");
        if (mAdapter == null) {
            mAdapter = new DetailPagerAdapter(getSupportFragmentManager(), mAid, introduction);
            mViewpager.setOffscreenPageLimit(1);
            mViewpager.setAdapter(mAdapter);
            mTabs.setViewPager(mViewpager);
        }
        mSdvPic.setImageURI(introduction.getPic()); //设置封面图片
        mCollapsingToolbar.setTitle(introduction.getTitle());
        mCollapsingToolbar.setExpandedTitleTextAppearance(R.style.DetailActivity_Title);
    }

    @Override
    public void setVideoInfo(VideoInfo videoInfo) {
        LogUtils.d("set video info");
        mVideoUrl = videoInfo.getDurl().get(0).getUrl();
        if (mIjkplayerView != null) {
            mIjkplayerView.setOnPreparedListener(new IMediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(IMediaPlayer iMediaPlayer) {
                    LogUtils.d("onPrepared");
                    bufferingIndicator.setVisibility(View.VISIBLE);
                    mVideoStart.setVisibility(View.VISIBLE);
                    mLoadingAnim = (AnimationDrawable) mBiliAnim.getBackground();
                    mLoadingAnim.start();
                }
            });
            mIjkplayerView.setOnInfoListener(new IMediaPlayer.OnInfoListener() {
                @Override
                public boolean onInfo(IMediaPlayer iMediaPlayer, int what, int extra) {

                    return false;
                }
            });
            mIjkplayerView.setOnCompletionListener(new IMediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(IMediaPlayer iMediaPlayer) {
                    LogUtils.d("onCompletion");
                }
            });
            mIjkplayerView.setVideoPath(mVideoUrl);
            mIjkplayerView.start(); //开始播放
        }
    }

    @Override
    public void setPresenter(IntroductionContract.Presenter presenter) {
        this.mPresenter = presenter;
    }

    @Override
    public void onBackPressed() {
        mBackPressed = true;
        super.onBackPressed();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mBackPressed || !mIjkplayerView.isBackgroundPlayEnabled()) {
            mIjkplayerView.stopPlayback();
            mIjkplayerView.release(true);
            mIjkplayerView.stopBackgroundPlay();
        } else {
            mIjkplayerView.enterBackground();
        }
        IjkMediaPlayer.native_profileEnd();

    }
}

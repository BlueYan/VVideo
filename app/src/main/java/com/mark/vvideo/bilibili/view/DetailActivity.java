package com.mark.vvideo.bilibili.view;

import android.content.pm.ActivityInfo;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
import com.mark.vvideo.widget.media.CustomMediaController;
import com.mark.vvideo.widget.media.IjkVideoView;
import com.mvp.library.utils.LogUtils;
import com.mvp.library.utils.ScreenUtils;
import com.mvp.library.utils.SizeUtils;

import org.xml.sax.SAXParseException;

import butterknife.BindView;
import master.flame.danmaku.controller.DrawHandler;
import master.flame.danmaku.danmaku.model.BaseDanmaku;
import master.flame.danmaku.danmaku.model.DanmakuTimer;
import master.flame.danmaku.danmaku.model.android.DanmakuContext;
import master.flame.danmaku.danmaku.parser.BaseDanmakuParser;
import master.flame.danmaku.ui.widget.DanmakuView;
import tv.danmaku.ijk.media.player.IMediaPlayer;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;

/**
 * Author: Mark.
 * Date: 2016/9/20.
 * Function:
 */
public class DetailActivity extends BaseActivity implements IntroductionContract.View,
        IMediaPlayer.OnErrorListener, IMediaPlayer.OnPreparedListener, IMediaPlayer.OnInfoListener,
            IMediaPlayer.OnCompletionListener, CustomMediaController.OnOrientationChangeListener {

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
    AppBarLayout mAppbarLayout;

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

    //private AndroidMediaController mMediaController; //控制器

    private CustomMediaController mMediaController;

    private AnimationDrawable mLoadingAnim; //动画

    private int mLastPosition = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        mAid = getIntent().getStringExtra("aid");
        LogUtils.d("aid = " + mAid);
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
        mMediaController = new CustomMediaController(this);
        mMediaController.setOnOrientationChangeListener(this);
        mIjkplayerView.setMediaController(mMediaController);
        mIjkplayerView.setOnPreparedListener(this);
        mIjkplayerView.setOnInfoListener(this);
        mIjkplayerView.setOnCompletionListener(this);
        mIjkplayerView.setOnErrorListener(this);
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
                mVideoView.setVisibility(View.VISIBLE);
                mVideoStart.setVisibility(View.VISIBLE);
                mLoadingAnim = (AnimationDrawable) mBiliAnim.getBackground();
                mLoadingAnim.start();
                mPresenter.getVideoInfo(mIntroduction.getListBean().getCid());
                //获取弹幕信息
                mPresenter.getDanmuInfo(mIntroduction.getListBean().getCid());
            }
        });
    }

    @Override
    public void setIntroduction(Introduction introduction) {
        mIntroduction = introduction;
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


    /**
     * 设置播放路径，开始播放
     * @param videoInfo
     */
    @Override
    public void setVideoInfo(VideoInfo videoInfo) {
        LogUtils.d("set video info");
        mVideoUrl = videoInfo.getDurl().get(0).getUrl();
        LogUtils.d("url = " + mVideoUrl);
        if (mIjkplayerView != null) {
            mCollapsingToolbar.setTitle(" ");
            mIjkplayerView.setVideoPath(mVideoUrl);
            mIjkplayerView.start(); //开始播放
        }
    }

    /**
     * 设置弹幕信息
     */
    @Override
    public void setDanmuInfo(BaseDanmakuParser parser) {
        if ( mDanmukuView != null ) {
            /**
             * 之所以在这里加上try catch 语句块
             * 是因为解析xml时候 报org.xml.sax.SAXParseException这个错误
             * 在strackoverflow http://stackoverflow.com/questions/2012127/saxparseexception-in-android 上
             * 貌似存在的一个bug 加上后就可以了。
             */
            try {
                mDanmukuView.prepare(parser, DanmakuContext.create());
                mDanmukuView.showFPS(false);
                mDanmukuView.enableDanmakuDrawingCache(false);
                mDanmukuView.setCallback(new DrawHandler.Callback() {
                    @Override
                    public void prepared() {
                        LogUtils.d("danmu prepared");
                    }

                    @Override
                    public void updateTimer(DanmakuTimer timer) {

                    }

                    @Override
                    public void danmakuShown(BaseDanmaku danmaku) {

                    }

                    @Override
                    public void drawingFinished() {

                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
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
        if ( mDanmukuView != null ) {
            mDanmukuView.release();
            mDanmukuView = null;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if ( mIjkplayerView != null && !mIjkplayerView.isPlaying() ) {
            mIjkplayerView.seekTo(mLastPosition);
        }
        if ( mDanmukuView != null && mDanmukuView.isPrepared() && mDanmukuView.isPaused() ) {
            mDanmukuView.resume();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if ( mIjkplayerView != null ) {
            mLastPosition = mIjkplayerView.getCurrentPosition();  //记录当前视频位置
            mIjkplayerView.pause();
        }
        if ( mDanmukuView != null && mDanmukuView.isPrepared() ) {
            mDanmukuView.pause();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if ( mBackPressed || !mIjkplayerView.isBackgroundPlayEnabled() ) {
            mIjkplayerView.stopPlayback();
            mIjkplayerView.release(true);
            mIjkplayerView.stopBackgroundPlay();
        } else {
            mIjkplayerView.enterBackground();
        }
        IjkMediaPlayer.native_profileEnd();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if ( mLoadingAnim != null ) {
            mLoadingAnim.stop();
            mLoadingAnim = null;
        }
        if ( mMediaController != null ) {
            mMediaController.onDestroy();
        }
        if ( mDanmukuView != null ) {
            mDanmukuView.release();
            mDanmukuView = null;
        }
    }

    /**
     * 视频播放错误
     * @param iMediaPlayer
     * @param i
     * @param i1
     * @return
     */
    @Override
    public boolean onError(IMediaPlayer iMediaPlayer, int i, int i1) {
        LogUtils.d("i = " + i + " i1 = " + i1);
        return false;
    }

    /**
     * 播放视频准备
     * @param iMediaPlayer
     */
    @Override
    public void onPrepared(IMediaPlayer iMediaPlayer) {
        LogUtils.d("on prepared");
        mLoadingAnim.stop(); //停止动画
        mVideoStart.setVisibility(View.GONE);
        mDanmukuView.start(); //开启弹幕
    }

    /**
     *
     * 视频缓冲
     * @param iMediaPlayer
     * @param what
     * @param i1
     * @return
     */
    @Override
    public boolean onInfo(IMediaPlayer iMediaPlayer, int what, int i1) {
        if ( what == iMediaPlayer.MEDIA_INFO_BUFFERING_START ) {
            if ( bufferingIndicator != null ) {
                bufferingIndicator.setVisibility(View.VISIBLE);
            }
        } else if ( what == iMediaPlayer.MEDIA_INFO_BUFFERING_END ) {
            if ( bufferingIndicator != null && bufferingIndicator.getVisibility() == View.VISIBLE ) {
                bufferingIndicator.setVisibility(View.GONE);
            }
        }
        return true;
    }

    /**
     * 视频播放完成
     * @param iMediaPlayer
     */
    @Override
    public void onCompletion(IMediaPlayer iMediaPlayer) {
        if ( bufferingIndicator != null ) {
            bufferingIndicator.setVisibility(View.GONE);
        }
        if ( mVideoView != null ) {
            mVideoView.setVisibility(View.GONE);
        }
        if ( mVideoStart != null ) {
            mVideoStart.setVisibility(View.GONE);
        }
        if ( mSdvPic != null ) {
            mSdvPic.setVisibility(View.VISIBLE);
        }
        mCollapsingToolbar.setTitle(mIntroduction.getTitle());
    }

    /**
     * 动态切换横竖屏
     * @param screen
     */
    @Override
    public void onChangeListener(int screen) {
        LogUtils.d("screen = " + screen);
        if ( screen == CustomMediaController.SCREEN_PROTRAIT ) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            CoordinatorLayout.LayoutParams mProtraitLayoutParams
                    = new CoordinatorLayout.LayoutParams(CoordinatorLayout.LayoutParams.MATCH_PARENT, SizeUtils.dp2px(this, 200));
            mAppbarLayout.setLayoutParams(mProtraitLayoutParams);
        } else if ( screen == CustomMediaController.SCREEN_LANDSCAPE ) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
            CoordinatorLayout.LayoutParams mProtraitLayoutParams
                    = new CoordinatorLayout.LayoutParams(ScreenUtils.getScreenWidth(this), ScreenUtils.getScreenHeight(this));
            mAppbarLayout.setLayoutParams(mProtraitLayoutParams);
        }
    }
}

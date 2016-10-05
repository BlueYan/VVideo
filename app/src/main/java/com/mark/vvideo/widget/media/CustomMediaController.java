package com.mark.vvideo.widget.media;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.OrientationEventListener;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.SeekBar;
import android.widget.TextView;

import com.mark.vvideo.R;
import com.mvp.library.utils.LogUtils;
import com.mvp.library.utils.SizeUtils;

import java.util.Formatter;
import java.util.Locale;

import tv.danmaku.ijk.media.player.IMediaPlayer;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;

/**
 * Author: Mark.
 * Date: 2016/9/29.
 * Function: 自定义视频控制器
 */

public class CustomMediaController extends MediaController implements IMediaController {

    private static final String TAG = CustomMediaController.class.getSimpleName();

    private View mView;

    private ImageView mIvPlay;

    private TextView mTvStart;

    private TextView mTvEnd;

    private SeekBar mSeekBar;

    private ImageView mIvFullScreen;

    private boolean mDragging; //拖动进度条

    private MediaPlayerControl mPlayer;

    private StringBuilder mFormatBuilder = new StringBuilder();

    private Formatter mFormatter = new Formatter(mFormatBuilder, Locale.getDefault());

    private static final int sDefaultTimeout = 5000;

    private static final int FADE_OUT = 1;

    private static final int SHOW_PROGRESS = 2;  //显示进度值

    private OrientationEventListener mOrientationListener; //屏幕旋转监听器

    private boolean mScreenProtrait = true;

    private boolean mCurrentOrient = false;

    public static final int SCREEN_PROTRAIT = 101; //竖屏状态

    public static final int SCREEN_LANDSCAPE = 202; //横屏状态

    private OnOrientationChangeListener mOrientationChangeListener;  //实现转屏监听器

    private Context mContext;

    public CustomMediaController(Context context) {
        super(context);
        this.mContext = context;
        initView(context);
        //startOrientationChangeListener(); //开启转屏监听器
    }

    public CustomMediaController(Context context, boolean useFastForward) {
        super(context, useFastForward);
        initView(context);
    }

    private void initView(Context context) {
        mView = LayoutInflater.from(getContext()).inflate(R.layout.view_mediacontroller, null);
        mSeekBar = (SeekBar) mView.findViewById(R.id.id_sb_process);
        mSeekBar.setMax(1000);
        mSeekBar.setOnSeekBarChangeListener(mSeekBarChangeListener);
        mTvStart = (TextView) mView.findViewById(R.id.id_tv_start_time);
        mTvEnd = (TextView) mView.findViewById(R.id.id_tv_end_time);
        //mTvEnd.setText(stringForTime(mVideoLength));
        mIvPlay = (ImageView) mView.findViewById(R.id.id_iv);
        mIvPlay.setImageResource(R.drawable.bili_player_play_can_pause);
        mIvPlay.setOnClickListener(mListener);
        mIvFullScreen = (ImageView) mView.findViewById(R.id.id_iv_full_screen);
        mIvFullScreen.setOnClickListener(mListener);
    }

    @Override
    public void setAnchorView(View view) {
        super.setAnchorView(view);

    }

    @Override
    public void showOnce(View view) {

    }

    @Override
    public void setMediaPlayer(MediaPlayerControl player) {
        super.setMediaPlayer(player);
        this.mPlayer = player;
    }

    @Override
    public void show() {
        super.show();
        removeAllViews();
        addView(mView);

    }

    @Override
    public void show(int timeout) {
        super.show(timeout);
        mHandler.sendEmptyMessage(SHOW_PROGRESS);
    }

    @Override
    public void hide() {
        super.hide();
    }

    private View.OnClickListener mListener = new View.OnClickListener(){

        @Override
        public void onClick(View v) {
            switch ( v.getId() ) {
                case R.id.id_iv:
                    show(sDefaultTimeout);
                    if ( mPlayer != null &&mIvPlay != null ) {
                        if ( mPlayer.isPlaying() && mPlayer.canPause()) {
                            mIvPlay.setImageResource(R.drawable.bili_player_play_can_play);
                            mPlayer.pause();
                        } else if ( !mPlayer.isPlaying() ) {
                            mIvPlay.setImageResource(R.drawable.bili_player_play_can_pause);
                            mPlayer.start();
                        }
                    }
                    break;

                case R.id.id_iv_full_screen:
                    if ( mOrientationChangeListener != null ) {
                        if ( mScreenProtrait ) {
                            mOrientationChangeListener.onChangeListener(SCREEN_LANDSCAPE); //如果当前是竖屏就切换成横屏
                        } else {
                            mOrientationChangeListener.onChangeListener(SCREEN_PROTRAIT); //反之亦然
                        }
                        mScreenProtrait = !mScreenProtrait;
                    }
                    break;
            }
        }
    };

    private final SeekBar.OnSeekBarChangeListener mSeekBarChangeListener = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            if ( !fromUser ) {
                return;
            }

            long duration = mPlayer.getDuration();
            long newposition = (duration * progress) /1000L;
            mPlayer.seekTo((int) newposition);
            if ( mTvStart != null ) {
                mTvStart.setText(stringForTime((int) newposition));
            }

        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {
            show(36000000);
            mDragging = true;
            mHandler.removeMessages(SHOW_PROGRESS);
        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            mDragging = false;
            setProgress();
            show(sDefaultTimeout);
            mHandler.sendEmptyMessage(SHOW_PROGRESS);
        }
    };




    /**
     * 设置seekbar的进度值
     * @return
     */
    private int setProgress() {
        if (mPlayer == null || mDragging) {
            return 0;
        }
        int position = (int) mPlayer.getCurrentPosition();
        int duration = (int) mPlayer.getDuration();
        if (mSeekBar != null) {
            if (duration > 0) {
                // use long to avoid overflow
                long pos = 1000L * position / duration;
                mSeekBar.setProgress( (int) pos);
            }
            int percent = mPlayer.getBufferPercentage();
            mSeekBar.setSecondaryProgress(percent * 10);
        }
        if (mTvEnd != null)
            mTvEnd.setText(stringForTime(duration));
        if (mTvStart != null)
            mTvStart.setText(stringForTime(position));
        return position;
    }

    /**
     * 将string转成时间
     * @param timeMs
     * @return
     */
    private String stringForTime(int timeMs) {
        int totalSeconds = timeMs / 1000;

        int seconds = totalSeconds % 60;
        int minutes = (totalSeconds / 60) % 60;
        int hours   = totalSeconds / 3600;

        mFormatBuilder.setLength(0);
        if (hours > 0) {
            return mFormatter.format("%d:%02d:%02d", hours, minutes, seconds).toString();
        } else {
            return mFormatter.format("%02d:%02d", minutes, seconds).toString();
        }
    }

    /**
     * Handler处理事件信息
     */
    private Handler mHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int pos;
            switch ( msg.what ) {
                case SHOW_PROGRESS:
                    pos = setProgress();
                    if ( !mDragging && mPlayer.isPlaying() ) {
                        msg = obtainMessage(SHOW_PROGRESS);
                        sendMessageDelayed(msg, 1000 - (pos % 1000));
                    }
                    break;
            }
        }
    };

 /*   private void startOrientationChangeListener() {
         mOrientationListener = new OrientationEventListener(mContext) {
            @Override
            public void onOrientationChanged(int orientation) {
                if (((orientation >= 0) && (orientation <= 45)) || (orientation >= 315)
                        ||((orientation>=135)&&(orientation<=225))) {//portrait  当前处于竖屏的情况
                        mScreenProtrait = false;
//                        if ( mOrientationChangeListener != null ) {
//                            mOrientationChangeListener.onChangeListener(SCREEN_LANDSCAPE);  //由当前是竖屏状态转成横屏的状态

                    } else if (((orientation > 45) && (orientation < 135))
                            ||((orientation>225)&&(orientation<315))) {//landscape
                            mScreenProtrait = true;
//                            if ( mOrientationChangeListener != null ) {
//                                mOrientationChangeListener.onChangeListener(SCREEN_PROTRAIT); //由当前的横屏转成竖屏
//                            }
                    }
            }
        };
        mOrientationListener.enable();
    }*/

    public void setOnOrientationChangeListener(OnOrientationChangeListener listener) {
        this.mOrientationChangeListener = listener;
    }

    public interface OnOrientationChangeListener {
        void onChangeListener(int screen);
    }

    public void  onDestroy() {
        if ( mOrientationListener != null ) {
            mOrientationListener = null;
        }
        if ( mOrientationChangeListener != null ) {
            mOrientationChangeListener = null;
        }
        if ( mHandler != null ) {
            mHandler.removeMessages(SHOW_PROGRESS);
            mHandler = null;
        }
        if ( mPlayer != null ) {
            mPlayer = null;
        }
    }
}

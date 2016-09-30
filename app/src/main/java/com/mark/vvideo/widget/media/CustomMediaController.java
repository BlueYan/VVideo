package com.mark.vvideo.widget.media;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.SeekBar;
import android.widget.TextView;

import com.mark.vvideo.R;

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

    public CustomMediaController(Context context) {
        super(context);
        initView(context);
    }

    public CustomMediaController(Context context, boolean useFastForward) {
        super(context, useFastForward);
        initView(context);
    }

    private void initView(Context context) {
        mView = LayoutInflater.from(getContext()).inflate(R.layout.view_mediacontroller, null);
        mSeekBar = (SeekBar) findViewById(R.id.id_sb_process);
    }

    @Override
    public void setAnchorView(View view) {
        super.setAnchorView(view);

    }

    @Override
    public void showOnce(View view) {

    }

    @Override
    public void show() {
        super.show();
        removeAllViews();
        addView(mView);
    }

    @Override
    public void hide() {
        super.hide();
    }

    @Override
    public void setMediaPlayer(MediaPlayerControl player) {
        super.setMediaPlayer(player);

    }

    private static class MyClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            switch ( v.getId() ) {
                case R.id.id_sb_process:

                    break;
            }
        }
    }
}

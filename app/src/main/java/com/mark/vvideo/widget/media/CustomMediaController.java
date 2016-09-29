package com.mark.vvideo.widget.media;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.MediaController;

import com.mark.vvideo.R;

/**
 * Author: Mark.
 * Date: 2016/9/29.
 * Function: 自定义视频控制器
 */

public class CustomMediaController extends FrameLayout implements IMediaController {

    private static final String TAG = CustomMediaController.class.getSimpleName();

    private View mView;

    public CustomMediaController(Context context) {
        super(context);
        initView(context);
    }

    public CustomMediaController(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    private void initView(Context context) {
        mView = LayoutInflater.from(context).inflate(R.layout.view_mediacontroller, this);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

    }



    @Override
    public void hide() {

    }

    @Override
    public boolean isShowing() {
        return false;
    }

    @Override
    public void setAnchorView(View view) {

    }

    @Override
    public void setMediaPlayer(MediaController.MediaPlayerControl player) {

    }

    @Override
    public void show(int timeout) {

    }

    @Override
    public void show() {
    }

    @Override
    public void showOnce(View view) {

    }
}

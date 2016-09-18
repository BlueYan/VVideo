package com.mark.vvideo.base;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.mvp.library.utils.LogUtils;

/**
 * Author: Mark
 * Date: 2016/9/18.
 * Function:
 */
public class App extends Application {

    private static App mApp;

    public static App getContext() {
        return mApp;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mApp = this;
        //init Fresco
        Fresco.initialize(this);
        LogUtils.init(true);
    }
}

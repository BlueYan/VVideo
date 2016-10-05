package com.mark.vvideo.bilibili.model.impl;

import com.mark.vvideo.bilibili.model.entry.Introduction;
import com.mark.vvideo.bilibili.model.entry.VideoInfo;
import com.mark.vvideo.bilibili.model.iface.IIntroduction;
import com.mark.vvideo.network.RetrofitManager;
import com.mark.vvideo.util.Utils;
import com.mvp.library.utils.LogUtils;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.zip.DataFormatException;

import master.flame.danmaku.danmaku.loader.ILoader;
import master.flame.danmaku.danmaku.loader.IllegalDataException;
import master.flame.danmaku.danmaku.loader.android.DanmakuLoaderFactory;
import master.flame.danmaku.danmaku.parser.BaseDanmakuParser;
import master.flame.danmaku.danmaku.parser.IDataSource;
import master.flame.danmaku.danmaku.parser.android.BiliDanmukuParser;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.Observable;
import rx.Subscriber;
import rx.schedulers.Schedulers;

/**
 * Author: Mark.
 * Date: 2016/9/22.
 * Function:
 */

public class IntroductionImpl implements IIntroduction {

    @Override
    public Observable<String> getVideoIntroduction(int aid) {
        return RetrofitManager.getInstance().getBiliApiWithNoGson().getIntroductions(aid);
    }

    @Override
    public Observable<VideoInfo> getVideoInfo(int cid) {
        return RetrofitManager.getInstance().getBiliApi().getVideoInfo(cid);
    }

    @Override
    public Observable<BaseDanmakuParser> getDanmuInfo(final int cid) {
        return Observable.create(new Observable.OnSubscribe<BaseDanmakuParser>() {

            @Override
            public void call(Subscriber<? super BaseDanmakuParser> subscriber) {
                try {
                    Response<ResponseBody> mResponse = RetrofitManager.getInstance().getBiliApiDanmu().getDanmuInfo(cid).execute();
                    //LogUtils.d("body = " + mResponse.body().string());
                    InputStream mIs = new ByteArrayInputStream(Utils.decompressXML(mResponse.body().bytes()));
                    /*String str;
                    BufferedReader mBr = new BufferedReader(new InputStreamReader(mIs));
                    while ( (str = mBr.readLine()) != null  )  {
                        LogUtils.d("str = " + str); //输出中文不乱码
                    }*/

                    ILoader mLoader = DanmakuLoaderFactory.
                            create(DanmakuLoaderFactory.TAG_BILI);
                    mLoader.load(mIs);
                    BaseDanmakuParser parser = new BiliDanmukuParser();
                    IDataSource<?> dataSource = mLoader.getDataSource();
                    parser.load(dataSource);
                    LogUtils.d("parser = " + parser.toString());
                    subscriber.onNext(parser);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (IllegalDataException e) {
                    e.printStackTrace();
                } catch (DataFormatException e) {
                    e.printStackTrace();
                }
            }
        }).subscribeOn(Schedulers.io());
    }
}

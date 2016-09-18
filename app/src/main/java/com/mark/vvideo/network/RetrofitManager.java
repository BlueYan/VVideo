package com.mark.vvideo.network;

import com.mark.vvideo.base.App;
import com.mark.vvideo.network.api.BiliApi;
import com.mvp.library.utils.NetworkUtils;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Author: Mark.
 * Date: 2016/9/18.
 * Function:
 */
public class RetrofitManager {

    private static final String TAG = RetrofitManager.class.getSimpleName();

    private static RetrofitManager mInstance = null;

    private OkHttpClient mClient; //OkHttp对象

    private static BiliApi mBiliApi;

    public static RetrofitManager getInstance() {
        if ( mInstance == null ) {
            synchronized (RetrofitManager.class) {
                if ( mInstance == null ) {
                    mInstance = new RetrofitManager();
                }
            }
        }
        return mInstance;
    }

    /**
     * 创建一个OkHttpClient对象，整合到Retrofit
     */
    private void createOkHttpClient() {
        if ( mClient == null ) {
            OkHttpClient.Builder mBuilder = new OkHttpClient.Builder();
            mBuilder.addInterceptor(REWRITE_CACHE_CONTROL_INTERCEPTOR);
            //设置超时
            mBuilder.connectTimeout(15, TimeUnit.SECONDS);
            mBuilder.readTimeout(20, TimeUnit.SECONDS);
            mBuilder.writeTimeout(20, TimeUnit.SECONDS);
            //错误重连
            mBuilder.retryOnConnectionFailure(true);
            mClient = mBuilder.build();
        }
    }

    public BiliApi getBiliApi() {
        if ( mBiliApi == null ) {
            synchronized (mBiliApi) {
                if (mBiliApi == null) {
                    mBiliApi = new Retrofit.Builder()
                            .baseUrl("http://bilibili-service.daoapp.io")
                            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                            .addConverterFactory(GsonConverterFactory.create())
                            .client(mClient)
                            .build()
                            .create(BiliApi.class);
                }
            }
        }
        return mBiliApi;
    }


    /**
     * 离线缓存
     */
    private static final Interceptor REWRITE_CACHE_CONTROL_INTERCEPTOR = new Interceptor() {
        @Override
        public Response intercept(Interceptor.Chain chain) throws IOException {
            Response originalResponse = chain.proceed(chain.request());
            if (NetworkUtils.isAvailable(App.getContext())) {
                int maxAge = 60; // 在线缓存在1分钟内可读取
                return originalResponse.newBuilder()
                        .removeHeader("Pragma")
                        .removeHeader("Cache-Control")
                        .header("Cache-Control", "public, max-age=" + maxAge)
                        .build();
            } else {
                int maxStale = 60 * 60 * 24 * 28; // 离线时缓存保存4周
                return originalResponse.newBuilder()
                        .removeHeader("Pragma")
                        .removeHeader("Cache-Control")
                        .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                        .build();
            }
        }
    };

}

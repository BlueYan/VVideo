package com.mark.vvideo.network;

import com.mark.vvideo.base.App;
import com.mark.vvideo.network.api.BiliApi;
import com.mark.vvideo.network.api.DouyuApi;
import com.mvp.library.utils.NetworkUtils;
import com.zhy.http.okhttp.log.LoggerInterceptor;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

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

    private static BiliApi mHostBiliApi = null;

    private static BiliApi mBiliApiWithNoGson = null; //解决由于json格式数据无法直接解析成bean

    private static BiliApi mBiliApiDanmu = null;

    private static DouyuApi mDouyuApi = null;

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
    private OkHttpClient createOkHttpClient() {
        if ( mClient == null ) {
            OkHttpClient.Builder mBuilder = new OkHttpClient.Builder();
            mBuilder.addInterceptor(new LoggerInterceptor("OkHttp"));
            mBuilder.addInterceptor(REWRITE_CACHE_CONTROL_INTERCEPTOR);
            //设置超时
            mBuilder.connectTimeout(15, TimeUnit.SECONDS);
            mBuilder.readTimeout(20, TimeUnit.SECONDS);
            mBuilder.writeTimeout(20, TimeUnit.SECONDS);
            //错误重连
            mBuilder.retryOnConnectionFailure(true);
            mClient = mBuilder.build();
        }
        return mClient;
    }

    /**
     * 获取url是http://bilibili-service.daoapp.io的Api接口
     * @return
     */
    public BiliApi getBiliApi() {
        if ( mHostBiliApi == null ) {
            synchronized (BiliApi.class) {
                if (mHostBiliApi == null) {
                    mHostBiliApi = new Retrofit.Builder()
                            .baseUrl("http://bilibili-service.daoapp.io")
                            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                            .addConverterFactory(GsonConverterFactory.create())
                            .client(createOkHttpClient())
                            .build()
                            .create(BiliApi.class);
                }
            }
        }
        return mHostBiliApi;
    }

    /**
     * 该方法是为了解决某些json数据源无法直接解析成bean, 需要手动解析的。
     * @return
     */
    public BiliApi getBiliApiWithNoGson() {
        if ( mBiliApiWithNoGson == null ) {
            synchronized (BiliApi.class) {
                if ( mBiliApiWithNoGson == null ) {
                    mBiliApiWithNoGson = new Retrofit.Builder()
                            .baseUrl("http://bilibili-service.daoapp.io")
                            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                            .addConverterFactory(ScalarsConverterFactory.create())
                            .client(createOkHttpClient())
                            .build()
                            .create(BiliApi.class);
                }
            }
        }
        return mBiliApiWithNoGson;
    }

    /**
     *
     * @return
     */
    public BiliApi getBiliApiDanmu() {
        if ( mBiliApiDanmu == null ) {
            synchronized (BiliApi.class) {
                if ( mBiliApiDanmu == null ) {
                    mBiliApiDanmu = new Retrofit.Builder()
                            .baseUrl("http://comment.bilibili.com")
                            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                            .addConverterFactory(ScalarsConverterFactory.create())
                            .client(createOkHttpClient())
                            .build()
                            .create(BiliApi.class);
                }
            }

        }
        return mBiliApiDanmu;
    }

    /**
     * 由于需要不同的接口
     * @return
     */
    public BiliApi getHostBiliApi() {
        if ( mBiliApi == null ) {
            synchronized (BiliApi.class) {
                if (mBiliApi == null) {
                    mBiliApi = new Retrofit.Builder()
                            .baseUrl("http://api.bilibili.cn")
                            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                            .addConverterFactory(GsonConverterFactory.create())
                            .client(createOkHttpClient())
                            .build()
                            .create(BiliApi.class);
                }
            }
        }
        return mBiliApi;
    }


    public DouyuApi getDouyuApi() {
        if ( mDouyuApi == null ) {
            synchronized (DouyuApi.class) {
                if ( mDouyuApi == null ) {
                    mDouyuApi = new Retrofit.Builder()
                            .baseUrl("http://capi.douyucdn.cn/")
                            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                            .addConverterFactory(GsonConverterFactory.create())
                            .client(createOkHttpClient())
                            .build()
                            .create(DouyuApi.class);
                }
            }
        }
        return mDouyuApi;
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

package com.mark.vvideo.douyutv.presenter;

import com.mark.vvideo.douyutv.model.entry.AllLive;
import com.mark.vvideo.douyutv.model.iface.IAllLive;
import com.mark.vvideo.douyutv.model.impl.AllLiveImpl;
import com.mark.vvideo.network.api.DouyuApi;
import com.mvp.library.base.BasePresenterImpl;
import com.mark.vvideo.douyutv.contract.AllLiveContract;
import com.mvp.library.utils.LogUtils;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Author: Mark.
 * Date: 2016/9/22.
 * Function:
 */

public class AllLivePresenter extends BasePresenterImpl implements AllLiveContract.Presenter {

    private static final String TAG = AllLivePresenter.class.getSimpleName();

    private AllLiveContract.View mView;

    private IAllLive mAllLive;

    public AllLivePresenter(AllLiveContract.View mView) {
        this.mView = mView;
        mAllLive = new AllLiveImpl();
    }


    @Override
    public void getAllLives(int limit, int offest) {
        Subscription subscription = mAllLive.getAllLives(limit, offest)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<AllLive>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(List<AllLive> allLives) {
                        LogUtils.d("allLives.size() = " + allLives.size());
                    }
                });

        addSubscription(subscription);

//        Retrofit mRetrofit = new Retrofit.Builder()
//                .baseUrl("http://capi.douyucdn.cn/")
//                .addConverterFactory(ScalarsConverterFactory.create())
//                .build();
//        DouyuApi mApi = mRetrofit.create(DouyuApi.class);
//        Call<String> mCall = mApi.getAllLives(limit, 0);
//        mCall.enqueue(new Callback<String>() {
//            @Override
//            public void onResponse(Call<String> call, Response<String> response) {
//                LogUtils.d("str = " + response.body().toString());
//            }
//
//            @Override
//            public void onFailure(Call<String> call, Throwable t) {
//                t.printStackTrace();
//            }
//        });

    }

    @Override
    public void subscribe() {

    }
}

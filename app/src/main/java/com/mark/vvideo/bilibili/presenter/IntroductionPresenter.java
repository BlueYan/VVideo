package com.mark.vvideo.bilibili.presenter;

import com.google.gson.JsonObject;
import com.mark.vvideo.bilibili.model.entry.Comment;
import com.mark.vvideo.bilibili.model.entry.Introduction;
import com.mark.vvideo.bilibili.model.entry.VideoInfo;
import com.mark.vvideo.bilibili.model.iface.IIntroduction;
import com.mark.vvideo.bilibili.model.impl.IntroductionImpl;
import com.mvp.library.base.BasePresenterImpl;
import com.mark.vvideo.bilibili.contract.IntroductionContract;
import com.mvp.library.utils.LogUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Author: Mark.
 * Date: 2016/9/22.
 * Function:
 */

public class IntroductionPresenter extends BasePresenterImpl implements IntroductionContract.Presenter {

    private static final String TAG = IntroductionPresenter.class.getSimpleName();

    private IIntroduction mIntroduction;

    private IntroductionContract.View mView;

    public IntroductionPresenter(IntroductionContract.View mView) {
        this.mView = mView;
        mIntroduction = new IntroductionImpl();
    }

    @Override
    public void getIntroduction(int aid) {
        Subscription subscription = mIntroduction.getVideoIntroduction(aid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Func1<String, Introduction>() {
                    @Override
                    public Introduction call(String s) {
                        LogUtils.d("s = " + s);
                        Introduction mIntroduction = new Introduction();
                        try {
                            JSONObject mIntroductionObject = new JSONObject(s);
                            mIntroduction.setTid(mIntroductionObject.getInt("tid"));
                            mIntroduction.setTypename(mIntroductionObject.getString("typename"));
                            mIntroduction.setArctype(mIntroductionObject.getString("arctype"));
                            mIntroduction.setPlay(mIntroductionObject.getString("play"));
                            mIntroduction.setReview(mIntroductionObject.getString("review"));
                            mIntroduction.setVideoReview(mIntroductionObject.getString("video_review"));
                            mIntroduction.setFavorites(mIntroductionObject.getString("favorites"));
                            mIntroduction.setTitle(mIntroductionObject.getString("title"));
                            mIntroduction.setDescription(mIntroductionObject.getString("description"));
                            mIntroduction.setTag(mIntroductionObject.getString("tag"));
                            mIntroduction.setPic(mIntroductionObject.getString("pic"));
                            mIntroduction.setAuthor(mIntroductionObject.getString("author"));
                            mIntroduction.setMid(mIntroductionObject.getString("mid"));
                            mIntroduction.setFace(mIntroductionObject.getString("face"));
                            mIntroduction.setPages(mIntroductionObject.getInt("pages"));
                            mIntroduction.setCreatedAt(mIntroductionObject.getString("created_at"));
                            mIntroduction.setCoins(mIntroductionObject.getString("coins"));

                            JSONObject mListBeanObject = mIntroductionObject.getJSONObject("list").getJSONObject("0");
                            com.mark.vvideo.bilibili.model.entry.Introduction.ListBean mLitBean
                                        = new Introduction.ListBean();
                                mLitBean.setPage(mListBeanObject.getInt("page"));
                                mLitBean.setType(mListBeanObject.getString("type"));
                                mLitBean.setPart(mListBeanObject.getString("part"));
                                mLitBean.setCid(mListBeanObject.getInt("cid"));
                                mLitBean.setVid(mListBeanObject.getInt("vid"));
                            mIntroduction.setListBean(mLitBean);
                            return mIntroduction;
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        return null;
                    }
                })
                .subscribe(new Subscriber<Introduction>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(Introduction introduction) {
                        mView.setIntroduction(introduction);
                    }
                });

        addSubscription(subscription);
    }

    @Override
    public void getVideoInfo(int cid) {
        Subscription subscription = mIntroduction.getVideoInfo(cid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<VideoInfo>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(VideoInfo videoInfo) {
                        mView.setVideoInfo(videoInfo);
                    }
                });
        addSubscription(subscription);
    }

    @Override
    public void subscribe() {

    }
}

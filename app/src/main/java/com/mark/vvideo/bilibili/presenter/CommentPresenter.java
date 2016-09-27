package com.mark.vvideo.bilibili.presenter;

import com.google.gson.JsonArray;
import com.mark.vvideo.bilibili.model.entry.Comment;
import com.mark.vvideo.bilibili.model.iface.IComment;
import com.mark.vvideo.bilibili.model.impl.CommentImpl;
import com.mark.vvideo.network.RetrofitManager;
import com.mark.vvideo.network.api.BiliApi;
import com.mvp.library.base.BasePresenterImpl;
import com.mark.vvideo.bilibili.contract.CommentContract;
import com.mvp.library.utils.LogUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Author: Mark.
 * Date: 2016/9/21.
 * Function:
 */

public class CommentPresenter extends BasePresenterImpl implements CommentContract.Presenter {

    private static final String TAG = CommentPresenter.class.getSimpleName();

    private IComment mIComment;

    private CommentContract.View mCommentView;

    public CommentPresenter(CommentContract.View mCommentView) {
        this.mCommentView = mCommentView;
        this.mIComment = new CommentImpl();
    }

    @Override
    public void getComments(int aid, int page, int pageSize, int ver, String order) {
        Subscription s = mIComment.getComments(aid, page, pageSize, ver, order)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Comment>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtils.d("msg = " + e.getMessage());
                        e.printStackTrace();

                    }

                    @Override
                    public void onNext(Comment comment) {
                        LogUtils.d("comment.getHostList().size() = " + comment.getList().size());
                        mCommentView.setComments(comment);
                    }
                });
        addSubscription(s);

//        mIComment.getAllComments(aid, page, pageSize, ver, order)
//                .enqueue(new Callback<String>() {
//                    @Override
//                    public void onResponse(Call<String> call, Response<String> response) {
//                        LogUtils.d("response");
//                        try {
//                            JSONObject mJsonObject = new JSONObject(response.body().toString());
//                            JSONArray mHostListJsonArray = mJsonObject.getJSONArray("hotList");
//                            JSONArray mListJsonArray = mJsonObject.getJSONArray("list");
//                            List<Comment.ListBean> mListBeans = new ArrayList<Comment.ListBean>();
//                            for ( int i = 0; i < mListJsonArray.length(); i++ ) {
//                                Comment.ListBean mListBean = new Comment.ListBean();
//                                JSONObject mListBeanObject = mListJsonArray.getJSONObject(i);
//                                mListBean.setMid(mListBeanObject.getInt("mid"));
//                                mListBean.setLv(mListBeanObject.getInt("lv"));
//                                mListBean.setFbid(mListBeanObject.getString("fbid"));
//                                mListBean.setAd_check(mListBeanObject.getInt("ad_check"));
//                                mListBean.setGood(mListBeanObject.getInt("good"));
//                                mListBean.setIsgood(mListBeanObject.getInt("isgood"));
//                                mListBean.setMsg(mListBeanObject.getString("msg"));
//                                mListBean.setDevice(mListBeanObject.getString("device"));
//                                mListBean.setCreate(mListBeanObject.getInt("create"));
//                                mListBean.setCreate_at(mListBeanObject.getString("create_at"));
//                                mListBean.setReply_count(mListBeanObject.getInt("reply_count"));
//                                mListBean.setFace(mListBeanObject.getString("face"));
//                                mListBean.setRank(mListBeanObject.getInt("rank"));
//                                mListBean.setNick(mListBeanObject.getString("nick"));
//                                mListBean.setSex(mListBeanObject.getString("sex"));
//
//                                Comment.ListBean.LevelInfoBean mLevelInfoBean
//                                        = new Comment.ListBean.LevelInfoBean();
//                                JSONObject mLevelInfoBeanObject =
//                                        mListBeanObject.getJSONObject("level_info");
//                                mLevelInfoBean.setCurrent_exp(mLevelInfoBeanObject.getInt("current_exp"));
//                                mLevelInfoBean.setCurrent_level(mLevelInfoBeanObject.getInt("current_level"));
//                                mLevelInfoBean.setCurrent_min(mLevelInfoBeanObject.getInt("current_min"));
//                                mLevelInfoBean.setNext_exp(mLevelInfoBeanObject.getInt("next_exp"));
//                                mListBean.setLevel_info(mLevelInfoBean);
//
//
//                                JSONArray mReplyBeanArray = mListBeanObject.getJSONArray("reply");
//                                List<Comment.ListBean.ReplyBean> mListReplyBeans
//                                        = new ArrayList<Comment.ListBean.ReplyBean>();
//                                for ( int j = 0; j < mReplyBeanArray.length(); j++ ) {
//                                    Comment.ListBean.ReplyBean mReplyBean =
//                                            new Comment.ListBean.ReplyBean();
//                                    JSONObject mReplyBeanObject = mReplyBeanArray.getJSONObject(j);
//                                    mReplyBean.setMid(mReplyBeanObject.getInt("mid"));
//                                    mReplyBean.setLv(mReplyBeanObject.getInt("lv"));
//                                    mReplyBean.setFbid(mReplyBeanObject.getString("fbid"));
//                                    mReplyBean.setAd_check(mReplyBeanObject.getInt("ad_check"));
//                                    mReplyBean.setGood(mReplyBeanObject.getInt("good"));
//                                    mReplyBean.setIsgood(mReplyBeanObject.getInt("isgood"));
//                                    mReplyBean.setMsg(mReplyBeanObject.getString("msg"));
//                                    mReplyBean.setDevice(mReplyBeanObject.getString("device"));
//                                    mReplyBean.setCreate(mReplyBeanObject.getInt("create"));
//                                    mReplyBean.setCreate_at(mReplyBeanObject.getString("create_at"));
//                                    mReplyBean.setReply_count(mReplyBeanObject.getInt("reply_count"));
//                                    mReplyBean.setFace(mReplyBeanObject.getString("face"));
//                                    mReplyBean.setRank(mReplyBeanObject.getInt("rank"));
//                                    mReplyBean.setNick(mReplyBeanObject.getString("nick"));
//                                    mReplyBean.setSex(mReplyBeanObject.getString("sex"));
//
//
//                                    Comment.ListBean.ReplyBean.LevelInfoBean mReplyLevelInfoBean
//                                            = new Comment.ListBean.ReplyBean.LevelInfoBean();
//                                    JSONObject mReplyLevelInfoBeanObject = mReplyBeanObject.getJSONObject("level_info");
//
//                                    mReplyLevelInfoBean.setCurrent_exp(mReplyLevelInfoBeanObject.getInt("current_exp"));
//                                    mReplyLevelInfoBean.setCurrent_level(mReplyLevelInfoBeanObject.getInt("current_level"));
//                                    mReplyLevelInfoBean.setCurrent_min(mReplyLevelInfoBeanObject.getInt("current_min"));
//                                    mReplyLevelInfoBean.setNext_exp(mReplyLevelInfoBeanObject.getInt("next_exp"));
//                                    mReplyBean.setLevel_info(mReplyLevelInfoBean);
//
//                                    mListReplyBeans.add(mReplyBean);
//
//                                }
//
//                                mListBean.setReply(mListReplyBeans);
//
//
//                            }
//
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(Call<String> call, Throwable t) {
//                        t.printStackTrace();
//                    }
//                });

    }

    @Override
    public void subscribe() {

    }
}

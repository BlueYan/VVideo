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
//                            for ( int i = 0; i < mListJsonArray.length(); i++ ) {
//                                LogUtils.d("nick = " + mListJsonArray.getJSONObject(i).getString("nick"));
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

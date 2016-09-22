package com.mark.vvideo.bilibili.presenter;

import com.mark.vvideo.bilibili.model.entry.Comment;
import com.mark.vvideo.bilibili.model.iface.IComment;
import com.mark.vvideo.bilibili.model.impl.CommentImpl;
import com.mark.vvideo.network.RetrofitManager;
import com.mark.vvideo.network.api.BiliApi;
import com.mvp.library.base.BasePresenterImpl;
import com.mark.vvideo.bilibili.contract.CommentContract;
import com.mvp.library.utils.LogUtils;

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
    public void getComments(int aid, int page, int pageSize, int ver) {
        Subscription s = mIComment.getComments(aid, page, pageSize, ver)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<Comment>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtils.d("msg = " + e.getMessage());
                        e.printStackTrace();

                    }

                    @Override
                    public void onNext(List<Comment> comments) {
                        LogUtils.d("comments.size() = " + comments.size());
                        mCommentView.setComments(comments);
                    }
                });
        addSubscription(s);
    }

    @Override
    public void subscribe() {

    }
}

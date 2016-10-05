package com.mark.vvideo.bilibili.presenter;

import com.mark.vvideo.bilibili.contract.AllRankContract;
import com.mark.vvideo.bilibili.model.entry.AllRank;
import com.mark.vvideo.bilibili.model.iface.IAllRank;
import com.mark.vvideo.bilibili.model.impl.AllRankImpl;
import com.mvp.library.base.BasePresenterImpl;
import com.mvp.library.utils.LogUtils;

import java.util.List;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Author: Mark.
 * Date: 2016/9/18.
 * Function: 所有分类热门数据P层
 *
 * P层持有V M 层的对象
 *
 */
public class AllRankPresenter extends BasePresenterImpl implements AllRankContract.Presenter {

    private IAllRank mAllRank; //M层对象

    private AllRankContract.View mAllRankView; //V层对象

    public AllRankPresenter(AllRankContract.View mAllRankView) {
        this.mAllRankView = mAllRankView;
        mAllRank = new AllRankImpl();  //实例化
        mAllRankView.setPresenter(this);  //设置P层对象，在V层中去实现契约类接口中的V层接口　
    }

    @Override
    public void setPagerTitle() {
        Subscription s = mAllRank.getAllRank()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<AllRank>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(List<AllRank> allRankModels) {
                        for ( int i = 0; i < allRankModels.size(); i++ ) {
                            AllRank allRank = allRankModels.get(i);
                            List<AllRank.VideosBean> videosBeans = allRank.getVideos();
                            for ( AllRank.VideosBean videosBean : videosBeans ) {
                                LogUtils.d("title = " + videosBean.getTitle());
                            }
                        }
                        mAllRankView.setViewPager(allRankModels);
                    }
                });
        addSubscription(s);
    }

    @Override
    public void subscribe() {

    }
}

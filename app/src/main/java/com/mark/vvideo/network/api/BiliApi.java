package com.mark.vvideo.network.api;

import com.mark.vvideo.bilibili.model.entry.AllRank;
import com.mark.vvideo.bilibili.model.entry.Comment;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Author: Mark.
 * Date: 2016/9/18.
 * Function: Bilibili Api
 * 访问Bilibili所有接口都写在这里
 */
public interface BiliApi {

    /**
     * 获取首页所有的分类
     * @return
     */
    @GET("/allrank")
    Observable<List<AllRank>> getAllRank();

    @GET("/feedback")
    Observable<List<Comment>> getComments(@Query("aid") int aid, @Query("page") int page,
                                          @Query("pagesize") int pageSize, @Query("ver") int ver);

}

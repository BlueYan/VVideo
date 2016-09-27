package com.mark.vvideo.network.api;

import com.mark.vvideo.bilibili.model.entry.AllRank;
import com.mark.vvideo.bilibili.model.entry.Comment;
import com.mark.vvideo.bilibili.model.entry.Introduction;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
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
    Observable<Comment> getComments(@Query("aid") int aid, @Query("page") int page,
                                          @Query("pagesize") int pageSize, @Query("ver") int ver, @Query("order") String order);

    @GET("/feedback")
    Call<String> getAllComments(@Query("aid") int aid, @Query("page") int page,
                                    @Query("pagesize") int pageSize, @Query("ver") int ver, @Query("order") String order);

    //http://bilibili-service.daoapp.io/view/aid
    @GET("/view/{aid}")
    Observable<String> getIntroductions(@Path("aid") int aid);
}

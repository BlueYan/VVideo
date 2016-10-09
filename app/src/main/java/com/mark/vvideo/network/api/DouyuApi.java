package com.mark.vvideo.network.api;

import com.mark.vvideo.douyutv.model.entry.AllLive;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Author: Mark.
 * Date: 2016/9/22.
 * Function:
 */

public interface DouyuApi {


    /*http://capi.douyucdn.cn/api/v1/live?limit=20&client_sys=android&offset=0
    * limit : 一次获取多少条信息
    * client_sys : android ios
    * offset : 起始位置
    */

    @GET("api/v1/live")
    Observable<AllLive> getAllLives(@Query("limit") int limit, @Query("client_sys") String sys, @Query("offest") int offest);

    /**
     * http://capi.douyucdn.cn/api/v1/room/60062?aid=ios&client_sys=ios&ne=1&support_pwd=1&time=1475911260&auth=67bb3519245f7f2e7ae9e9b6e38c7b89
     */

//    @GET("api/v1/live")
//    Call<String> getAllLives(@Query("limit") int limit, @Query("offest") int offest);
    //"room/"+285995+"?aid=android&clientsys=android&time=1474607061"+1231
}

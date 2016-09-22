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


    //http://capi.douyucdn.cn/api/v1/live?limit=20&offset=0
    @GET("api/v1/live")
    Observable<List<AllLive>> getAllLives(@Query("limit") int limit, @Query("offest") int offest);

}

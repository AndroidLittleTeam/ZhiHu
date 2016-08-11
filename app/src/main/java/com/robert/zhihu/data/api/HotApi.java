package com.robert.zhihu.data.api;

import com.robert.zhihu.entity.ApiResponse;
import com.robert.zhihu.entity.Popular;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by robert on 2016/8/11.
 */

public interface HotApi {
    @GET("txapi/weixin/wxhot")
    Observable<ApiResponse<List<Popular>>> getPopular(@Query("page") int page, @Query("num") int num, @Query("word") String word);
}

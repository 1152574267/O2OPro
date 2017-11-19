package com.mrk.yinxiangzhe.api;

import com.mrk.yinxiangzhe.bean.BaseEntry;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiService {
    @Headers({"X-Bmob-Application-Id:a084b0f6d2b5bdad98015a49bc68f000", "X-Bmob-REST-API-Key:67d80d14f6f856e4c50de8b8d5ec0f70", "Content-Type:application/json"})
    @POST("BaseEntry")
    Call<BaseEntry> createUser(@Body BaseEntry entry);

    @Headers({"X-Bmob-Application-Id:a084b0f6d2b5bdad98015a49bc68f000", "X-Bmob-REST-API-Key:67d80d14f6f856e4c50de8b8d5ec0f70", "Content-Type:application/json"})
    @GET("BaseEntry")
    Call<BaseEntry> getUser(@Query("mUserName") String userName, @Query("mUserPwd") String userPwd, @Query("isLogin") int isLogin);
}
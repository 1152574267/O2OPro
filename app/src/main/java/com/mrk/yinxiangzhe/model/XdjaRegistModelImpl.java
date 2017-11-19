package com.mrk.yinxiangzhe.model;

import com.mrk.yinxiangzhe.contract.XdjaRegistContract;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class XdjaRegistModelImpl implements XdjaRegistContract.XdjaRegistModel {

    @Override
    public OkHttpClient getOkHttpClient() {
//        HttpLoggingInterceptor mHttpLoggingInterceptor = new HttpLoggingInterceptor();
//        mHttpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient httpClient = new OkHttpClient.Builder()
                //.addNetworkInterceptor(mHttpLoggingInterceptor)
                .addInterceptor(new Interceptor() {

                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request request = chain.request().newBuilder()
                                .addHeader("X-Bmob-Application-Id", "a084b0f6d2b5bdad98015a49bc68f000")
                                .addHeader("X-Bmob-REST-API-Key", "67d80d14f6f856e4c50de8b8d5ec0f70")
                                .addHeader("Content-Type", "application/json")
                                .build();
                        return chain.proceed(request);
                    }
                }).build();
        return httpClient;
    }
}
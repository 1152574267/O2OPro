package com.mrk.yinxiangzhe.presenter;

import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.mrk.yinxiangzhe.R;
import com.mrk.yinxiangzhe.api.ApiService;
import com.mrk.yinxiangzhe.bean.BaseEntry;
import com.mrk.yinxiangzhe.contract.XdjaLoginContract;
import com.mrk.yinxiangzhe.model.XdjaLoginModelImpl;
import com.mrk.yinxiangzhe.utils.Utils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class XdjaLoginPresenterImpl implements XdjaLoginContract.XdjaLoginPresenter {
    private Context mContext;
    private XdjaLoginContract.XdjaLoginModel xdjaLoginModel;

    public XdjaLoginPresenterImpl(Context context) {
        this.mContext = context;

        xdjaLoginModel = new XdjaLoginModelImpl();
    }

    @Override
    public void userLogin(String userName, String userPwd) {
        if (TextUtils.isEmpty(userName) || TextUtils.isEmpty(userPwd)) {
            Toast.makeText(mContext, R.string.xdja_user_or_pwd_empty, Toast.LENGTH_SHORT).show();
            return;
        }

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Utils.BASE_URL)
                .client(xdjaLoginModel.getOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        retrofit.create(ApiService.class)
                .getUser(userName, userPwd, 1)
                .enqueue(new Callback<BaseEntry>() {

                    @Override
                    public void onResponse(Call<BaseEntry> call, Response<BaseEntry> response) {
                        Toast.makeText(mContext, mContext.getResources().getString(R.string.xdja_login_success) + ": " + response.toString(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<BaseEntry> call, Throwable t) {
                        Toast.makeText(mContext, mContext.getResources().getString(R.string.xdja_login_failure) + ":" + t.toString(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
package com.mrk.yinxiangzhe.presenter;

import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.mrk.yinxiangzhe.R;
import com.mrk.yinxiangzhe.api.ApiService;
import com.mrk.yinxiangzhe.bean.BaseEntry;
import com.mrk.yinxiangzhe.contract.XdjaRegistContract;
import com.mrk.yinxiangzhe.model.XdjaRegistModelImpl;
import com.mrk.yinxiangzhe.utils.Utils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class XdjaRegistPresenterImpl implements XdjaRegistContract.XdjaRegistPresenter {
    private Context mContext;
    private XdjaRegistContract.XdjaRegistModel xdjaRegistModel;

    public XdjaRegistPresenterImpl(Context context) {
        this.mContext = context;

        xdjaRegistModel = new XdjaRegistModelImpl();
    }

    @Override
    public void userRegist(String userName, String userPwd) {
        if (TextUtils.isEmpty(userName) || TextUtils.isEmpty(userPwd)) {
            Toast.makeText(mContext, R.string.xdja_user_or_pwd_empty, Toast.LENGTH_SHORT).show();
            return;
        }
        BaseEntry entry = new BaseEntry();
        entry.setUserName(userName);
        entry.setUserPwd(userPwd);
        entry.setIsLogin(0);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Utils.BASE_URL)
                .client(xdjaRegistModel.getOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        retrofit.create(ApiService.class)
                .createUser(entry)
                .enqueue(new Callback<BaseEntry>() {

                    @Override
                    public void onResponse(Call<BaseEntry> call, Response<BaseEntry> response) {
                        Toast.makeText(mContext, mContext.getResources().getString(R.string.xdja_register_success) + ": " + response.toString(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<BaseEntry> call, Throwable t) {
                        Toast.makeText(mContext, mContext.getResources().getString(R.string.xdja_register_failure) + ":" + t.toString(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
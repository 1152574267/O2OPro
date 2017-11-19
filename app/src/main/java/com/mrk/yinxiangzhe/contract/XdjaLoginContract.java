package com.mrk.yinxiangzhe.contract;

import com.mrk.yinxiangzhe.base.BaseModel;
import com.mrk.yinxiangzhe.base.BasePresenter;
import com.mrk.yinxiangzhe.base.BaseView;

import okhttp3.OkHttpClient;

public interface XdjaLoginContract {
    interface XdjaLoginView extends BaseView {

    }

    interface XdjaLoginModel extends BaseModel {
        OkHttpClient getOkHttpClient();
    }

    interface XdjaLoginPresenter extends BasePresenter {
        void userLogin(String userName, String userPwd);
    }
}




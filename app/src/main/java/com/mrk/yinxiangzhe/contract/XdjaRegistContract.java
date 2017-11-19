package com.mrk.yinxiangzhe.contract;

import com.mrk.yinxiangzhe.base.BaseModel;
import com.mrk.yinxiangzhe.base.BasePresenter;
import com.mrk.yinxiangzhe.base.BaseView;

import okhttp3.OkHttpClient;

public interface XdjaRegistContract {
    interface XdjaRegistView extends BaseView {

    }

    interface XdjaRegistModel extends BaseModel {
        OkHttpClient getOkHttpClient();
    }

    interface XdjaRegistPresenter extends BasePresenter {
        void userRegist(String userName, String userPwd);
    }
}




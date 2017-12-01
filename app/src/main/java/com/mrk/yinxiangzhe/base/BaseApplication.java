package com.mrk.yinxiangzhe.base;

import android.app.Application;

import com.mrk.lib_mvp_zxing.utils.ZXingLibrary;

public class BaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        ZXingLibrary.initDisplayOpinion(this);
    }
}

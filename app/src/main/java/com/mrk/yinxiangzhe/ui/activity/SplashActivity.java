package com.mrk.yinxiangzhe.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;

import com.mrk.yinxiangzhe.R;
import com.mrk.yinxiangzhe.base.BaseActivity;
import com.mrk.yinxiangzhe.bean.BaseEntry;
import com.mrk.yinxiangzhe.utils.Utils;

public class SplashActivity extends BaseActivity {
    private BaseEntry item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        item = getBaseEntry();
        // 闪屏的核心代码
        mHandler.postDelayed(new Runnable() {

            @Override
            public void run() {
                // 判断用户是否具有登录态
                if (!TextUtils.isEmpty(item.getUserId())) {
                    // 用户具有登录态,直接进入主页面
//                    Intent intent = new Intent(mActivity, MainActivity.class);
//                    mActivity.startActivity(intent);
//                    Utils.startActivityAnimation(mActivity);
//                    mActivity.finish();
                } else {
                    // 没有登录态，进入登录页面
                    //new LoginTasks(mActivity).execute();
                    Intent intent = new Intent(mActivity, MainActivity.class);
                    mActivity.startActivity(intent);
                    Utils.startActivityAnimation(mActivity);
                    mActivity.finish();
                }
            }
        }, 2000);
    }
}

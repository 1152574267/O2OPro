package com.mrk.yinxiangzhe.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.mrk.banner.base.Banner;
import com.mrk.banner.loader.GlideImageLoader;
import com.mrk.banner.transformer.DefaultTransformer;
import com.mrk.lib_mvp_zxing.ui.activity.CaptureActivity;
import com.mrk.yinxiangzhe.R;
import com.mrk.yinxiangzhe.contract.XdjaLoginContract;
import com.mrk.yinxiangzhe.presenter.XdjaLoginPresenterImpl;
import com.mrk.yinxiangzhe.utils.Utils;

import cn.bmob.v3.Bmob;

public class MainActivity extends Activity implements XdjaLoginContract.XdjaLoginView, View.OnClickListener {
    private TextView mUserRegister;
    private EditText mUserAccount;
    private EditText mUserPassword;
    private Button mUserLogin;
    private Button mBarcodeScan;
    private Button mBarcodeGallery;
    // Banner
    private Banner mBanner;

    private XdjaLoginContract.XdjaLoginPresenter xdjaLoginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Bmob.initialize(this, "a084b0f6d2b5bdad98015a49bc68f000");
        initView();
        initData();
    }

    @Override
    protected void onResume() {
        super.onResume();

        mBanner.startAutoPlay();
    }

    @Override
    protected void onStop() {
        super.onStop();

        mBanner.stopAutoPlay();
    }

    public void initView() {
        mUserRegister = (TextView) findViewById(R.id.xdja_user_register);
        mUserAccount = (EditText) findViewById(R.id.xdja_account);
        mUserPassword = (EditText) findViewById(R.id.xdja_password);
        mUserLogin = (Button) findViewById(R.id.xdja_login);
        mBarcodeScan = (Button) findViewById(R.id.xdja_barcode_scan);
        mBarcodeGallery = (Button) findViewById(R.id.xdja_barcode_gallery);
        mBanner = (Banner) findViewById(R.id.xdja_banner);

        mUserRegister.setOnClickListener(this);
        mUserLogin.setOnClickListener(this);
        mBarcodeScan.setOnClickListener(this);
        mBarcodeGallery.setOnClickListener(this);
    }

    @Override
    public void initData() {
        xdjaLoginPresenter = new XdjaLoginPresenterImpl(this);

        mBanner.setImages(Utils.getImageList(this))
                .setImageLoader(new GlideImageLoader())
                .setBannerAnimation(DefaultTransformer.class)
                .isAutoPlay(true)
                .setDelayTime(3000)
                .start();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.xdja_user_register:
                Intent i = new Intent(MainActivity.this, XdjaRegistActivity.class);
                startActivity(i);
                break;
            case R.id.xdja_login:
                xdjaLoginPresenter.userLogin(mUserAccount.getText().toString().trim(),
                        mUserPassword.getText().toString().trim());
                break;
            case R.id.xdja_barcode_scan:
                Intent intent = new Intent(this, CaptureActivity.class);
                startActivityForResult(intent, Utils.REQUEST_CODE);
                break;
            case R.id.xdja_barcode_gallery:
                Intent intent1 = new Intent();
                intent1.setAction(Intent.ACTION_PICK);
                intent1.setType("image/*");
                startActivityForResult(intent1, Utils.REQUEST_IMAGE);
                break;
            default:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            // 处理二维码扫描结果
            case Utils.REQUEST_CODE:
                Utils.startBarcodeScan(this, data);
                break;
            // 选择系统图片并解析
            case Utils.REQUEST_IMAGE:
                Utils.startBarcodeGallery(this, data);
                break;
            default:
                break;
        }
    }

}

package com.mrk.yinxiangzhe.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.mrk.lib_mvp_zxing.ui.activity.CaptureActivity;
import com.mrk.lib_mvp_zxing.utils.CodeUtils;
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
    private Button mBarcodeAbout;

    private XdjaLoginContract.XdjaLoginPresenter xdjaLoginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Bmob.initialize(this, "a084b0f6d2b5bdad98015a49bc68f000");
        initView();
        initData();
    }

    public void initView() {
        mUserRegister = (TextView) findViewById(R.id.xdja_user_register);
        mUserAccount = (EditText) findViewById(R.id.xdja_account);
        mUserPassword = (EditText) findViewById(R.id.xdja_password);
        mUserLogin = (Button) findViewById(R.id.xdja_login);
        mBarcodeAbout = (Button) findViewById(R.id.xdja_barcode_about);

        mUserRegister.setOnClickListener(this);
        mUserLogin.setOnClickListener(this);
        mBarcodeAbout.setOnClickListener(this);
    }

    @Override
    public void initData() {
        xdjaLoginPresenter = new XdjaLoginPresenterImpl(this);
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
            case R.id.xdja_barcode_about:
                Intent intent = new Intent(this, CaptureActivity.class);
                startActivityForResult(intent, Utils.REQUEST_CODE);
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
                // 处理扫描结果（在界面上显示）
                if (null != data) {
                    Bundle bundle = data.getExtras();
                    if (bundle == null) {
                        return;
                    }

                    int type = bundle.getInt(CodeUtils.RESULT_TYPE);
                    if (type == CodeUtils.RESULT_SUCCESS) {
                        String result = bundle.getString(CodeUtils.RESULT_STRING);
                        Toast.makeText(this, "解析结果:" + result, Toast.LENGTH_LONG).show();
                    } else if (type == CodeUtils.RESULT_FAILED) {
                        Toast.makeText(this, "解析二维码失败", Toast.LENGTH_LONG).show();
                    }
                }
                break;
            default:
                break;
        }
    }

}

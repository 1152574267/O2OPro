package com.mrk.yinxiangzhe.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.mrk.yinxiangzhe.R;
import com.mrk.yinxiangzhe.contract.XdjaLoginContract;
import com.mrk.yinxiangzhe.presenter.XdjaLoginPresenterImpl;

import cn.bmob.v3.Bmob;

public class MainActivity extends Activity implements XdjaLoginContract.XdjaLoginView, View.OnClickListener {
    private TextView mUserRegister;
    private EditText mUserAccount;
    private EditText mUserPassword;
    private Button mUserLogin;

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

        mUserRegister.setOnClickListener(this);
        mUserLogin.setOnClickListener(this);
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
            default:
                break;
        }
    }
}

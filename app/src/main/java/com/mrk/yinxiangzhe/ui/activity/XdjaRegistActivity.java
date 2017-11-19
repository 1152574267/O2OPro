package com.mrk.yinxiangzhe.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.mrk.yinxiangzhe.R;
import com.mrk.yinxiangzhe.contract.XdjaRegistContract;
import com.mrk.yinxiangzhe.presenter.XdjaRegistPresenterImpl;

public class XdjaRegistActivity extends Activity implements XdjaRegistContract.XdjaRegistView,
        View.OnClickListener {
    private EditText mUserAccount;
    private EditText mUserPassword;
    private Button mUserRegist;

    private XdjaRegistContract.XdjaRegistPresenter xdjaRegistPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.xdja_user_regist);

        initView();
        initData();
    }

    @Override
    public void initView() {
        mUserRegist = (Button) findViewById(R.id.xdja_regist);
        mUserAccount = (EditText) findViewById(R.id.xdja_account);
        mUserPassword = (EditText) findViewById(R.id.xdja_password);

        mUserRegist.setOnClickListener(this);
    }

    @Override
    public void initData() {
        xdjaRegistPresenter = new XdjaRegistPresenterImpl(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.xdja_regist:
                xdjaRegistPresenter.userRegist(
                        mUserAccount.getText().toString().trim(),
                        mUserPassword.getText().toString().trim());
                break;
            default:
                break;
        }
    }
}
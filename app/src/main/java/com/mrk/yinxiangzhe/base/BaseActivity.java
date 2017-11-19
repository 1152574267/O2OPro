package com.mrk.yinxiangzhe.base;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;

import com.mrk.yinxiangzhe.bean.BaseEntry;
import com.mrk.yinxiangzhe.utils.Utils;

public class BaseActivity extends Activity {
    protected BaseActivity mActivity;
    private ProgressDialog mLoadDialog;
    private static final int showDialog = 1;
    private static final int closeDialog = 2;

    public Handler mHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            if (msg.what == showDialog) {
                String result = msg.obj.toString();
                if (mLoadDialog == null) {
                    // 显示Progress对话框
                    mLoadDialog = ProgressDialog.show(mActivity, "", result, true);
                }
                mLoadDialog.setMessage(result);
            } else if (msg.what == closeDialog) {
                if (mLoadDialog != null) {
                    mLoadDialog.dismiss();
                    mLoadDialog = null;
                }
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.mActivity = this;
    }

    public void showLoadDialog(String str) {
        Message msg = new Message();
        msg.what = showDialog;
        msg.obj = str;
        mHandler.sendMessage(msg);
    }

    public void closeLoadDialog() {
        mHandler.sendEmptyMessage(closeDialog);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            finish();
            Utils.closeActivityAnimation(this);
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }

    public BaseEntry getBaseEntry() {
        return getBaseEntry("MiniLogin");
    }

    public BaseEntry getBaseEntry(String name) {
        SharedPreferences sp = this.getSharedPreferences(name, MODE_PRIVATE);

        BaseEntry item = new BaseEntry();
        item.setDeviceId(sp.getString("DEVICEID", ""));
        item.setSessionId(sp.getString("YXZSESSIONID", ""));
        item.setOpenId(sp.getString("OPENID", ""));
        item.setUserName(sp.getString("UNAME", ""));
        item.setUserId(sp.getString("UID", ""));
        item.setCheckoutquoteId(sp.getString("CHECKOUTQUOTEID", ""));
        item.setUserType(sp.getString("USERTYPE", ""));
        item.setImgUrl(sp.getString("IMGURL", ""));
        item.setIsLogin(1);

        return item;
    }

//	public void SetBaseModel(BaseModel item){
//	    if(item.getUserType().equals("WeiXin")){
//	    	//如果是微信登录则存储之前的登录方式
//	    	BaseModel model = GetBaseModel();
//	    	model.setUserType("");
//	    	SetBaseModel(model,"MiniCustomer");
//	    }
//	    SetBaseModel(item,"MiniLogin");
//	}

//	private void SetBaseModel(BaseModel item,String name){
//		SharedPreferences sp = this.getSharedPreferences(name, MODE_PRIVATE);
//	    Editor editor = sp.edit();
//	    editor.putString("DEVICEID", item.getDeviceId());
//	    editor.putString("YXZSESSIONID", item.getSessionId());
//	    editor.putString("OPENID", item.getOpenId());
//	    editor.putString("UNAME", item.getUserName());
//	    editor.putString("UID", item.getUserId());
//	    editor.putString("CHECKOUTQUOTEID", item.getCheckoutquoteId());
//	    editor.putString("USERTYPE", item.getUserType());
//	    editor.putString("IMGURL", item.getImgUrl());
//	    editor.commit();
//	}

    //    public Boolean isThreadRun() {
//        SharedPreferences sp = this.getSharedPreferences("MiniRun", MODE_PRIVATE);
//        return sp.getBoolean("IsThreadRun", false);
//    }

//    public void SetIsThreadRun(Boolean value) {
//        SharedPreferences sp = this.getSharedPreferences("MiniRun", MODE_PRIVATE);
//        Editor editor = sp.edit();
//        editor.putBoolean("IsThreadRun", value);
//        editor.commit();
//    }

//    	public void ToMainActivity(){
//		String uid = this.GetBaseModel().getUserId();
//		if(uid!=null && !uid.trim().equals("")){
//			Intent intent = new Intent(mActivity,MainActivity.class);
//			mActivity.startActivity(intent);
//			Utils.StartActivity(mActivity);
//			mActivity.finish();
//		}else{
//			CustomToast.ShowToast(mActivity, "至少选择一个登录方式！");
//		}
//	}

//    //设置用户信息  使用时重写该方法
//    public void SetUserInfo() {
//    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (mLoadDialog != null) {
            mLoadDialog.dismiss();
            mLoadDialog = null;
        }
    }
}

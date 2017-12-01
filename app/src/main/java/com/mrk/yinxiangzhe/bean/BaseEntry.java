package com.mrk.yinxiangzhe.bean;

import org.json.JSONObject;

import cn.bmob.v3.BmobUser;

public class BaseEntry extends BmobUser {
    //设备Id
    private String mDeviceId = "";
    //会话ID
    private String mSessionId = "";
    //用户名称
    private String mUserName = "";
    //用户密码
    private String mUserPwd = "";
    //用户Id
    private String mUserId = "";
    //订单索引ID
    private String mCheckoutquoteId = "";
    //微信openid或其他平台的用户openid
    private String mOpenId = "";
    //标记客户端平台，android或ios
    private String mPlatform = "android";
    //wexin或者qq 默认为空
    private String mUserType = "";
    //是否登录
    private int isLogin = 0;
    //登陆时间
    private String mLoginTime = "";
    //请求时间
    private String mRequestTime = "";
    //相应时间
    private String mResponseTime = "";
    //请求命令
    private String mRequestCmd = "";
    //相应结果
    private String mReqSuccess = "";
    //错误信息
    private String mErrMsg = "";

    private String mImgUrl = "";

    private JSONObject json = new JSONObject();

    public String getImgUrl() {
        return mImgUrl;
    }

    public void setImgUrl(String imgUrl) {
        mImgUrl = imgUrl;
    }

    public String getDeviceId() {
        return mDeviceId;
    }

    public void setDeviceId(String deviceId) {
        mDeviceId = deviceId;
    }

    public String getSessionId() {
        return mSessionId;
    }

    public void setSessionId(String sessionId) {
        mSessionId = sessionId;
    }

    public String getUserName() {
        return mUserName;
    }

    public void setUserName(String userName) {
        mUserName = userName;
    }

    public String getUserPwd() {
        return mUserPwd;
    }

    public void setUserPwd(String userPwd) {
        mUserPwd = userPwd;
    }

    public String getUserId() {
        return mUserId;
    }

    public void setUserId(String userId) {
        mUserId = userId;
    }

    public String getOpenId() {
        return mOpenId;
    }

    public void setOpenId(String openId) {
        mOpenId = openId;
    }

    public String getPlatform() {
        return mPlatform;
    }

    public String getUserType() {
        return mUserType;
    }

    public void setUserType(String userType) {
        mUserType = userType;
    }

    public String getCheckoutquoteId() {
        return mCheckoutquoteId;
    }

    public void setCheckoutquoteId(String checkoutquoteId) {
        mCheckoutquoteId = checkoutquoteId;
    }

    public int getIsLogin() {
        return isLogin;
    }

    public void setIsLogin(int isLogin) {
        this.isLogin = isLogin;
    }

    public String getLoginTime() {
        return mLoginTime;
    }

    public void setLoginTime(String loginTime) {
        mLoginTime = loginTime;
    }

    public String getRequestTime() {
        return mRequestTime;
    }

    public void setRequestTime(String requestTime) {
        mRequestTime = requestTime;
    }

    public String getResponseTime() {
        return mResponseTime;
    }

    public void setResponseTime(String responseTime) {
        mResponseTime = responseTime;
    }

    public String getRequestCmd() {
        return mRequestCmd;
    }

    public void setRequestCmd(String requestCmd) {
        mRequestCmd = requestCmd;
    }

    public String getReqSuccess() {
        return mReqSuccess;
    }

    public void setReqSuccess(String reqSuccess) {
        mReqSuccess = reqSuccess;
    }

    public String getErrMsg() {
        return mErrMsg;
    }

    public void setErrMsg(String errMsg) {
        mErrMsg = errMsg;
    }

    public JSONObject getJson() {
        return json;
    }

    public void setJson(JSONObject json) {
        this.json = json;
    }

    public void addJson(String name, Object value) {
        try {
            json.put(name, value);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getJsonParams() {
        this.addJson("DEVICEID", this.getDeviceId());
        this.addJson("YXZSESSIONID", this.getSessionId());
        this.addJson("UNAME", this.getUserName());
        this.addJson("UID", this.getUserId());
        this.addJson("OPENID", this.getOpenId());
        this.addJson("UTYPE", this.getUserType());
        this.addJson("PLATFORM", this.getPlatform());
        this.addJson("ISLOGIN", this.getIsLogin());
        this.addJson("LOGINTIME", this.getLoginTime());
        this.addJson("REQUESTTIME", this.getRequestTime());
        this.addJson("RESPONSETIME", this.getResponseTime());
        this.addJson("REQUESTCMD", this.getRequestCmd());
        this.addJson("REQSUCCESS", this.getReqSuccess());
        this.addJson("ERRMSG", this.getErrMsg());
        this.addJson("CHECKOUTQUOTEID", this.getCheckoutquoteId());

        return json.toString();
    }
}

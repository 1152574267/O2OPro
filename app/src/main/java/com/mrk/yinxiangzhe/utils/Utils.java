package com.mrk.yinxiangzhe.utils;

import java.security.MessageDigest;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

import com.mrk.lib_mvp_zxing.utils.CodeUtils;
import com.mrk.yinxiangzhe.R;
import com.mrk.yinxiangzhe.base.BaseActivity;
import com.mrk.yinxiangzhe.ui.activity.MainActivity;

public class Utils {
    static final String TAG = Utils.class.getSimpleName();

    public static final String BASE_URL = "https://api.bmob.cn/1/classes/";
    // 扫描跳转Activity RequestCode
    public static final int REQUEST_CODE = 100;
    // 选择系统图片RequestCode
    public static final int REQUEST_IMAGE = 101;

    public static String Get32MD5(String value) {
        char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'a', 'b', 'c', 'd', 'e', 'f'};
        try {
            byte[] strTemp = value.getBytes();
            // 使用MD5创建MessageDigest对象
            MessageDigest mdTemp = MessageDigest.getInstance("MD5");
            mdTemp.update(strTemp);
            byte[] md = mdTemp.digest();
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte b = md[i];
                // System.out.println((int)b);
                // 将没个数(int)b进行双字节加密
                str[k++] = hexDigits[b >> 4 & 0xf];
                str[k++] = hexDigits[b & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 获取设备编号
     */
    public static String GetDeviceId(BaseActivity activity) {
        TelephonyManager telephonyManager = (TelephonyManager) activity
                .getSystemService(Context.TELEPHONY_SERVICE);
        return telephonyManager.getDeviceId();
        // imsistring = telephonyManager.getSubscriberId();
    }

    /**
     * 获取当前时间
     */
    public static Long GetCurrTimeMillis() {
        return new Date().getTime();
    }

    public static String StringToUnicode(String str) {
        char[] utfBytes = str.toCharArray();
        String result = "";
        for (int byteIndex = 0; byteIndex < utfBytes.length; byteIndex++) {
            String hexB = Integer.toHexString(utfBytes[byteIndex]);
            if (hexB.length() <= 2) {
                hexB = "00" + hexB;
            }
            result = result + "\\u" + hexB;
        }
        return result;
    }

    public static String UnicodeToString(String uStr) {
        Log.i("UnicodeToString", uStr);
        int start = 0, end = 0;
        final StringBuffer buffer = new StringBuffer();
        while (start > -1) {
            end = uStr.indexOf("\\u", start + 2);
            String charStr = "";
            if (end == -1) {
                charStr = uStr.substring(start + 2, uStr.length());
            } else {
                charStr = uStr.substring(start + 2, end);
            }
            Log.i("UnicodeToString", charStr);
            char letter = (char) Integer.parseInt(charStr, 16); // 16进制parse整形字符串。
            buffer.append(new Character(letter).toString());
            start = end;
        }
        Log.i("UnicodeToString", buffer.toString());
        return buffer.toString();
    }

    /**
     * 数据格式化
     */
    public static String FromartMoney(Float value) {
        DecimalFormat decimalFormat = new DecimalFormat("##0.00");
        return decimalFormat.format(value);
    }

    /**
     * 检查SD卡是否存在
     */
    public static boolean CheckSDcard(BaseActivity activity) {
        boolean flag = Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED);
        if (!flag) {
            Toast.makeText(activity, "请插入手机存储卡再使用本功能", Toast.LENGTH_SHORT)
                    .show();
        }
        return flag;
    }

    /**
     * 启动Activity动画
     */
    public static void startActivityAnimation(BaseActivity activity) {
        activity.overridePendingTransition(R.anim.in_from_right,
                R.anim.out_to_left);
    }

    /**
     * 关闭Activity动画
     */
    public static void closeActivityAnimation(BaseActivity activity) {
        activity.overridePendingTransition(R.anim.in_from_left,
                R.anim.out_to_right);
    }

    public static List<Map.Entry> sortMapKeyData(HashMap map) {
        if (map == null) {
            return null;
        }
        List<Map.Entry> infoIds = new ArrayList<Map.Entry>(
                map.entrySet());
        // 排序前
        for (int i = 0; i < infoIds.size(); i++) {
            String id = infoIds.get(i).toString();
            Log.i("infoIdsBefore", id);
        }

        Collections.sort(infoIds, new Comparator<Map.Entry>() {

            @Override
            public int compare(Entry o1,
                               Entry o2) {
                return (o1.getKey().toString()).compareTo(o2.getKey()
                        .toString());
            }
        });

        // 排序后
        for (int i = 0; i < infoIds.size(); i++) {
            String id = infoIds.get(i).toString();
            Log.i("infoIdsAfter", id);
        }

        return infoIds;
    }

    /**
     * 处理扫描结果（在界面上显示）
     */
    public static void startBarcodeScan(Context context, Intent data) {
        if (null != data) {
            Bundle bundle = data.getExtras();
            if (bundle == null) {
                return;
            }

            int type = bundle.getInt(CodeUtils.RESULT_TYPE);
            if (type == CodeUtils.RESULT_SUCCESS) {
                String result = bundle.getString(CodeUtils.RESULT_STRING);
                Toast.makeText(context, "解析结果: " + result, Toast.LENGTH_LONG).show();
            } else if (type == CodeUtils.RESULT_FAILED) {
                Toast.makeText(context, "解析二维码失败", Toast.LENGTH_LONG).show();
            }
        }
    }

    /**
     * 处理扫描结果（在界面上显示）
     */
    public static void startBarcodeGallery(Context context, Intent data) {
        final Context mContext;

        mContext = context;
        if (data != null) {
            Uri uri = data.getData();

            try {
                CodeUtils.analyzeBitmap(ImageUtil.getImageAbsolutePath(context, uri), new CodeUtils.AnalyzeCallback() {

                    @Override
                    public void onAnalyzeSuccess(Bitmap mBitmap, String result) {
                        Toast.makeText(mContext, "解析结果:" + result, Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onAnalyzeFailed() {
                        Toast.makeText(mContext, "解析二维码失败", Toast.LENGTH_LONG).show();
                    }
                });
            } catch (Exception e) {
                Log.d(TAG, "startBarcodeGallery exception: " + e.getMessage());
            }
        }
    }

}

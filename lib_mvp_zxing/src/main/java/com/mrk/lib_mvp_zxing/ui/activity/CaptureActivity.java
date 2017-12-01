package com.mrk.lib_mvp_zxing.ui.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.mrk.lib_mvp_zxing.R;
import com.mrk.lib_mvp_zxing.contract.XdjaCaptureContract;
import com.mrk.lib_mvp_zxing.ui.fragment.CaptureFragment;
import com.mrk.lib_mvp_zxing.utils.CodeUtils;

/**
 * Initial the camera
 * <p>
 * 默认的二维码扫描Activity
 */
public class CaptureActivity extends AppCompatActivity implements XdjaCaptureContract.XdjaCaptureView {
    static final String TAG = CaptureActivity.class.getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.camera);

        initData();
    }

    @Override
    public void initData() {
        CaptureFragment captureFragment = new CaptureFragment();
        captureFragment.setAnalyzeCallback(analyzeCallback);
        getSupportFragmentManager().beginTransaction().replace(R.id.fl_zxing_container, captureFragment).commit();
        captureFragment.setCameraInitCallBack(new CaptureFragment.CameraInitCallBack() {

            @Override
            public void callBack(Exception e) {
                if (e == null) {

                } else {
                    Log.d(TAG, "callBack: " + e.getMessage());
                }
            }
        });
    }

    /**
     * 二维码解析回调函数
     */
    CodeUtils.AnalyzeCallback analyzeCallback = new CodeUtils.AnalyzeCallback() {

        @Override
        public void onAnalyzeSuccess(Bitmap mBitmap, String result) {
            Intent resultIntent = new Intent();
            Bundle bundle = new Bundle();
            bundle.putInt(CodeUtils.RESULT_TYPE, CodeUtils.RESULT_SUCCESS);
            bundle.putString(CodeUtils.RESULT_STRING, result);
            resultIntent.putExtras(bundle);
            CaptureActivity.this.setResult(RESULT_OK, resultIntent);
            CaptureActivity.this.finish();
        }

        @Override
        public void onAnalyzeFailed() {
            Intent resultIntent = new Intent();
            Bundle bundle = new Bundle();
            bundle.putInt(CodeUtils.RESULT_TYPE, CodeUtils.RESULT_FAILED);
            bundle.putString(CodeUtils.RESULT_STRING, "");
            resultIntent.putExtras(bundle);
            CaptureActivity.this.setResult(RESULT_OK, resultIntent);
            CaptureActivity.this.finish();
        }
    };
}

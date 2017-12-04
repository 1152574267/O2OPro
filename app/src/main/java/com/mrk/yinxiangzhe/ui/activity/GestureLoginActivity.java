package com.mrk.yinxiangzhe.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.mrk.gesturelock.R;
import com.mrk.gesturelock.ui.activity.CreateGestureActivity;
import com.mrk.gesturelock.utils.ACache;
import com.mrk.gesturelock.utils.Constant;
import com.mrk.gesturelock.utils.LockPatternUtil;
import com.mrk.gesturelock.widget.LockPatternView;

import java.util.List;

/**
 * Created by Sym on 2015/12/24.
 */
public class GestureLoginActivity extends Activity {
    static final String TAG = GestureLoginActivity.class.getSimpleName();

    private LockPatternView lockPatternView;
    private ACache aCache;
    private Button forgetGestureBtn;
    private Button otherAccountBtn;
    private TextView messageTv;

    private static final long DELAYTIME = 600l;
    private byte[] gesturePassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gesture_login);

        initView();
        initData();
    }

    private void initView() {
        lockPatternView = (LockPatternView) findViewById(R.id.lockPatternView);
        forgetGestureBtn = (Button) findViewById(R.id.forgetGestureBtn);
        otherAccountBtn = (Button) findViewById(R.id.otherAccountBtn);
        messageTv = (TextView) findViewById(R.id.messageTv);
    }

    private void initData() {
        aCache = ACache.get(this);
        //得到当前用户的手势密码
        gesturePassword = aCache.getAsBinary(Constant.GESTURE_PASSWORD);
        lockPatternView.setOnPatternListener(patternListener);
        updateStatus(Status.DEFAULT);
        forgetGestureBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                forgetGesturePasswrod();
            }
        });
        otherAccountBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GestureLoginActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private LockPatternView.OnPatternListener patternListener = new LockPatternView.OnPatternListener() {

        @Override
        public void onPatternStart() {
            lockPatternView.removePostClearPatternRunnable();
        }

        @Override
        public void onPatternComplete(List<LockPatternView.Cell> pattern) {
            if (pattern != null) {
                if (LockPatternUtil.checkPattern(pattern, gesturePassword)) {
                    updateStatus(Status.CORRECT);
                } else {
                    updateStatus(Status.ERROR);
                }
            }
        }
    };

    /**
     * 更新状态
     *
     * @param status
     */
    private void updateStatus(Status status) {
        messageTv.setText(status.strId);
        messageTv.setTextColor(getResources().getColor(status.colorId));
        switch (status) {
            case DEFAULT:
                lockPatternView.setPattern(LockPatternView.DisplayMode.DEFAULT);
                break;
            case ERROR:
                lockPatternView.setPattern(LockPatternView.DisplayMode.ERROR);
                lockPatternView.postClearPatternRunnable(DELAYTIME);
                break;
            case CORRECT:
                lockPatternView.setPattern(LockPatternView.DisplayMode.DEFAULT);
                loginGestureSuccess();
                break;
        }
    }

    /**
     * 手势登录成功（去首页）
     */
    private void loginGestureSuccess() {
        Toast.makeText(this, "success", Toast.LENGTH_SHORT).show();
    }

    /**
     * 忘记手势密码（去账号登录界面）
     */
    void forgetGesturePasswrod() {
        Intent intent = new Intent(GestureLoginActivity.this, CreateGestureActivity.class);
        startActivity(intent);
        finish();
    }

    private enum Status {
        //默认的状态
        DEFAULT(R.string.gesture_default, R.color.grey_a5a5a5),
        //密码输入错误
        ERROR(R.string.gesture_error, R.color.red_f4333c),
        //密码输入正确
        CORRECT(R.string.gesture_correct, R.color.grey_a5a5a5);

        private Status(int strId, int colorId) {
            this.strId = strId;
            this.colorId = colorId;
        }

        private int strId;
        private int colorId;
    }
}

package com.babuwyt.consignor.ui.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.babuwyt.consignor.R;
import com.babuwyt.consignor.base.BaseActivity;
import com.babuwyt.consignor.finals.BaseURL;
import com.babuwyt.jonylibrary.bean.BaseBean;
import com.babuwyt.jonylibrary.inteface.MyCallBack;
import com.babuwyt.jonylibrary.util.MD5Utils;
import com.babuwyt.jonylibrary.util.UHelper;
import com.babuwyt.jonylibrary.util.XUtil;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by lenovo on 2017/9/6.
 */
@ContentView(R.layout.activity_reginster)
public class RegisterActivity extends BaseActivity {
    @ViewInject(R.id.et_name)
    EditText et_name;
    @ViewInject(R.id.tv_code)
    TextView tv_code;
    @ViewInject(R.id.et_code)
    EditText et_code;
    @ViewInject(R.id.et_psd)
    EditText et_psd;
    @ViewInject(R.id.et_psd_mt)
    EditText et_psd_mt;
    @ViewInject(R.id.tv_register)
    TextView tv_register;


    private int time=60;
    private Timer timer;
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {

            if(time>0){
                tv_code.setText("("+time+") 秒后获取");
                tv_code.setEnabled(false);
                tv_code.setBackgroundResource(R.color.main_gray_line);
            }else{

                timer.cancel();
                timer=null;
                time=60;
                tv_code.setText("获取验证码");
                tv_code.setEnabled(true);
                tv_code.setBackgroundResource(R.color.colorPrimary);
            }
        }
    };
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Event(value = {R.id.img_goback,R.id.tv_code,R.id.tv_register})
    private void getE(View v){
        switch (v.getId()){
            case R.id.img_goback:
                finish();
                break;
            case R.id.tv_code:
                getCode();
                break;
            case R.id.tv_register:
                isEmpty();
                break;
        }
    }
    private void isEmpty(){
        if (TextUtils.isEmpty(et_name.getText().toString())){
            UHelper.showToast(this,getString(R.string.PHONE_IS_NOT_EMPTY));
            return;
        }
        if (TextUtils.isEmpty(et_code.getText().toString())){
            UHelper.showToast(this,getString(R.string.AUTHCODE_IS_NOT_EMPTY));
            return;
        }
        if (TextUtils.isEmpty(et_psd.getText().toString())){
            UHelper.showToast(this,getString(R.string.PROMPT_PSD_IS_NOT_EMPTY));
            return;
        }
        if (et_psd.getText().toString().length()<6){
            UHelper.showToast(this,getString(R.string.PROMPT_PSD_IS_TOO_SHORT));
            return;
        }
        if (!et_psd.getText().toString().equalsIgnoreCase(et_psd_mt.getText().toString())){
            UHelper.showToast(this,getString(R.string.MAKETRUE_PSD_IS_SUCCESS));
            return;
        }
        Register();
    }
    /**
     * 60秒倒计时
     */

    private void timeDown(){
        if (!UHelper.isPhone(et_name.getText().toString().trim())){
            UHelper.showToast(this,getString(R.string.phone_fail));
            return;
        }
        timer=new Timer();
        TimerTask timerTask=new TimerTask() {
            @Override
            public void run() {
                time--;
                Message msg=handler.obtainMessage();
                msg.what=1;
                handler.sendMessage(msg);
            }
        };
        timer.schedule(timerTask,0,1000);

    }
    //注册
    private void Register(){
        Map<String,String> map=new HashMap<String, String>();
        map.put("authCode",et_code.getText().toString().trim());
        map.put("userPhone",et_name.getText().toString().trim());
        map.put("userPsd", MD5Utils.encrypt(et_name.getText().toString().trim()));

        XUtil.PostJson(BaseURL.REGISTER, map, new MyCallBack<BaseBean>() {
            @Override
            public void onSuccess(BaseBean result) {
                super.onSuccess(result);
                if (result.isSuccess()){
                    finish();
                }
                UHelper.showToast(RegisterActivity.this,result.getMsg());
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                super.onError(ex, isOnCallback);
            }
        });
    }
    //获取验证码
    private void getCode(){
        if (TextUtils.isEmpty(et_name.getText().toString())){
            UHelper.showToast(this,getString(R.string.PHONE_IS_NOT_EMPTY));
            return;
        }
        Map<String,String> map=new HashMap<String, String>();
        map.put("type","1");
        map.put("userPhone",et_name.getText().toString().trim());

        XUtil.PostJson(BaseURL.GETAUTHCODE, map, new MyCallBack<BaseBean>() {
            @Override
            public void onSuccess(BaseBean result) {
                super.onSuccess(result);
                if (result.isSuccess()){
                    timeDown();
                }
                UHelper.showToast(RegisterActivity.this,result.getMsg());
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                super.onError(ex, isOnCallback);
                Log.d("获取验证码",ex+"");
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (timer!=null){
            timer.cancel();
            timer=null;
        }
    }
}

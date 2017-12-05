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
import com.babuwyt.consignor.base.SessionManager;
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
@ContentView(R.layout.activity_forgetpsd)
public class ForgetPsdActivity extends BaseActivity {
    @ViewInject(R.id.img_goback)
    ImageView img_goback;
    @ViewInject(R.id.et_name)
    EditText et_name;
    @ViewInject(R.id.et_code)
    EditText et_code;
    @ViewInject(R.id.tv_code)
    TextView tv_code;
    @ViewInject(R.id.et_psd)
    EditText et_psd;
    @ViewInject(R.id.et_psd_mt)
    EditText et_psd_mt;
    @ViewInject(R.id.tv_mk)
    TextView tv_mk;
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

    @Event(value = {R.id.img_goback,R.id.tv_mk,R.id.tv_code})
    private void getE(View v){
        switch (v.getId()){
            case R.id.img_goback:
                finish();
                break;
            case R.id.tv_mk:
                isEmpty();
                break;
            case R.id.tv_code:
                getCode();
                break;
        }
    }

    /**
     * 60秒倒计时
     */

    private void timeDown(){

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

    //获取验证码
    private void getCode(){
        if (!UHelper.isPhone(et_name.getText().toString().trim())){
            UHelper.showToast(this,getString(R.string.PHONE_IS_FAIL));
            return;
        }
        Map<String,String> map=new HashMap<String, String>();
        map.put("type","3");
        map.put("userPhone",et_name.getText().toString().trim());

        XUtil.PostJson(BaseURL.GETAUTHCODE, map, new MyCallBack<BaseBean>() {
            @Override
            public void onSuccess(BaseBean result) {
                super.onSuccess(result);
                if (result.isSuccess()){
                    timeDown();
                    UHelper.showToast(ForgetPsdActivity.this,getString(R.string.send_authcode_to_this_phone));
                }else {
                    UHelper.showToast(ForgetPsdActivity.this,getString(R.string.send_authcode_fail));
                }

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                super.onError(ex, isOnCallback);
            }
        });
    }

    private void changePsd(){
        Map<String,String> map=new HashMap<String, String>();
        map.put("authCode",et_code.getText().toString());
        map.put("userPhone",et_name.getText().toString());
        map.put("userPsd", MD5Utils.encrypt(et_psd_mt.getText().toString()));
        XUtil.PostJson(BaseURL.FORGETPSD, map, new MyCallBack<BaseBean>() {
            @Override
            public void onSuccess(BaseBean result) {
                super.onSuccess(result);
                if (result.isSuccess()){
                    finish();
                    UHelper.showToast(ForgetPsdActivity.this,getString(R.string.changepsd_success));
                }else {
                    UHelper.showToast(ForgetPsdActivity.this,getString(R.string.changepsd_fail));
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                super.onError(ex, isOnCallback);
            }
        });
    }
    private void isEmpty(){
        if (!UHelper.isPhone(et_name.getText().toString().trim())){
            UHelper.showToast(this,getString(R.string.PHONE_IS_FAIL));
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
        if (!UHelper.isPsd(et_psd.getText().toString())){
            UHelper.showToast(this,getString(R.string.PROMPT_FORMAT_FAIL));
            return;
        }
        if (!et_psd.getText().toString().equalsIgnoreCase(et_psd_mt.getText().toString())){
            UHelper.showToast(this,getString(R.string.MAKETRUE_PSD_IS_SUCCESS));
            return;
        }
        changePsd();
    }

}

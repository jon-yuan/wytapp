package com.babuwyt.documentary.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.babuwyt.documentary.R;
import com.babuwyt.documentary.base.BaseActivity;
import com.babuwyt.documentary.base.ClientApp;
import com.babuwyt.documentary.bean.UserInfoBean;
import com.babuwyt.documentary.entity.UserInfoEntity;
import com.babuwyt.documentary.finals.BaseURL;
import com.babuwyt.documentary.inteface.MyCallBack;
import com.babuwyt.documentary.util.DensityUtils;
import com.babuwyt.documentary.util.DeviceUtils;
import com.babuwyt.documentary.util.UHelper;
import com.babuwyt.documentary.util.XUtil;
import com.babuwyt.jonylibrary.util.MD5Utils;
import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by lenovo on 2017/7/14.
 */
@ContentView(R.layout.activity_login)
public class LoginActivity extends BaseActivity {
    @ViewInject(R.id.et_name)
    EditText et_name;
    @ViewInject(R.id.et_psd)
    EditText et_psd;
    @ViewInject(R.id.tv_login)
    TextView tv_login;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }
    private void init(){

    }
    @Event(value = {R.id.tv_login})
    private void getE(View v){
        switch (v.getId()){
            case R.id.tv_login:
                getDate();
                break;
        }
    }

    private void getDate(){
        if (TextUtils.isEmpty(et_name.getText().toString())){
            UHelper.showToast(this,getString(R.string.please_input_zhanghao));
            return;
        }
        if (TextUtils.isEmpty(et_psd.getText().toString())){
            UHelper.showToast(this,getString(R.string.please_input_psd));
            return;
        }

        Map<String, String> map = new HashMap<>();
        map.put("floginname", et_name.getText().toString());
        map.put("fpassword", MD5Utils.encrypt(et_psd.getText().toString()));
        map.put("phonekey", DeviceUtils.id(this));
        loadingDialog.showDialog();
        XUtil.PostJson(BaseURL.OPERATION_LOGIN,map,new MyCallBack<UserInfoBean>(){
            @Override
            public void onSuccess(UserInfoBean result) {
                super.onSuccess(result);
                loadingDialog.dissDialog();
                Gson gson = new Gson();
                String obj2 = gson.toJson(result);
                Log.d("==floginname==",et_name.getText().toString());
                Log.d("==fpassword==",MD5Utils.encrypt(et_psd.getText().toString()));
                Log.d("==LoginActivity==",obj2);
                if (result.isSuccess()){
                    UHelper.showToast(LoginActivity.this,getString(R.string.login_succeed));
                    UserInfoEntity entity=result.getObj();
                    Log.d("floginname",entity.getFloginname());
                    ((ClientApp)getApplication()).saveLoginUser(entity);
                    startActivity(new Intent(LoginActivity.this,MainActivity.class));
                    finish();
                }else {
                    UHelper.showToast(LoginActivity.this,getString(R.string.login_faild));
//                    UserInfoEntity entity=new UserInfoEntity();
//                    entity.setFloginname("name");
//                    entity.setFscenename("name");
//                    ((ClientApp)getApplication()).saveLoginUser(entity);
                }
            }
            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                super.onError(ex, isOnCallback);
                loadingDialog.dissDialog();
                Log.d("失败",ex+"");
                UHelper.showToast(LoginActivity.this,getString(R.string.login_neterr));
            }
        });
    }
}

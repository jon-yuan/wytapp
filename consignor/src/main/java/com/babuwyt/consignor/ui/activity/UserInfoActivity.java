package com.babuwyt.consignor.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.babuwyt.consignor.R;
import com.babuwyt.consignor.base.BaseActivity;
import com.babuwyt.consignor.base.SessionManager;
import com.babuwyt.consignor.bean.UserBean;
import com.babuwyt.consignor.entity.UserInfoEntity;
import com.babuwyt.consignor.finals.BaseURL;
import com.babuwyt.jonylibrary.inteface.MyCallBack;
import com.babuwyt.jonylibrary.util.XUtil;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by lenovo on 2017/9/5.
 */
@ContentView(R.layout.activity_userinfo)
public class UserInfoActivity extends BaseActivity {
    @ViewInject(R.id.tv_userphone)
    TextView tv_userphone;
    @ViewInject(R.id.tv_company)
    TextView tv_company;
    @ViewInject(R.id.tv_username)
    TextView tv_username;
    @ViewInject(R.id.toolbar)
    Toolbar toolbar;
    private UserInfoEntity userInfoEntity;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
        getInfo();
    }

    private void init() {
        toolbar.setTitle(getString(R.string.gerenxinxi));
        toolbar.setNavigationIcon(R.drawable.goback_black);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void getInfo(){
        Map<String,String> map=new HashMap<String, String>();
        map.put("token", SessionManager.getInstance().getUser().getToken());
        map.put("userId", SessionManager.getInstance().getUser().getUserId());
        XUtil.PostJson(BaseURL.GETUSERINFO, map, new MyCallBack<UserBean>() {
            @Override
            public void onSuccess(UserBean result) {
                super.onSuccess(result);
                if (result.isSuccess()){
                    userInfoEntity=result.getObj();
                    if (userInfoEntity!=null){
                        tv_username.setText(userInfoEntity.getUserName());
                        tv_company.setText(userInfoEntity.getCompanyName());
                        tv_userphone.setText(userInfoEntity.getUserPhone());
                    }
                }
            }
            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                super.onError(ex, isOnCallback);
            }
        });
    }

    @Event(value = {R.id.tv_userphone})
    private void getE(View v){
        switch (v.getId()){
            case R.id.tv_userphone:
                toBind();
                break;
        }
    }
    private void toBind(){
        if (userInfoEntity!=null){
            if (TextUtils.isEmpty(userInfoEntity.getUserPhone())){
                startActivityForResult(new Intent(this,BindTelActivity.class),0);
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==0 && resultCode==1 ){
            tv_userphone.setText(data.getStringExtra("phonenumber"));
        }

    }
}

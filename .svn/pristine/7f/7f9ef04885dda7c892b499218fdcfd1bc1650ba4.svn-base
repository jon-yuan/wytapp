package com.babuwyt.consignor.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
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
import org.xutils.view.annotation.ViewInject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by lenovo on 2017/9/5.
 */
@ContentView(R.layout.activity_companyinfo)
public class CompanyInfoActivity extends BaseActivity {
    @ViewInject(R.id.tv_companycode)
    TextView tv_companycode;
    @ViewInject(R.id.tv_companyperson)
    TextView tv_companyperson;
    @ViewInject(R.id.tv_companyname)
    TextView tv_companyname;
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
        toolbar.setTitle(getString(R.string.companyinfo));
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
                        tv_companycode.setText(userInfoEntity.getCompanyCode());
                        tv_companyperson.setText(userInfoEntity.getCompanyPerson());
                        tv_companyname.setText(userInfoEntity.getCompanyName());
                    }
                }
            }
            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                super.onError(ex, isOnCallback);
            }
        });

    }
}

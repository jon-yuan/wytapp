package com.babuwyt.documentary.ui.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.babuwyt.documentary.R;
import com.babuwyt.documentary.base.BaseActivity;
import com.babuwyt.documentary.base.ClientApp;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

/**
 * Created by lenovo on 2017/8/8.
 */
@ContentView(R.layout.activity_setting)
public class SettingActivity extends BaseActivity {
    @ViewInject(R.id.toolbar)
    Toolbar toolbar;
    @ViewInject(R.id.btn_logout)
    Button btn_logout;
    @ViewInject(R.id.layout_chengePsd)
    LinearLayout layout_chengePsd;
    private Intent intent;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    private void init() {
        intent=new Intent();
        toolbar.setTitle(getString(R.string.setting));
        toolbar.setNavigationIcon(R.drawable.goback);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
    @Event(value ={R.id.btn_logout,R.id.layout_chengePsd})
    private void getE(View v){
        switch (v.getId()){
            case R.id.btn_logout:
                LogOut();
                break;
            case R.id.layout_chengePsd:
                intent.setClass(this,ChengePsdActivity.class);
                startActivity(intent);
                break;
        }
    }

    /**
     * 退出登录
     */
    private void LogOut(){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setMessage("确定退出登录?");
        builder.setPositiveButton(R.string.maketrue, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                ((ClientApp)getApplication()).clearLoginUser();
                Intent intent=new Intent(SettingActivity.this,LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }
        });
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.create();
        builder.show();
    }


}

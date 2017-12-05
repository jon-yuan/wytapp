package com.babuwyt.consignor.base;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.babuwyt.jonylibrary.views.dialog.LoadingDialog;

import org.xutils.x;

/**
 * Created by lenovo on 2017/6/28.
 */

public class BaseActivity extends AppCompatActivity {
    protected LoadingDialog loadingDialog;
    @SuppressLint({"NewApi", "ResourceAsColor"})
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getWindow().setNavigationBarColor(R.color.colorPrimaryDark); //写法一
        loadingDialog=new LoadingDialog(this);
        x.view().inject(this);
    }
}
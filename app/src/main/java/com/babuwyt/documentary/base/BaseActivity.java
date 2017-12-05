package com.babuwyt.documentary.base;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;


import com.babuwyt.documentary.R;
import com.babuwyt.documentary.ui.views.dialog.LoadingDialog;

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

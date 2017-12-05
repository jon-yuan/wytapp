package com.babuwyt.daili.utils.jpush;

import android.content.Intent;
import android.os.Bundle;
import android.view.ViewGroup.LayoutParams;
import android.widget.TextView;

import com.babuwyt.daili.base.BaseActivity;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by lenovo on 2017/7/18.
 */

public class TestActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TextView tv = new TextView(this);
        tv.setText("用户自定义打开的Activity");
        Intent intent = getIntent();
        if (null != intent) {
            Bundle bundle = getIntent().getExtras();
            String title = null;
            String content = null;
            if(bundle!=null){
                title = bundle.getString(JPushInterface.EXTRA_NOTIFICATION_TITLE);
                content = bundle.getString(JPushInterface.EXTRA_ALERT);
            }
            tv.setText("Title : " + title + "  " + "Content : " + content);
        }
        addContentView(tv, new LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.FILL_PARENT));
    }

}

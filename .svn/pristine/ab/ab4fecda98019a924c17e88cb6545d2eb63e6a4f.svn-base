package com.babuwyt.documentary.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.babuwyt.documentary.R;
import com.babuwyt.documentary.adapter.MsgCenterAdapter;
import com.babuwyt.documentary.base.BaseActivity;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;

/**
 * Created by lenovo on 2017/7/14.
 * 消息中心列表界面
 */
@ContentView(R.layout.activity_msg)
public class MsgCenterActivity extends BaseActivity implements AdapterView.OnItemClickListener{
    @ViewInject(R.id.listview)
    ListView listview;
    @ViewInject(R.id.toolbar)
    Toolbar toolbar;
    private MsgCenterAdapter mAdapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    private void init(){
        toolbar.setTitle(getString(R.string.msg_center));
        toolbar.setNavigationIcon(R.drawable.back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        mAdapter=new MsgCenterAdapter(this);

        ArrayList<String> mList=new ArrayList<String>();
        for (int i=0;i<20;i++){
            mList.add("测试"+i);
        }
        mAdapter.setmList(mList);
        listview.setAdapter(mAdapter);
        listview.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        startActivity(new Intent(this,MsgDetailsActivity.class));
    }
}

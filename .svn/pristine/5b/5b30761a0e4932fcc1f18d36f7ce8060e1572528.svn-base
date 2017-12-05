package com.babuwyt.documentary.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.ListView;

import com.babuwyt.documentary.R;
import com.babuwyt.documentary.adapter.MainAdapter;
import com.babuwyt.documentary.base.BaseActivity;
import com.babuwyt.documentary.base.SessionManager;
import com.babuwyt.documentary.bean.MainBean;
import com.babuwyt.documentary.entity.MainEntity;
import com.babuwyt.documentary.finals.BaseURL;
import com.babuwyt.documentary.inteface.MyCallBack;
import com.babuwyt.documentary.util.DefaultHeaders;
import com.babuwyt.documentary.util.XUtil;
import com.google.gson.Gson;
import com.liaoinstan.springview.container.DefaultFooter;
import com.liaoinstan.springview.widget.SpringView;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;

/**
 * Created by lenovo on 2017/10/13.
 */
@ContentView(R.layout.activity_test)
public class Testactivity extends BaseActivity {
    @ViewInject(R.id.springview)
    SpringView springview;
    @ViewInject(R.id.listviewpage)
    ListView listview;
    private ArrayList<MainEntity> mList;
    private MainAdapter mAdapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAdapter = new MainAdapter(this,1);
        mList = new ArrayList<MainEntity>();
        mAdapter.setmList(mList);
        listview.setAdapter(mAdapter);
        springview.setHeader(new DefaultHeaders(this, R.drawable.progress_small,R.drawable.icon_app_logo));
        springview.setFooter(new DefaultFooter(this));
        springview.setType(SpringView.Type.FOLLOW);
        springview.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                getHttp();
            }

            @Override
            public void onLoadmore() {
                getHttp();
            }
        });
    }
    private void getHttp() {
        ArrayList<String> list = new ArrayList<String>();
        list.add(SessionManager.getInstance().getUser().getFid());
//        list.add(SessionManager.getInstance().getEntity().getAdCode());
        list.add(0+"");
        list.add(1+"");
        XUtil.GetPing(BaseURL.OPERATIONGETHOMEPAGEDATA, list, new MyCallBack<MainBean>() {
            @Override
            public void onSuccess(MainBean result) {
                super.onSuccess(result);
                Gson gson=new Gson();
                String s=gson.toJson(result);
                Log.d("==result==",s);
                if (result.isSuccess()) {
                    if (mList != null) {
                        mList.clear();
                        mList.addAll(result.getObj());
                        mAdapter.notifyDataSetChanged();
                    }
                } else {
                    Log.d("result",result+"");
                }
//                refreshlayout.refreshComplete();
                springview.onFinishFreshAndLoad();
            }
            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                super.onError(ex, isOnCallback);
//                refreshlayout.refreshComplete();
                springview.onFinishFreshAndLoad();
                Log.d("+result+",ex+"");
            }
        });
    }
}

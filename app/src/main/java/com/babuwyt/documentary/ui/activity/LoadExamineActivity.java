package com.babuwyt.documentary.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.babuwyt.documentary.R;
import com.babuwyt.documentary.adapter.LoadExamineAdapter;
import com.babuwyt.documentary.base.BaseActivity;
import com.babuwyt.documentary.base.SessionManager;
import com.babuwyt.documentary.bean.LoadExamineBean;
import com.babuwyt.documentary.finals.BaseURL;
import com.babuwyt.documentary.inteface.MyCallBack;
import com.babuwyt.documentary.ui.views.CustomGridView;
import com.babuwyt.documentary.util.UHelper;
import com.babuwyt.documentary.util.XUtil;
import com.google.gson.Gson;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;

/**
 * Created by lenovo on 2017/7/14.
 * 该页面用做 装卸照片
 */

@ContentView(R.layout.activity_loadexamine)
public class LoadExamineActivity extends BaseActivity implements OnItemClickListener {

    @ViewInject(R.id.toolbar)
    Toolbar toolbar;
    @ViewInject(R.id.gridview)
    CustomGridView gridview;
    @ViewInject(R.id.tv_commit)
    TextView tv_commit;
    @ViewInject(R.id.layout_tongguo)
    RelativeLayout layout_tongguo;
    @ViewInject(R.id.layout_butongguo)
    RelativeLayout layout_butongguo;
    @ViewInject(R.id.check_true)
    ImageView check_true;
    @ViewInject(R.id.check_false)
    ImageView check_false;
    @ViewInject(R.id.layout_show)
    LinearLayout layout_show;
    @ViewInject(R.id.nodata)
    TextView nodata;
    @ViewInject(R.id.scrollView)
    ScrollView scrollView;

    private LoadExamineAdapter mAdapter;
    private ArrayList<String> paths;
    private String fsendcarno;
    private int check = 1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getIntents();
    }

    private void getIntents() {
        fsendcarno = getIntent().getStringExtra("fsendcarno");
        init();
        getHttp();
    }
    private void init() {
        toolbar.setTitle(getString(R.string.loading_examine));
        toolbar.setNavigationIcon(R.drawable.back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        mAdapter = new LoadExamineAdapter(this);
        paths = new ArrayList<String>();
        mAdapter.setmList(paths);
        gridview.setAdapter(mAdapter);

        gridview.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent intent = new Intent();
        intent.setClass(this, LookBigPicActivity.class);
        intent.putExtra(LookBigPicActivity.MLIST, paths);
        intent.putExtra("postion", (int) l);
        startActivity(intent);
    }

    @Event(value = {R.id.layout_tongguo, R.id.layout_butongguo,R.id.tv_commit})
    private void gete(View view) {
        switch (view.getId()){
            case R.id.layout_tongguo:
                check_true.setImageResource(R.drawable.yuan_true);
                check_false.setImageResource(R.drawable.yuan_false);
                check=1;
                break;
            case R.id.layout_butongguo:
                check_true.setImageResource(R.drawable.yuan_false);
                check_false.setImageResource(R.drawable.yuan_true);
                check=2;
                break;
            case R.id.tv_commit:
                getHttp1();
                break;
        }
    }

    private void getHttp() {
        ArrayList<String> list = new ArrayList<String>();
        list.add(fsendcarno+"");
        loadingDialog.showDialog();
        XUtil.GetPing(BaseURL.GETSENDCAROREDERP, list, new MyCallBack<LoadExamineBean>() {
            @Override
            public void onSuccess(LoadExamineBean result) {
                super.onSuccess(result);
                loadingDialog.dissDialog();
                Gson gson=new Gson();
                String s=gson.toJson(result);
                if (result.isSuccess()) {
                    if (paths != null) {
                        paths.clear();
                        paths.addAll(result.getObj());
                        mAdapter.notifyDataSetChanged();
                    }
                    UHelper.showToast(LoadExamineActivity.this, result.getMsg());
                } else {
                    UHelper.showToast(LoadExamineActivity.this, result.getMsg());
                }
                showData(result.isSuccess());

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                super.onError(ex, isOnCallback);
                loadingDialog.dissDialog();
                UHelper.showToast(LoadExamineActivity.this, getString(R.string.NET_ERROR));
            }
        });
    }

    private void getHttp1() {
        ArrayList<String> list = new ArrayList<String>();
        list.add(fsendcarno);
        list.add(SessionManager.getInstance().getUser().getFid());
        list.add(check+"");//1通过 2不通过
        loadingDialog.showDialog();
        XUtil.GetPing(BaseURL.CHECKINFO, list, new MyCallBack<LoadExamineBean>() {
            @Override
            public void onSuccess(LoadExamineBean result) {
                super.onSuccess(result);
                loadingDialog.dissDialog();
                if (result.isSuccess()) {
                    UHelper.showToast(LoadExamineActivity.this, result.getMsg());
                    finish();
                } else {
                    UHelper.showToast(LoadExamineActivity.this, result.getMsg());
                }

            }
            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                super.onError(ex, isOnCallback);
                loadingDialog.dissDialog();
                Log.d("服务器异常", ex + "");
                UHelper.showToast(LoadExamineActivity.this, getString(R.string.NET_ERROR));
            }
        });
    }
    /**
     * 判断是否有数据
     */
    private void showData(boolean b){
        if (b){
            nodata.setVisibility(View.GONE);
            scrollView.setVisibility(View.VISIBLE);
        }else {
            nodata.setVisibility(View.VISIBLE);
            scrollView.setVisibility(View.GONE);
        }
    }

}

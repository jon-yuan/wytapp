package com.babuwyt.documentary.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.babuwyt.documentary.R;
import com.babuwyt.documentary.adapter.SignPicAdapter;
import com.babuwyt.documentary.base.BaseActivity;
import com.babuwyt.documentary.base.SessionManager;
import com.babuwyt.documentary.bean.LoadExamineBean;
import com.babuwyt.documentary.bean.ReQuestBean;
import com.babuwyt.documentary.bean.SignPicBean;
import com.babuwyt.documentary.entity.SignPicEntity;
import com.babuwyt.documentary.finals.BaseURL;
import com.babuwyt.documentary.inteface.MyCallBack;
import com.babuwyt.documentary.inteface.SignPicAdapterCallBack;
import com.babuwyt.documentary.util.UHelper;
import com.babuwyt.jonylibrary.util.XUtil;
import com.babuwyt.jonylibrary.views.CustomGridView;
import com.google.gson.Gson;

import net.sf.json.JSON;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by lenovo on 2017/8/8.
 */
@ContentView(R.layout.activity_signpic)
public class SignPicActivity extends BaseActivity implements SignPicAdapterCallBack{
    @ViewInject(R.id.toolbar)
    Toolbar toolbar;
    @ViewInject(R.id.btn_commit)
    Button btn_commit;
    @ViewInject(R.id.layout_butongguo)
    RelativeLayout layout_butongguo;
    @ViewInject(R.id.layout_tongguo)
    RelativeLayout layout_tongguo;
    @ViewInject(R.id.check_false)
    ImageView check_false;
    @ViewInject(R.id.check_true)
    ImageView check_true;
    @ViewInject(R.id.gridview)
    CustomGridView gridview;
    @ViewInject(R.id.nodata)
    TextView nodata;
    @ViewInject(R.id.scrollView)
    ScrollView scrollView;

    private int check = 2;
    private SignPicAdapter mAdapter;
    private ArrayList<SignPicEntity> mList;
    private String fsendcarno;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fsendcarno=getIntent().getStringExtra("fsendcarno");
        init();
        getHttp();
    }

    private void init() {
        toolbar.setTitle(getString(R.string.qianshou_pic));
        toolbar.setNavigationIcon(R.drawable.goback);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        mAdapter=new SignPicAdapter(this);
        mList=new ArrayList<SignPicEntity>();
        mAdapter.setmList(mList);
        gridview.setAdapter(mAdapter);
        mAdapter.setSignPicAdapterCallBack(this);
    }





    @Event(value = {R.id.layout_tongguo, R.id.layout_butongguo,R.id.btn_commit})
    private void gete(View view) {
        switch (view.getId()){
            case R.id.layout_tongguo:
                check_true.setImageResource(R.drawable.yuan_true);
                check_false.setImageResource(R.drawable.yuan_false);
                check=2;
                break;
            case R.id.layout_butongguo:
                check_true.setImageResource(R.drawable.yuan_false);
                check_false.setImageResource(R.drawable.yuan_true);
                check=3;
                break;
            case R.id.btn_commit:
                getHttp1();
                break;
        }
    }

    private void getHttp() {
        ArrayList<String> list = new ArrayList<String>();
        list.add(fsendcarno+"");
        loadingDialog.showDialog();
        XUtil.GetPing(BaseURL.GETSENDCAROREDERP1, list, new MyCallBack<SignPicBean>() {
            @Override
            public void onSuccess(SignPicBean result) {
                super.onSuccess(result);
                loadingDialog.dissDialog();
                Gson gson=new Gson();
                String s=gson.toJson(result);
                Log.d("==result1==",result+"");
                if (result.isSuccess()) {
                    if (mList != null) {
                        mList.clear();
                        mList.addAll(result.getObj());
                        mAdapter.notifyDataSetChanged();
                    }
                    UHelper.showToast(SignPicActivity.this, result.getMsg());
                } else {
                    UHelper.showToast(SignPicActivity.this, result.getMsg());
                }
                showData(result.isSuccess());

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                super.onError(ex, isOnCallback);
                loadingDialog.dissDialog();
                Log.d("服务器异常", ex + "");
                UHelper.showToast(SignPicActivity.this, getString(R.string.NET_ERROR));
            }
        });
    }

    private void getHttp1() {
        String [] strings=new String[mList.size()];
        JSONArray jsonArray=new JSONArray();
        for (int i=0;i<mList.size();i++){
            strings[i]=mList.get(i).isIserror()?"true":"false";
            jsonArray.put(mList.get(i).isIserror()?"true":"false");
        }
        JSONObject jsonObject=new JSONObject();
        try {
            jsonObject.put("userid",SessionManager.getInstance().getUser().getFid());
            jsonObject.put("sendcarorderno",fsendcarno);
            jsonObject.put("check",jsonArray);
            jsonObject.put("fcheckstate",check);
            Log.d("==jsonObject==",jsonObject+"");

        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestParams params=new RequestParams();
        params.setUri(BaseURL.CHECKINFO1);
        params.setAsJsonContent(true);
        params.setBodyContent(jsonObject+"");
        loadingDialog.showDialog();
        XUtil.PostJson(params, new MyCallBack<LoadExamineBean>() {
            @Override
            public void onSuccess(LoadExamineBean result) {
                super.onSuccess(result);
                loadingDialog.dissDialog();
                if (result.isSuccess()) {
                    UHelper.showToast(SignPicActivity.this, result.getMsg());
                    finish();
                } else {
                    UHelper.showToast(SignPicActivity.this, result.getMsg());
                }
            }
            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                super.onError(ex, isOnCallback);
                loadingDialog.dissDialog();
                Log.d("服务器异常", ex + "");

                UHelper.showToast(SignPicActivity.this, getString(R.string.NET_ERROR));
            }
        });
    }

    @Override
    public void CallBack(int i) {
        boolean b=mList.get(i).isIserror();
        mList.get(i).setIserror(!b);
        mAdapter.notifyDataSetChanged();
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

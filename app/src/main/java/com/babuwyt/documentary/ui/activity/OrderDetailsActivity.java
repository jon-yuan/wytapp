package com.babuwyt.documentary.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.BoolRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.babuwyt.documentary.R;
import com.babuwyt.documentary.adapter.OrderDetailsAdapter;
import com.babuwyt.documentary.adapter.OrderDetailsStateAdapter;
import com.babuwyt.documentary.base.BaseActivity;
import com.babuwyt.documentary.base.SessionManager;
import com.babuwyt.documentary.bean.MainBean;
import com.babuwyt.documentary.bean.OrderDetailsBean;
import com.babuwyt.documentary.entity.orderdetailsentity.Fromtomap;
import com.babuwyt.documentary.entity.orderdetailsentity.OrderDetailsEntity;
import com.babuwyt.documentary.entity.orderdetailsentity.TworktracklistObj;
import com.babuwyt.documentary.finals.BaseURL;
import com.babuwyt.documentary.inteface.MyCallBack;
import com.babuwyt.documentary.util.UHelper;
import com.babuwyt.documentary.util.XUtil;
import com.babuwyt.jonylibrary.util.DateUtils;
import com.babuwyt.jonylibrary.views.CustomGridView;
import com.google.gson.Gson;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;

/**
 * Created by lenovo on 2017/7/17.
 * 订单详情界面
 */
@ContentView(R.layout.activity_orderdetails)
public class OrderDetailsActivity extends BaseActivity {
    @ViewInject(R.id.toolbar)
    Toolbar toolbar;
    @ViewInject(R.id.gridview)
    CustomGridView zhgridview;
    @ViewInject(R.id.img_zhuanghuo)
    ImageView img_zhuanghuo;
    @ViewInject(R.id.qianshou_gridview)
    CustomGridView qsgridview;
    @ViewInject(R.id.image_qianshou)
    ImageView image_qianshou;

    @ViewInject(R.id.layout_state)
    LinearLayout layout_state;

    @ViewInject(R.id.tv_state)
    TextView tv_state;
    @ViewInject(R.id.tv_ordernum)
    TextView tv_ordernum;
    @ViewInject(R.id.tv_form)
    TextView tv_form;
    @ViewInject(R.id.tv_to)
    TextView tv_to;
    @ViewInject(R.id.tv_timeti)
    TextView tv_timeti;
    @ViewInject(R.id.tv_timexie)
    TextView tv_timexie;
    @ViewInject(R.id.tv_name)
    TextView tv_name;
    @ViewInject(R.id.tv_beizhu)
    TextView tv_beizhu;

    private OrderDetailsAdapter zhAdapter;
    private OrderDetailsAdapter qsAdapter;
    private String fsendcarno;
    private String Fownerid;
    private ArrayList<String> mList;
    private ArrayList<String> qsList;
    private ArrayList<TworktracklistObj> stateList;
    private boolean showZH=true;//装货照片是否展开
    private boolean showQS=true;//签收照片是否展开
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fsendcarno=getIntent().getStringExtra("fsendcarno");
        Fownerid=getIntent().getStringExtra("Fownerid");
        init();
        getHttp();
    }

    private void init() {
        toolbar.setTitle(getString(R.string.orderdetails));
        toolbar.setNavigationIcon(R.drawable.back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        zhAdapter=new OrderDetailsAdapter(this);
        mList=new ArrayList<String>();
        zhAdapter.setmList(mList);
        zhgridview.setAdapter(zhAdapter);
        zhAdapter.showView(showZH,zhgridview);

        qsList=new ArrayList<String>();
        qsAdapter=new OrderDetailsAdapter(this);
        qsAdapter.setmList(qsList);
        qsgridview.setAdapter(qsAdapter);
        qsAdapter.showView(showQS,qsgridview);



    }
    private void setData(OrderDetailsEntity b){
        String str="";
        tv_ordernum.setText(b.getFsendcarno());
        for (Fromtomap fromtomap: b.getFromtolist()){
            if (fromtomap.getFromto().equals("FROM")){
                tv_form.setText(fromtomap.getFaddress());
                break;
            }
        }
        for (Fromtomap fromtomap: b.getFromtolist()){
            if (fromtomap.getFromto().equals("TO")){
                tv_to.setText(fromtomap.getFaddress());
            }
        }
        tv_name.setText(b.getOrdergood());
        tv_timeti.setText(DateUtils.timedate1(b.getFpicktime()));
        tv_timexie.setText(DateUtils.timedate1(b.getFpickuptime()));
        switch (b.getFtaskstate()) {
            case "1":
                str="装货已签到";
                break;
            case "2":
                str="装货已拍照";
                break;
            case "3":
                str="已装货（装货照片已审核）";
                break;
            case "4":
                str="卸货已签到";
                break;
            case "5":
                str="已卸货（签收单照片已审核）";
                break;
            case "6":
                str="签收单已交回";
                break;
            case "7":
                str="签收单已确认";
                break;
        }
        tv_state.setText(str);
        tv_beizhu.setText("");
        if (mList!=null){
            mList.clear();
            mList.addAll(b.getPricture());
            zhAdapter.notifyDataSetChanged();
        }
        if (qsList!=null){
            qsList.clear();
            qsList.addAll(b.getPricture1());
            qsAdapter.notifyDataSetChanged();
        }

    }
    private void getHttp(){
        ArrayList<String> list=new ArrayList<String>();
        list.add(fsendcarno);
        XUtil.GetPing(BaseURL.ORDER_DETAILED,list,new MyCallBack<OrderDetailsBean>(){
            @Override
            public void onSuccess(OrderDetailsBean result) {
                super.onSuccess(result);
                Gson gson=new Gson();
                gson.toJson(result);
                Log.d("==details==",gson.toJson(result));
                if (result.isSuccess()){
                    setData(result.getObj());
                }else {
                    UHelper.showToast(OrderDetailsActivity.this,result.getMsg());
                }

            }
            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                super.onError(ex, isOnCallback);
                Log.d("服务器异常",ex+"");
            }
        });
    }
    @Event(value = {R.id.img_zhuanghuo,R.id.image_qianshou,R.id.layout_state})
    private void getE(View v){
        switch (v.getId()){
            case R.id.img_zhuanghuo:
                showZH=!showZH;
                zhAdapter.showView(showZH,zhgridview);
                break;
            case R.id.image_qianshou:
                showQS=!showQS;
                qsAdapter.showView(showQS,qsgridview);
                break;

            case R.id.layout_state:
                Intent intent=new Intent(this,StateGenzongActivity.class);
                intent.putExtra("Fownerid",Fownerid);
                startActivity(intent);
                break;
        }
    }




}

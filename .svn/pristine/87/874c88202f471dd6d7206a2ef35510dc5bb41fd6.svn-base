package com.babuwyt.consignor.ui.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.babuwyt.consignor.R;
import com.babuwyt.consignor.adapter.MyOrderAdapter;
import com.babuwyt.consignor.base.BaseActivity;
import com.babuwyt.consignor.base.SessionManager;
import com.babuwyt.consignor.bean.OrderListBean;
import com.babuwyt.consignor.entity.OrderEntity;
import com.babuwyt.consignor.finals.BaseURL;
import com.babuwyt.jonylibrary.inteface.MyCallBack;
import com.babuwyt.jonylibrary.util.DateUtils;
import com.babuwyt.jonylibrary.util.UHelper;
import com.babuwyt.jonylibrary.util.XUtil;
import com.bigkoo.pickerview.TimePickerView;
import com.liaoinstan.springview.container.DefaultFooter;
import com.liaoinstan.springview.container.DefaultHeader;
import com.liaoinstan.springview.widget.SpringView;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

//import com.bigkoo.pickerview.TimePickerView;

/**
 * Created by lenovo on 2017/8/1.
 */
@ContentView(R.layout.activity_myorder)
public class MyOrderActivity extends BaseActivity{
    @ViewInject(R.id.toolbar)
    Toolbar toolbar;
    @ViewInject(R.id.listview)
    ListView listview;
    @ViewInject(R.id.springview)
    SpringView springview;
    @ViewInject(R.id.tv_time_select)
    TextView tv_time_select;
    @ViewInject(R.id.layout_show)
    LinearLayout layout_show;
    @ViewInject(R.id.tv_select)
    TextView tv_select;
    @ViewInject(R.id.layout_starY)
    LinearLayout layout_starY;
    @ViewInject(R.id.tv_startM)
    TextView tv_startM;
    @ViewInject(R.id.tv_startY)
    TextView tv_startY;
    @ViewInject(R.id.layout_endY)
    LinearLayout layout_endY;
    @ViewInject(R.id.tv_endM)
    TextView tv_endM;
    @ViewInject(R.id.tv_endY)
    TextView tv_endY;
    private int isStartOrEnd=-1; //1为开始时间  2为结束时间

    private ArrayList<OrderEntity> mList;
    private MyOrderAdapter mAdapter;

    private boolean isshow=false;

    private String StartY;
    private String StartMD;

    private String EndY;
    private String EndMD;
    private SimpleDateFormat Y;
    private SimpleDateFormat MD;
    private String uptime;
    private String startTime;
    private String entime;
    private String endTime;
    private Intent intent;
    private int pagenum=1;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
        initView();
        initRefresh();
        getOrder();
    }
    private void init() {
        intent=new Intent();
        toolbar.setTitle(getString(R.string.my_order));
        toolbar.setNavigationIcon(R.drawable.goback_black);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void initRefresh(){
        springview.setType(SpringView.Type.FOLLOW);
        springview.setHeader(new DefaultHeader(this,R.drawable.pullrefresh_progressbar,R.drawable.icon_arrow_down));
        springview.setFooter(new DefaultFooter(this));
        springview.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                getOrder();
            }

            @Override
            public void onLoadmore() {
                getOrderMore();
            }
        });
    }

    @SuppressLint("NewApi")
    private void initView() {
        setEmptyView();
        mList=new ArrayList<OrderEntity>();
        mAdapter=new MyOrderAdapter(this);
        mAdapter.setmList(mList);
        listview.setAdapter(mAdapter);

        Date date=new Date();
        Y=new SimpleDateFormat("yyyy年");
        MD=new SimpleDateFormat("MM月dd日");
        startTime=DateUtils.DateToString(date, DateUtils.type1);
        endTime=DateUtils.DateToString(date, DateUtils.type1);
        StartY=Y.format(date);
        StartMD=MD.format(date);
        uptime=StartY.replace("年","-")+StartMD.replace("月","-").replace("日","");

        EndY=Y.format(date);
        EndMD=MD.format(date);
        entime=EndY.replace("年","-")+EndMD.replace("月","-").replace("日","");
        Log.d("时间",uptime+""+entime);
        tv_startY.setText(StartY);
        tv_startM.setText(StartMD);
        tv_endY.setText(EndY);
        tv_endM.setText(EndMD);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                intent.setClass(MyOrderActivity.this,HistoryOrderDetailsActivity.class);
                intent.putExtra("orderId",mList.get(i).getOrderId());
                startActivity(intent);
            }
        });
    }

    @SuppressLint("ResourceAsColor")
    private void setEmptyView(){
        TextView emptyView = new TextView(this);
        emptyView.setTextColor(R.color.black_666);
        emptyView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        emptyView.setText("还没有订单");
        emptyView.setGravity(Gravity.CENTER_HORIZONTAL|Gravity.CENTER_VERTICAL);
        emptyView.setVisibility(View.GONE);
        ((ViewGroup)listview.getParent()).addView(emptyView);
        listview.setEmptyView(emptyView);
    }
    //获取当前时间
    private void getOrder(){
        pagenum=1;
        Map<String,Object> map=new HashMap<String, Object>();
        map.put("token",SessionManager.getInstance().getUser().getToken());
        map.put("userId",SessionManager.getInstance().getUser().getUserId());
        map.put("type",5);
        map.put("pageNum",pagenum);
        loadingDialog.showDialog();
        XUtil.PostJsonObj(BaseURL.GETORDERLIST, map, new MyCallBack<OrderListBean>() {
            @Override
            public void onSuccess(OrderListBean baseBean) {
                loadingDialog.dissDialog();
                if (baseBean.isSuccess() && baseBean.getObj()!=null && baseBean.getObj().size()>0){
                    mList.clear();
                    mList.addAll(baseBean.getObj());
                    mAdapter.notifyDataSetChanged();
                }else {
                    UHelper.showToast(MyOrderActivity.this,getString(R.string.has_no_data));
                }
                springview.onFinishFreshAndLoad();
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                loadingDialog.dissDialog();
                springview.onFinishFreshAndLoad();
            }
        });
    }

    private void getOrderMore(){
        pagenum++;
        Map<String,Object> map=new HashMap<String, Object>();
        map.put("token",SessionManager.getInstance().getUser().getToken());
        map.put("userId",SessionManager.getInstance().getUser().getUserId());
        map.put("type",5);
        map.put("pageNum",pagenum);
        loadingDialog.showDialog();
        XUtil.PostJsonObj(BaseURL.GETORDERLIST, map, new MyCallBack<OrderListBean>() {
            @Override
            public void onSuccess(OrderListBean baseBean) {
                loadingDialog.dissDialog();
                if (baseBean.isSuccess() && baseBean.getObj()!=null && baseBean.getObj().size()>0){
                    mList.addAll(baseBean.getObj());
                    mList.add(new OrderEntity());
                    mAdapter.notifyDataSetChanged();
                }else {
                    UHelper.showToast(MyOrderActivity.this,getString(R.string.has_no_data));
                }
                springview.onFinishFreshAndLoad();
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                loadingDialog.dissDialog();
                springview.onFinishFreshAndLoad();
            }
        });
    }
    //根据时间搜索
    private void searchOrder(){
        Map<String,Object> map=new HashMap<String, Object>();
        map.put("token",SessionManager.getInstance().getUser().getToken());
        map.put("userId",SessionManager.getInstance().getUser().getUserId());
        map.put("type",1);
        map.put("pageNum",1);
        map.put("startTime",startTime);
        map.put("endTime",endTime);
        loadingDialog.showDialog();
        XUtil.PostJsonObj(BaseURL.GETORDERLIST, map, new MyCallBack<OrderListBean>() {
            @Override
            public void onSuccess(OrderListBean baseBean) {
                loadingDialog.dissDialog();
                if (baseBean.isSuccess() && baseBean.getObj()!=null && baseBean.getObj().size()>0){

                    mList.clear();
                    mList.addAll(baseBean.getObj());
                    mAdapter.notifyDataSetChanged();
                }else {
                    UHelper.showToast(MyOrderActivity.this,getString(R.string.has_no_data));
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                loadingDialog.dissDialog();
            }
        });
    }

    @Event(value = {R.id.tv_time_select,R.id.tv_select,R.id.layout_starY,R.id.layout_endY})
    private void gete(View view){
        switch (view.getId()){
            case R.id.tv_time_select:
                showTimeSelectView();
                break;
            case R.id.tv_select:
                searchOrder();
                break;

            case R.id.layout_starY:
                isStartOrEnd=1;
                showTimeSelect();
                break;
            case R.id.layout_endY:
                isStartOrEnd=2;
                showTimeSelect();
                break;
        }
    }

    private void showTimeSelectView(){
        if (isshow){
            isshow=false;
            layout_show.setVisibility(View.GONE);
        }else {
            isshow=true;
            layout_show.setVisibility(View.VISIBLE);
        }
    }
    private void showTimeSelect(){

        TimePickerView pvTime = new TimePickerView.Builder(this, new TimePickerView.OnTimeSelectListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onTimeSelect(Date date, View v) {//选中事件回调
                if (isStartOrEnd==1){
                    startTime = DateUtils.DateToString(date, DateUtils.type1);
                    StartY=Y.format(date);
                    StartMD=MD.format(date);
                    tv_startY.setText(StartY);
                    tv_startM.setText(StartMD);
                    uptime=StartY.replace("年","-")+StartMD.replace("月","-").replace("日","");
                }else {
                    endTime = DateUtils.DateToString(date, DateUtils.type1);
                    EndY=Y.format(date);
                    EndMD=MD.format(date);
                    tv_endY.setText(EndY);
                    tv_endM.setText(EndMD);
                    entime=EndY.replace("年","-")+EndMD.replace("月","-").replace("日","");
                }
            }
        }).setType(new boolean[]{true, true, true, false, false, false})// 默认全部显示
           .setLabel("","","","","","")//默认设置为年月日时分秒
            .build();
        pvTime.show();

    }
}

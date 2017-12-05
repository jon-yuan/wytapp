package com.babuwyt.consignor.ui.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.babuwyt.consignor.R;
import com.babuwyt.consignor.adapter.GenzongOrderAdapter;
import com.babuwyt.consignor.base.BaseFragment;
import com.babuwyt.consignor.base.SessionManager;
import com.babuwyt.consignor.bean.OrderListBean;
import com.babuwyt.consignor.entity.OrderEntity;
import com.babuwyt.consignor.finals.BaseURL;
import com.babuwyt.consignor.finals.DataSynEvent;
import com.babuwyt.consignor.ui.activity.GuijiActivity;
import com.babuwyt.consignor.ui.activity.StateGenzongActivity;
import com.babuwyt.consignor.ui.activity.WeiZhiActivity;
import com.babuwyt.jonylibrary.inteface.MyCallBack;
import com.babuwyt.jonylibrary.util.UHelper;
import com.babuwyt.jonylibrary.util.XUtil;
import com.liaoinstan.springview.container.DefaultFooter;
import com.liaoinstan.springview.container.DefaultHeader;
import com.liaoinstan.springview.widget.SpringView;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import de.greenrobot.event.EventBus;

/**
 * Created by lenovo on 2017/9/10.
 */
@ContentView(R.layout.fragment_genzongorder)
public class GenzongOrderFragment extends BaseFragment{
    @ViewInject(R.id.toolbar)
    Toolbar toolbar;
    @ViewInject(R.id.listview)
    ListView listview;
    @ViewInject(R.id.springview)
    SpringView springview;
    private GenzongOrderAdapter mAdapter;
    private ArrayList<OrderEntity> mList;
    private int type;
    private Intent intent;
    private int pageNum=1;
    public static GenzongOrderFragment newInstance(int type) {
        GenzongOrderFragment fragment = new GenzongOrderFragment();
        Bundle args = new Bundle();
        args.putInt("type", type);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            type = getArguments().getInt("type");
        }
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        init();
        initRefresh();
        getOrder();
    }
    private void initRefresh(){
        springview.setType(SpringView.Type.FOLLOW);
        springview.setHeader(new DefaultHeader(getActivity(),R.drawable.pullrefresh_progressbar,R.drawable.icon_arrow_down));
        springview.setFooter(new DefaultFooter(getActivity()));
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

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
    @SuppressLint("ResourceAsColor")
    private void setEmptyView(){
        TextView emptyView = new TextView(getActivity());
        emptyView.setTextColor(R.color.black_666);
        emptyView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        emptyView.setText("还没有可以跟踪的订单！");
        emptyView.setGravity(Gravity.CENTER_HORIZONTAL|Gravity.CENTER_VERTICAL);
        emptyView.setVisibility(View.GONE);
        ((ViewGroup)listview.getParent()).addView(emptyView);
        listview.setEmptyView(emptyView);
    }
    private void init() {
        setEmptyView();
        intent=new Intent();
        toolbar.setVisibility(type);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DataSynEvent event=new DataSynEvent();
                event.setType(event.DRAWER_LAYOUT_OPEN);
                EventBus.getDefault().post(event);
            }
        });

        mAdapter=new GenzongOrderAdapter(getActivity());
        listview.setAdapter(mAdapter);
        mList=new ArrayList<OrderEntity>();
        mAdapter.setmList(mList);
        mAdapter.setCallBack(new GenzongOrderAdapter.CallBack() {
            @Override
            public void weizhi(int position) {
                intent.setClass(getActivity(), WeiZhiActivity.class);
                intent.putExtra("orderId",mList.get(position).getOrderId());
                intent.putExtra("name",mList.get(position).getDriverName());
                startActivity(intent);
            }

            @Override
            public void guiji(int position) {
                intent.setClass(getActivity(), GuijiActivity.class);
                intent.putExtra("orderId",mList.get(position).getOrderId());
                startActivity(intent);
            }

            @Override
            public void zhuangtai(int position) {
                intent.setClass(getActivity(), StateGenzongActivity.class);
                intent.putExtra("orderId",mList.get(position).getOrderId());
                startActivity(intent);
            }
        });
    }

    private void getOrder() {
        pageNum=1;
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("userId", SessionManager.getInstance().getUser().getUserId());
        map.put("type", 6);
        map.put("pageNum", pageNum);
        XUtil.PostJsonObj(BaseURL.GETORDERLIST, map, new MyCallBack<OrderListBean>() {
            @Override
            public void onSuccess(OrderListBean result) {
                super.onSuccess(result);
                if (result.isSuccess()) {
                    ArrayList<OrderEntity> list = result.getObj();
                    if (list!=null && list.size()>0){
                        mList.clear();
                        mList.addAll(list);
                        mAdapter.notifyDataSetChanged();
                    }else {
                        UHelper.showToast(getActivity(),getString(R.string.has_no_data));
                    }
                }else {
                    UHelper.showToast(getActivity(),result.getMsg());
                }
                springview.onFinishFreshAndLoad();
            }
            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                super.onError(ex, isOnCallback);
                springview.onFinishFreshAndLoad();
            }
        });
    }
    private void getOrderMore() {
        pageNum++;
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("userId", SessionManager.getInstance().getUser().getUserId());
        map.put("type", 6);
        map.put("pageNum", pageNum);
        loadingDialog.showDialog();
        XUtil.PostJsonObj(BaseURL.GETORDERLIST, map, new MyCallBack<OrderListBean>() {
            @Override
            public void onSuccess(OrderListBean result) {
                super.onSuccess(result);
                loadingDialog.dissDialog();
                if (result.isSuccess()) {
                    ArrayList<OrderEntity> list = result.getObj();
                    if (list!=null && list.size()>0){
                        mList.addAll(list);

                        mAdapter.notifyDataSetChanged();
                    }
                }else {
                    UHelper.showToast(getActivity(),result.getMsg());
                }
                mList.add(new OrderEntity());
                mAdapter.notifyDataSetChanged();
                springview.onFinishFreshAndLoad();
            }
            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                super.onError(ex, isOnCallback);
                loadingDialog.dissDialog();
                springview.onFinishFreshAndLoad();
            }
        });
    }
}
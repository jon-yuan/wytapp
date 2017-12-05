package com.babuwyt.documentary.ui.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.babuwyt.documentary.R;
import com.babuwyt.documentary.adapter.MainAdapter;
import com.babuwyt.documentary.base.BaseFragment;
import com.babuwyt.documentary.base.SessionManager;
import com.babuwyt.documentary.bean.MainBean;
import com.babuwyt.documentary.entity.MainEntity;
import com.babuwyt.documentary.finals.BaseURL;
import com.babuwyt.documentary.inteface.MainAdapterCallBack;
import com.babuwyt.documentary.inteface.MyCallBack;
import com.babuwyt.documentary.ui.activity.GuijiActivity;
import com.babuwyt.documentary.ui.activity.LoadExamineActivity;
import com.babuwyt.documentary.ui.activity.OrderDetailsActivity;
import com.babuwyt.documentary.ui.activity.SignPicActivity;
import com.babuwyt.documentary.ui.activity.WeiZhiActivity;
import com.babuwyt.documentary.ui.views.dialog.LoadingDialog;
import com.babuwyt.documentary.util.UHelper;
import com.babuwyt.documentary.util.XUtil;
import com.babuwyt.jonylibrary.util.ViewUtils;
import com.google.gson.Gson;
import com.liaoinstan.springview.container.DefaultFooter;
import com.liaoinstan.springview.container.DefaultHeader;
import com.liaoinstan.springview.widget.SpringView;

import java.util.ArrayList;

/**
 * Created by lenovo on 2017/8/7.
 */
//@ContentView(R.layout.fragment_mainpager)
public class MainPagerFragment extends BaseFragment implements MainAdapterCallBack{
    ListView listview;
//    RefreshLayout refreshlayout;
    SpringView springview;

    private int type = 0;
    private MainAdapter mAdapter;
    private Intent intent;
    private ArrayList<MainEntity> mList;
    private LoadingDialog loadingDialog;

    public static MainPagerFragment newInstance(int type) {
        MainPagerFragment fragment = new MainPagerFragment();
        Bundle args = new Bundle();
        args.putInt("type", type);
        fragment.setArguments(args);
        return fragment;
    }
    protected boolean hasData;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            type = getArguments().getInt("type");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_mainpager,container,false);
        init(view);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//        init();
        getHttp();
    }

    @SuppressLint("ResourceAsColor")
    private void init(View view) {
        loadingDialog = new LoadingDialog(getActivity());
        intent = new Intent();
        mList = new ArrayList<MainEntity>();
        mAdapter = new MainAdapter(getContext(),type);
        mAdapter.setCallBack(this);
        mAdapter.setmList(mList);
        listview=view.findViewById(R.id.listviewpage);
//        refreshlayout=view.findViewById(R.id.refreshlayout);
        springview=view.findViewById(R.id.springview);
        ViewUtils.setEmptyListView(getActivity(),listview,getString(R.string.NO_DATA));
        listview.setAdapter(mAdapter);
        mAdapter.setCallBack(this);
        springview.setType(SpringView.Type.FOLLOW);
        springview.setHeader(new DefaultHeader(getActivity(),R.drawable.pullrefresh_progressbar,R.drawable.icon_arrow_down));
        springview.setFooter(new DefaultFooter(getActivity()));
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
    @Override
    public void onVisible() {
        if (isInit) {
            getHttp();
        }
    }
    /**
     * getHttp
     * 根据type判断 0 待审核  1 进行中
     */
    private void getHttp() {
        if (!isVisible){
//            refreshlayout.refreshComplete();
            return;
        }
//        if (TextUtils.isEmpty(SessionManager.getInstance().getEntity().getAdCode())){
//            refreshlayout.refreshComplete();
//            com.babuwyt.jonylibrary.util.UHelper.showToast(getActivity(),"定位地址不正确");
//            return;
//        }
        ArrayList<String> list = new ArrayList<String>();
        list.add(SessionManager.getInstance().getUser().getFid());
//        list.add(SessionManager.getInstance().getEntity().getAdCode());
        list.add(0+"");
        list.add(type+"");
        loadingDialog.showDialog();
        XUtil.GetPing(BaseURL.OPERATIONGETHOMEPAGEDATA, list, new MyCallBack<MainBean>() {
            @Override
            public void onSuccess(MainBean result) {
                super.onSuccess(result);
                Gson gson=new Gson();
                String s=gson.toJson(result);
                Log.d("==result==",s);


                loadingDialog.dissDialog();
                hasData = true;
                if (result.isSuccess()) {
                    if (mList != null) {
                        mList.clear();
                        mList.addAll(result.getObj());
                        mAdapter.notifyDataSetChanged();
                    }
                } else {
                    Log.d("result",result+"");
                    UHelper.showToast(getContext(), result.getMsg());
                }
//                refreshlayout.refreshComplete();
                springview.onFinishFreshAndLoad();
            }
            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                super.onError(ex, isOnCallback);
//                refreshlayout.refreshComplete();
                springview.onFinishFreshAndLoad();
                loadingDialog.dissDialog();
                Log.d("+result+",ex+"");
                UHelper.showToast(getContext(),getString(R.string.NET_ERROR));
            }
        });
    }
    @Override
    public void weizhiCallBack(int position, MainEntity o) {
        intent.setClass(getContext(), WeiZhiActivity.class);
        intent.putExtra("fsendcarno", o.getfSendCarNo());
        intent.putExtra("name",o.getDriverName());
        startActivity(intent);
    }

    @Override
    public void guijiCallBack(int position, MainEntity o) {
        intent.setClass(getContext(), GuijiActivity.class);
        intent.putExtra("fid",o.getFid());
        startActivity(intent);
    }

    @Override
    public void examineCallBack(int position, MainEntity o) {
        intent.setClass(getContext(), LoadExamineActivity.class);
        intent.putExtra("fsendcarno", o.getfSendCarNo());
        startActivity(intent);
    }

    @Override
    public void qianshouCallBack(int position, MainEntity o) {
        intent.setClass(getContext(), SignPicActivity.class);
//        intent.putExtra(LoadExamineActivity.INTENT_TYPE,LoadExamineActivity.INTENT_QIANSHOU);
        intent.putExtra("fsendcarno",o.getfSendCarNo());
        startActivity(intent);
    }

    @Override
    public void detailsCallBack(int position, MainEntity o) {
        intent.setClass(getContext(), OrderDetailsActivity.class);
        intent.putExtra("fsendcarno", o.getfSendCarNo());
        intent.putExtra("Fownerid", o.getFownerid());
        startActivity(intent);
    }

}

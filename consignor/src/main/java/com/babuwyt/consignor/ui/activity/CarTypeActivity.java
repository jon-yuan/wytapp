package com.babuwyt.consignor.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.babuwyt.consignor.R;
import com.babuwyt.consignor.base.BaseActivity;
import com.babuwyt.consignor.bean.TypeBean;
import com.babuwyt.consignor.entity.TypeEntity;
import com.babuwyt.consignor.finals.BaseURL;
import com.babuwyt.jonylibrary.inteface.MyCallBack;
import com.babuwyt.jonylibrary.util.UHelper;
import com.babuwyt.jonylibrary.util.XUtil;
import com.google.gson.Gson;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by lenovo on 2017/9/13.
 */
@ContentView(R.layout.activity_cartype)
public class CarTypeActivity extends BaseActivity {

    @ViewInject(R.id.listview)
    ListView listview;
    @ViewInject(R.id.toolbar)
    Toolbar toolbar;
    private ArrayList<TypeEntity> mList;
    private CarTypeAdapter mAdapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
        getType();
    }

    private void init() {
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        mList=new ArrayList<TypeEntity>();
        mAdapter=new CarTypeAdapter();
        listview.setAdapter(mAdapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent=new Intent();
                intent.putExtra(AddOrderActivity.CARTYPE,mList.get(i));
                setResult(AddOrderActivity.CAR_TYPE_RESULT,intent);
                finish();
            }
        });
    }


    private void getType(){
        ArrayList<String> list=new ArrayList<String>();
        list.add("1");
        loadingDialog.showDialog();
        XUtil.GetPing(BaseURL.GETSYSTYPE, list, new MyCallBack<TypeBean>() {
            @Override
            public void onSuccess(TypeBean result) {
                super.onSuccess(result);
                loadingDialog.dissDialog();
                if (result.isSuccess()){
                    mList.clear();
                    mList.addAll(result.getObj());
                    mAdapter.notifyDataSetChanged();
                }
                Log.d("数据",new Gson().toJson(result));
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                super.onError(ex, isOnCallback);
                loadingDialog.dissDialog();
                Log.d("获取类型",ex+"");
            }
        });
    }







    private class CarTypeAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return mList.size();
        }

        @Override
        public Object getItem(int i) {
            return i;
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            ViewHolder holder=null;
            if (view==null){
                holder=new ViewHolder();
                view= LayoutInflater.from(CarTypeActivity.this).inflate(R.layout.adapter_cartype,null);
                x.view().inject(holder,view);
                view.setTag(holder);
            }else {
                holder= (ViewHolder) view.getTag();
            }
            holder.tv_type.setText(mList.get(i).getName());
            return view;
        }

        class ViewHolder{
            @ViewInject(R.id.tv_type)
            TextView tv_type;
        }
    }
}

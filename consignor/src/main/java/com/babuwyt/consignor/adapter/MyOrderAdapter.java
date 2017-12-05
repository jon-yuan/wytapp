package com.babuwyt.consignor.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.babuwyt.consignor.R;
import com.babuwyt.consignor.entity.OrderEntity;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;

/**
 * Created by lenovo on 2017/9/11.
 */

public class MyOrderAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<OrderEntity> mList;
    public MyOrderAdapter(Context context){
        this.context=context;
        mList=new ArrayList<OrderEntity>();
    }
    public void setmList(ArrayList<OrderEntity> list){
        if (list!=null){
            mList=list;
        }
    }

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
            view= LayoutInflater.from(context).inflate(R.layout.adapter_item,null);
            x.view().inject(holder,view);
            view.setTag(holder);
        }else {
            holder= (ViewHolder) view.getTag();
        }
        holder.tv_orderno.setText("订单："+mList.get(i).getOrderSysNumber());
        holder.tv_luxian.setText("路线："+mList.get(i).getRouteFrom()+"-"+mList.get(i).getRouteTo());
        holder.tv_time.setText("提货时间:"+mList.get(i).getCreatetime());
        holder.tv_daodatime.setText("到达时间:"+mList.get(i).getFarrivetime());
        return view;
    }

    class ViewHolder{
        @ViewInject(R.id.tv_orderno)
        TextView tv_orderno;
        @ViewInject(R.id.tv_luxian)
        TextView tv_luxian;
        @ViewInject(R.id.tv_time)
        TextView tv_time;
        @ViewInject(R.id.tv_daodatime)
        TextView tv_daodatime;
    }
}

package com.babuwyt.consignor.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.babuwyt.consignor.R;
import com.babuwyt.consignor.entity.AddressEntity;
import com.babuwyt.consignor.ui.activity.AddOrderActivity;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;

/**
 * Created by lenovo on 2017/9/14.
 * 添加地址页面里 地址的adapter
 */

public class AddOrderAddressAdapter extends BaseAdapter {
    private Context mContext;
    private ArrayList<AddressEntity> mList;
    public AddOrderAddressAdapter(Context context){
        this.mContext=context;
        this.mList=new ArrayList<AddressEntity>();
    }
    public void setmList(ArrayList<AddressEntity> list){
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
        ViewHolder holder = null;
        if (view == null) {
            holder = new ViewHolder();
            view = LayoutInflater.from(mContext).inflate(R.layout.adapter_text, null);
            x.view().inject(holder,view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        holder.tv_type.setText(mContext.getString(R.string.tihuodi));
        if (mList.get(i).getAddressType().equalsIgnoreCase("2")){
            holder.tv_type.setText(mContext.getString(R.string.xiehuodi));
        }
        holder.tv_address.setText(mList.get(i).getProvinceName()+mList.get(i).getCityName()
                +mList.get(i).getAreaName()+mList.get(i).getAddressDetails());
        return view;
    }
    class ViewHolder {
        @ViewInject(R.id.tv_address)
        TextView tv_address;
        @ViewInject(R.id.tv_type)
        TextView tv_type;
    }
}

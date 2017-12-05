package com.babuwyt.consignor.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.babuwyt.consignor.R;
import com.babuwyt.consignor.entity.AddressEntity;
import com.babuwyt.consignor.ui.activity.TestActivity;

import java.util.ArrayList;

/**
 * Created by lenovo on 2017/9/15.
 */

public class AddressListAdapter extends RecyclerView.Adapter<AddressListAdapter.ViewHolder> {
    private LayoutInflater mLayoutInflater;
    private Context mContext;
    private ArrayList<AddressEntity> mList;
    public AddressListAdapter(Context context,ArrayList<AddressEntity> list){
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
        mList = list;
    }
    @Override
    public AddressListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new AddressListAdapter.ViewHolder(mLayoutInflater.inflate(R.layout.adapter_addresslist, parent, false));
    }
    @Override
    public void onBindViewHolder(AddressListAdapter.ViewHolder holder, final int position) {
        holder.tv_phone.setText(mList.get(position).getContactPhone());
        holder.tv_name.setText(mList.get(position).getContactName());
        holder.tv_address.setText(mList.get(position).getProvinceName()+mList.get(position).getCityName()
                +mList.get(position).getAreaName()+mList.get(position).getAddressDetails());
        if (mList.get(position).getAddressType().equalsIgnoreCase("1")){
            holder.tv_type.setText(mContext.getString(R.string.tihuodi));
        }else {
            holder.tv_type.setText(mContext.getString(R.string.xiehuodi));
        }

        holder.tv_bianji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edit!=null){
                    edit.edit(position);
                }
            }
        });
        holder.tv_del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (delete!=null){
                    delete.del(position);
                }
            }
        });

    }
    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_phone;
        private TextView tv_name;
        private TextView tv_address;
        private TextView tv_type;
        private TextView tv_bianji;
        private TextView tv_del;

        ViewHolder(View view) {
            super(view);
            tv_phone= view.findViewById(R.id.tv_phone);
            tv_name= view.findViewById(R.id.tv_name);
            tv_address= view.findViewById(R.id.tv_address);
            tv_type= view.findViewById(R.id.tv_type);
            tv_bianji= view.findViewById(R.id.tv_bianji);
            tv_del= view.findViewById(R.id.tv_del);
        }
    }

    private AddressInfoAdapter.Delete delete;
    public void setDelete(AddressInfoAdapter.Delete delete){
        this.delete=delete;
    }
    public interface Edit{
        void edit(int position);
    }
    private AddressInfoAdapter.Edit edit;
    public void setEdit(AddressInfoAdapter.Edit edit){
        this.edit=edit;
    }
    public interface Up{
        void up(int position);
    }
}

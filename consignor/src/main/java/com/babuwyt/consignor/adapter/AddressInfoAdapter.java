package com.babuwyt.consignor.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.babuwyt.consignor.R;
import com.babuwyt.consignor.entity.AddressEntity;
import com.babuwyt.jonylibrary.util.UHelper;
import com.daimajia.swipe.SimpleSwipeListener;
import com.daimajia.swipe.SwipeLayout;
import com.daimajia.swipe.adapters.BaseSwipeAdapter;
import com.google.gson.Gson;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 2017/9/10.
 */

public class AddressInfoAdapter extends BaseSwipeAdapter {
    private Context mContext;
    private ArrayList<AddressEntity> mList;
    public AddressInfoAdapter(Context mContext) {
        this.mContext = mContext;
        mList=new ArrayList<AddressEntity>();
    }
    public void setmList(ArrayList<AddressEntity> list){
        if (list!=null){
            mList=list;
        }
    }

    @Override
    public int getSwipeLayoutResourceId(int position) {
        return R.id.swipe;
    }

    @Override
    public View generateView(final int position, ViewGroup parent) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.listview_item, null);

        return v;
    }
    @Override
    public void fillValues(final int position, View convertView) {
        AddressEntity addressEntity=mList.get(position);
        Log.d("地址",new Gson().toJson(addressEntity));
        final TextView down=convertView.findViewById(R.id.down);
        TextView up=convertView.findViewById(R.id.up);
        TextView tv_phone=convertView.findViewById(R.id.tv_phone);
        TextView tv_name=convertView.findViewById(R.id.tv_name);
        TextView tv_address=convertView.findViewById(R.id.tv_address);
        TextView tv_type=convertView.findViewById(R.id.tv_type);
        TextView tv_bianji=convertView.findViewById(R.id.tv_bianji);
        TextView tv_del=convertView.findViewById(R.id.tv_del);
        tv_phone.setText(mList.get(position).getContactPhone());
        tv_name.setText(mList.get(position).getContactName());
        tv_address.setText(mList.get(position).getProvinceName()+mList.get(position).getCityName()
                +mList.get(position).getAreaName()+mList.get(position).getAddressDetails());
        if (mList.get(position).getAddressType().equalsIgnoreCase("1")){
            tv_type.setText(mContext.getString(R.string.tihuodi));
        }else {
            tv_type.setText(mContext.getString(R.string.xiehuodi));
        }
        if (position == 0){
            up.setEnabled(false);
            up.setBackgroundResource(R.color.main_gray_line);
            down.setEnabled(true);
            down.setBackgroundResource(R.color.color_btn_blue);
        }else{
            up.setEnabled(true);
            up.setBackgroundResource(R.color.color_btn_blue);
        }

        if (position == mList.size()-1&&position != 0){
            down.setEnabled(false);
            down.setBackgroundResource(R.color.main_gray_line);
        }else{
            down.setEnabled(true);
            down.setBackgroundResource(R.color.color_btn_blue);
        }

        tv_bianji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edit!=null){
                    edit.edit(position);
                }
            }
        });
        tv_del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (delete!=null){
                    delete.del(position);
                }
            }
        });
        down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (callbackdown!=null){
                    callbackdown.down(position);
                }
            }
        });

        up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (callbackup!=null){
                    callbackup.up(position);
                }
            }
        });

    }

    @Override
    public int getCount() {
        return mList.size();
    }
    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    public interface Delete{
        void del(int position);
    }
    private Delete delete;
    public void setDelete(Delete delete){
        this.delete=delete;
    }
    public interface Edit{
        void edit(int position);
    }
    private Edit edit;
    public void setEdit(Edit edit){
        this.edit=edit;
    }
    public interface Up{
        void up(int position);
    }
    public interface Down{
        void down(int position);
    }

    private Up callbackup;
    private Down callbackdown;

    public void setDown(Down down){
        this.callbackdown=down;
    }
    public void setUp(Up up){
        this.callbackup=up;
    }

}
